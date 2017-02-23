package brunel.fyp.david.lightscamerahome;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHMessageType;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.List;
import java.util.Map;
import java.util.Random;

import brunel.fyp.david.lightscamerahome.LightsActivity;
import brunel.fyp.david.lightscamerahome.huelights.AccessPointListAdapter;
import brunel.fyp.david.lightscamerahome.huelights.HueSharedPreferences;

/**
 * Created by David-Desktop on 16/02/2017.
 */

public class LightsFragment extends Fragment{

    private PHHueSDK phHueSDK;
    private PHSDKListener listener;

    private HueSharedPreferences prefs;
    private AccessPointListAdapter adapter;

    private static final int MAX_HUE=65535;
    public static final String TAG = "QuickStart";
    private String appname = "Lights Camera Home";



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

        //Log.d("test","this is one");

        View view = inflater.inflate(R.layout.fragment_lights, container, false);

//        phHueSDK = PHHueSDK.create();
//        //setting app name
//        phHueSDK.setAppName(appname);
//
//        phHueSDK.getNotificationManager().registerSDKListener(listener);
//
//        PHBridge bridge = PHHueSDK.getInstance().getSelectedBridge();
//
//        //searching for bridge
//        PHBridgeSearchManager sm = (PHBridgeSearchManager) phHueSDK.getSDKService(PHHueSDK.SEARCH_BRIDGE);
//        // Start the UPNP Searching of local bridges.
//        sm.search(true, true);
//        String text = sm.toString();
//        Log.d("test",text);
//
//        adapter = new AccessPointListAdapter(getActivity().getApplicationContext(), phHueSDK.getAccessPointsFound());
//
//        //int position =
//
//        //PHAccessPoint accessPoint = (PHAccessPoint) adapter.getItem(1);
//        //PHAccessPoint accessPoint = phHueSDK.getAccessPointsFound();
//
//        PHBridge connectedBridge = phHueSDK.getSelectedBridge();
//        if (connectedBridge != null) {
//            String connectedIP = connectedBridge.getResourceCache().getBridgeConfiguration().getIpAddress();
//            if (connectedIP != null) {   // We are already connected here:-
//                phHueSDK.disableHeartbeat(connectedBridge);
//                phHueSDK.disconnect(connectedBridge);
//            }
//        }
//        phHueSDK.connect(accessPoint);
//
//        // Local SDK Listener
//        listener = new PHSDKListener(){
//
//            @Override
//            public void onAccessPointsFound(List accessPoint) {
//                // Handle your bridge search results here.  Typically if multiple results are returned you will want to display them in a list
//                // and let the user select their bridge.   If one is found you may opt to connect automatically to that bridge.
//                Log.d("test", "access point found");
//                phHueSDK.getAccessPointsFound().clear();
//                phHueSDK.getAccessPointsFound().addAll(accessPoint);
//                adapter.updateData(phHueSDK.getAccessPointsFound());
//                Log.d("test", accessPoint.toString());
//
//            }
//
//            @Override
//            public void onCacheUpdated(List cacheNotificationsList, PHBridge bridge) {
//                // Here you receive notifications that the BridgeResource Cache was updated. Use the PHMessageType to
//                // check which cache was updated, e.g.
//                if (cacheNotificationsList.contains(PHMessageType.LIGHTS_CACHE_UPDATED)) {
//                    System.out.println("Lights Cache Updated ");
//                }
//            }
//
//            @Override
//            public void onBridgeConnected(PHBridge b, String username) {
//                phHueSDK.setSelectedBridge(b);
//                phHueSDK.enableHeartbeat(b, PHHueSDK.HB_INTERVAL);
//                phHueSDK.getLastHeartbeat().put(b.getResourceCache().getBridgeConfiguration() .getIpAddress(), System.currentTimeMillis());
//                prefs.setLastConnectedIPAddress(b.getResourceCache().getBridgeConfiguration().getIpAddress());
//                prefs.setUsername(username);
//                String text = prefs.getLastConnectedIPAddress();
//                Log.d("test", text);
//                Log.d("test", "connected");
//                // Here it is recommended to set your connected bridge in your sdk object (as above) and start the heartbeat.
//                // At this point you are connected to a bridge so you should pass control to your main program/activity.
//                // The username is generated randomly by the bridge.
//                // Also it is recommended you store the connected IP Address/ Username in your app here.  This will allow easy automatic connection on subsequent use.
//            }
//
//            @Override
//            public void onAuthenticationRequired(PHAccessPoint accessPoint) {
//                Log.d("test","push button");
//
//                phHueSDK.startPushlinkAuthentication(accessPoint);
//
//                // Arriving here indicates that Pushlinking is required (to prove the User has physical access to the bridge).  Typically here
//                // you will display a pushlink image (with a timer) indicating to to the user they need to push the button on their bridge within 30 seconds.
//            }
//
//            @Override
//            public void onConnectionResumed(PHBridge bridge) {
//
//            }
//
//            @Override
//            public void onConnectionLost(PHAccessPoint accessPoint) {
//                // Here you would handle the loss of connection to your bridge.
//            }
//
//            @Override
//            public void onError(int code, final String message) {
//                // Here you can handle events such as Bridge Not Responding, Authentication Failed and Bridge Not Found
//            }
//
//            @Override
//            public void onParsingErrors(List parsingErrorsList) {
//                // Any JSON parsing errors are returned here.  Typically your program should never return these.
//            }
//        };
//
//        Button randomButton;
//        randomButton = (Button) view.findViewById(R.id.buttonRand);
//        randomButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                randomLights();
//            }
//        });
        return view;
    }


    public void randomLights() {
        PHBridge bridge = phHueSDK.getSelectedBridge();

        PHBridgeResourcesCache cache = bridge.getResourceCache();

        //List<PHLight> allLights = bridge.getResourceCache().getAllLights();
        List<PHLight> allLights = cache.getAllLights();

        Random rand = new Random();

        for (PHLight light : allLights) {
            PHLightState lightState = new PHLightState();
            lightState.setHue(rand.nextInt(MAX_HUE));
            // To validate your lightstate is valid (before sending to the bridge) you can use:
            // String validState = lightState.validateState();
            bridge.updateLightState(light, lightState, lightListener);
            //  bridge.updateLightState(light, lightState);   // If no bridge response is required then use this simpler form.
        }
    }
    // If you want to handle the response from the bridge, create a PHLightListener object.
    PHLightListener lightListener = new PHLightListener() {

        @Override
        public void onSuccess() {
        }

        @Override
        public void onStateUpdate(Map<String, String> arg0, List<PHHueError> arg1) {
            Log.w(TAG, "Light has updated");
        }

        @Override
        public void onError(int arg0, String arg1) {}

        @Override
        public void onReceivingLightDetails(PHLight arg0) {}

        @Override
        public void onReceivingLights(List<PHBridgeResource> arg0) {}

        @Override
        public void onSearchComplete() {}
    };

}
