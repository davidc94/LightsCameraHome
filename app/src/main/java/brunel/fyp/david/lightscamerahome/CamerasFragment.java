package brunel.fyp.david.lightscamerahome;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;



/**
 * Created by David-Desktop on 16/02/2017.
 */

public class CamerasFragment extends Fragment{

    private String ipAddress = "192.168.1.112";
    private String userName = "root";
    private String password = "Axis206";
    private String streamAddress = "http://root:Axis206@192.168.1.112/mjpg/video.mjpg";

    Button button1;
    Button button2;

    public static CamerasFragment newInstance (int instance){
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        CamerasFragment camerasFragment = new CamerasFragment();
        camerasFragment.setArguments(args);
        return camerasFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle("Cameras");

        View view = inflater.inflate(R.layout.fragment_cameras, container, false);

        button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VLCActivity.class);
                        intent.putExtra("videoUrl", streamAddress);
                        startActivity(intent);
            }
        });

        button2 = (Button) view.findViewById(R.id.button2);

        return view;

    }



}
