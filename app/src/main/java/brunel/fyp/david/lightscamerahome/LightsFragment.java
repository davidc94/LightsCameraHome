package brunel.fyp.david.lightscamerahome;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        //creating buttons and onClickListeners
        Button buttonLightsOn = (Button) view.findViewById(R.id.buttonOn);
        buttonLightsOn.setOnClickListener(clickListener);

        Button buttonLightsOff = (Button) view.findViewById(R.id.buttonOff);
        buttonLightsOff.setOnClickListener(clickListener);

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
                case R.id.buttonOn:
                    try {
                        lightsOn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

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

//    public void lightsThread(String message) throws Exception{
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    connection();
//                    out.write(message);
//                    out.close();
//                    System.err.println(httpCon.getResponseCode());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//
//    }

    //lights on method
    public void lightsOn() throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connection();
                    String text = "{\"on\":true}";
                    out.write(text);
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
                    out.write("{\"on\":true, \"sat\":254, \"bri\":254,\"hue\":65280}");
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
                    out.write("{\"on\":true, \"sat\":254, \"bri\":254,\"hue\":46920}");
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
                    out.write("{\"on\":true, \"sat\":254, \"bri\":254,\"hue\":25500}");
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
