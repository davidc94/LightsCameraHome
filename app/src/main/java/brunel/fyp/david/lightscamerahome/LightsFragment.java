package brunel.fyp.david.lightscamerahome;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.HttpURLConnection;


/**
 * Created by David-Desktop on 16/02/2017.
 */

public class LightsFragment extends Fragment{

    public static URL url;
    public static String urlString = "http://192.168.1.109/api/7SYAlHp9U81re9LheauelW6GS9urzKTuKcdT-GQR/lights/1/state";
    public static HttpURLConnection httpCon;
    public static OutputStreamWriter out;

    public static double Brightness;

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
                //Log.d("CRS=>", String.valueOf(value));
                Brightness = value.intValue();
                Log.d("CRS=>", String.valueOf(Brightness));

                //if slider = 0 turn lights off
                if (Brightness == 0.0){
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

        return view;
    }

    //switch case for OnClickListener
    public final View.OnClickListener clickListener = new View.OnClickListener(){

        public void onClick(View view){
            switch (view.getId()){
                case R.id.buttonOff:
                    try {
                        lightsOff();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.buttonRed:
                    try {
                        lightsRed();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.buttonBlue:
                    try {
                        lightsBlue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case R.id.buttonGreen:
                    try {
                        lightsGreen();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }

    };

    //connection method for creating PUT request and setting data type to JSON
    public void connection() throws Exception{

        url = new URL(urlString);
        httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        httpCon.setRequestProperty("Content-Type", "application/json");
        httpCon.setRequestProperty("Accept", "application/json");
        out = new OutputStreamWriter(
                httpCon.getOutputStream());

    }

    //lights on method
    public void lightsBrightness() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection();
                    out.write("{\"on\":true, \"bri\":"+ ((int) Brightness)+"}");
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
                    connection();
                    out.write("{\"on\":false}");
                    out.close();
                    System.err.println(httpCon.getResponseCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //lights red colour method
    public void lightsRed() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection();
                    out.write("{\"sat\":254, \"hue\":65280}");
                    out.close();
                    System.err.println(httpCon.getResponseCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //lights blue colour method
    public void lightsBlue() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection();
                    out.write("{\"sat\":254, \"hue\":46920}");
                    out.close();
                    System.err.println(httpCon.getResponseCode());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //lights green colour method
    public void lightsGreen() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection();
                    out.write("{\"sat\":254, \"hue\":25500}");
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
