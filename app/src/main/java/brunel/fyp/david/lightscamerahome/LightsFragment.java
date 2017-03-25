package brunel.fyp.david.lightscamerahome;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.HttpURLConnection;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by David-Desktop on 16/02/2017.
 */

public class LightsFragment extends Fragment{

    public static URL url;
    public static String urlString = "http://192.168.1.109/api/7SYAlHp9U81re9LheauelW6GS9urzKTuKcdT-GQR/lights/1/state";
    public static HttpURLConnection httpCon;
    public static OutputStreamWriter out;

    public static double brightness;
    public static int colourValue;
    public static int saturation;
    public static String colour = "";

    public static LightsFragment newInstance (int instance){
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        LightsFragment lightsFragment = new LightsFragment();
        lightsFragment.setArguments(args);
        return lightsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle("Lights");

        //creating view
        View view = inflater.inflate(R.layout.fragment_lights, container, false);

        //creating seekbar
        final BubbleThumbSeekbar seekbar = (BubbleThumbSeekbar) view.findViewById(R.id.rangeSeekbar1);

        // set final value listener
        seekbar.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number value) {
                brightness = value.intValue();
                Log.d("CRS=>", String.valueOf(brightness));

                //if slider = 0 turn lights off
                if (brightness == 0.0){
                    try {
                        lightsOff();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //else set lights to whatever value
                } else {
                    try {
                        lightsBrightness();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //creating buttons and onClickListeners
        Button buttonLightsRed = (Button) view.findViewById(R.id.buttonRed);
        buttonLightsRed.setOnClickListener(clickListener);

        Button buttonLightsBlue = (Button) view.findViewById(R.id.buttonBlue);
        buttonLightsBlue.setOnClickListener(clickListener);

        Button buttonLightsGreen = (Button) view.findViewById(R.id.buttonGreen);
        buttonLightsGreen.setOnClickListener(clickListener);

        Button buttonLightsWhite = (Button) view.findViewById(R.id.buttonWhite);
        buttonLightsWhite.setOnClickListener(clickListener);

        Button buttonWeather = (Button) view.findViewById(R.id.buttonWeather);
        buttonWeather.setOnClickListener(clickListener);

        return view;
    }

    //switch case for OnClickListener
    public final View.OnClickListener clickListener = new View.OnClickListener(){

        public void onClick(View view){
            switch (view.getId()){

                case R.id.buttonRed:
                    try {
                        //setting values
                        colourValue = 65280;
                        saturation = 254;
                        colour = "red";
                        //running method
                        lightsColour();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.buttonBlue:
                    try {
                        //setting values
                        colourValue = 46920;
                        saturation = 254;
                        colour = "blue";
                        //running method
                        lightsColour();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.buttonGreen:
                    try {
                        //setting values
                        colourValue = 25500;
                        saturation = 254;
                        colour = "green";
                        //running method
                        lightsColour();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.buttonWhite:
                    try {
                        colourValue = 38000;
                        saturation = 100;
                        colour = "white";
                        lightsColour();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
//                case R.id.buttonWeather:
//                   GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(LightsFragment.this)
//                            .addApi(Awareness.API)
//                            .build();
//                    mGoogleApiClient.connect();
//                    break;
            }
        }

    };

    //connection method for creating PUT request and setting data type to JSON
    public void connection() throws Exception{

        //new url based on local IP initialised earlier
        url = new URL(urlString);
        //new http connection
        httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        //setting type of message
        httpCon.setRequestMethod("PUT");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Accept", "application/json");
        out = new OutputStreamWriter(
                httpCon.getOutputStream());
    }

    //lights brightness method
    public void lightsBrightness() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //creating connection to bridge
                    connection();
                    //writing message
                    out.write("{\"on\":true, \"bri\":"+ ((int) brightness)+"}");
                    //closing connection
                    out.close();
                    System.err.println(httpCon.getResponseCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //lights off method
    public void lightsOff() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //creating connection to bridge
                    connection();
                    //writing message
                    out.write("{\"on\":false}");
                    //closing connection
                    out.close();
                    System.err.println(httpCon.getResponseCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //lights colour method
    public void lightsColour() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //creating connection to bridge
                    connection();
                    //writing message
                    out.write("{\"sat\":"+saturation+", \"hue\":"+colourValue+"}");
                    //closing connection
                    out.close();
                    System.err.println(httpCon.getResponseCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
