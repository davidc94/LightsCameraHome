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
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by David-Desktop on 16/02/2017.
 */

public class HomeFragment extends Fragment{

    public static URL url;
    public static String urlString = "http://192.168.1.109/api/7SYAlHp9U81re9LheauelW6GS9urzKTuKcdT-GQR/lights/1/state";
    public static HttpURLConnection httpCon;
    public static OutputStreamWriter out;

    public static double brightness;

    public static HomeFragment newInstance (int instance){
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle("Home");

        //creating view
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button buttonCam1 = (Button) view.findViewById(R.id.buttonCam1);
        buttonCam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mA = new MainActivity();
                mA.switchCamera();
            }
        });

        Button buttonCam2 = (Button) view.findViewById(R.id.buttonCam2);
        buttonCam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mA = new MainActivity();
                mA.switchCamera();
            }
        });

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

        Button buttonColourSwatch = (Button) view.findViewById(R.id.buttonColourSwatch);
        buttonColourSwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mA = new MainActivity();
                mA.switchLights();
            }
        });

        return view;
    }

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
                    out.write("{\"on\":true, \"bri\":"+ ((int) brightness)+"}");
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

}
