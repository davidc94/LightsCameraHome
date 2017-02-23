package brunel.fyp.david.lightscamerahome;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import brunel.fyp.david.lightscamerahome.libvlc.DirectoryAdapter;
import brunel.fyp.david.lightscamerahome.libvlc.LibVLC;
import brunel.fyp.david.lightscamerahome.libvlc.LibVlcException;
import brunel.fyp.david.lightscamerahome.libvlc.MediaList;
import brunel.fyp.david.lightscamerahome.libvlc.VideoActivity;

/**
 * Created by David-Desktop on 16/02/2017.
 */

public class CamerasFragment extends Fragment{

    private String ipAddress = "192.168.1.112";
    private String userName = "root";
    private String password = "Axis206";
    private String streamAddress = "http://root:Axis206@192.168.1.112/mjpg/video.mjpg";

    DirectoryAdapter mAdapter;
    LibVLC mLibVLC;

    public static CamerasFragment newInstance (int instance){
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        CamerasFragment camerasFragment = new CamerasFragment();
        camerasFragment.setArguments(args);
        return camerasFragment;
    }

    View.OnClickListener mSimpleListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0)
        {
            switch (arg0.getId()){

                case R.id.button1:
                    Intent intent = new Intent(getActivity(), VideoActivity.class);
                    intent.putExtra(VideoActivity.LOCATION, streamAddress);
                    startActivity(intent);
                    break;

//                case R.id.button2:
//                    Intent intent2 = new Intent(MainActivity.this, VideoActivity.class);
//                    intent2.putExtra(VideoActivity.LOCATION, streamAddress);
//                    startActivity(intent2);
//                    break;

                default:

                    break;

            }

//            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
//            intent.putExtra(VideoActivity.LOCATION, BIG_H264_FILE_ON_HTTP);
//            startActivity(intent);
        }

//        public void onClick_old(View arg0) {
//            // Build the path to the media file
//            String amp3 = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + "/a.mp3";
//            if(!new File(amp3).exists()) {
//                Toast.makeText(
//                        MainActivity.this,
//                        Environment.getExternalStorageDirectory()
//                                .getAbsolutePath() + "/a.mp3 does not exist!",
//                        Toast.LENGTH_LONG).show();
//                return;
//            }
//
//            // LibVLC manages playback with media lists.
//            // Let's get the primary default list that comes with it.
//            MediaList list = mLibVLC.getPrimaryMediaList();
//
//            // Clear the list for demonstration purposes.
//            list.clear();
//
//            // Add the file. Notice that paths _must_ be converted to locations.
//            list.add(LibVLC.PathToURI(amp3));
//
//            // Finally, play it!
//            mLibVLC.playIndex(0);
//        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        getActivity().setTitle("Cameras");


        try {
            mLibVLC = LibVLC.getInstance();
            mLibVLC.init(getActivity());
        } catch(LibVlcException e) {
            Toast.makeText(getActivity(),
                    "Error initializing the libVLC multimedia framework!",
                    Toast.LENGTH_LONG).show();
            //finish();
        }

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Set up the UI elements.
        mAdapter = new DirectoryAdapter();
        Button load_a_mp3 = (Button) getActivity().findViewById(R.id.button1);

        Button load_a_mp32 = (Button) getActivity().findViewById(R.id.button2);

        load_a_mp3.setOnClickListener(mSimpleListener);

        load_a_mp32.setOnClickListener(mSimpleListener);

//        final ListView mediaView = (ListView) getActivity().findViewById(R.id.mediaView);
//        mediaView.setAdapter(mAdapter);
//        mediaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,
//                                    int position, long arg3) {
//                if (mAdapter.isAudioMode()) {
//                    mLibVLC.getMediaList().insert(0,
//                            (String) mAdapter.getItem(position));
//                    mLibVLC.playIndex(0);
//                } else {
//                    Intent intent = new Intent(MainActivity.this, VideoActivity.class);
//                    intent.putExtra(VideoActivity.LOCATION, (String) mAdapter.getItem(position));
//                    startActivity(intent);
//                }
//            }
//        });

        return inflater.inflate(R.layout.fragment_cameras, container, false);

    }



}
