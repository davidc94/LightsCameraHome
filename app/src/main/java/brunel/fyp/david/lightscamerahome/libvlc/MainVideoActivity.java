package brunel.fyp.david.lightscamerahome.libvlc;

import java.io.File;

//import org.videolan.libvlc.LibVLC;
//import org.videolan.libvlc.LibVlcException;
//import org.videolan.libvlc.MediaList;

import brunel.fyp.david.lightscamerahome.R;
import brunel.fyp.david.lightscamerahome.libvlc.LibVLC;
import brunel.fyp.david.lightscamerahome.libvlc.LibVlcException;
import brunel.fyp.david.lightscamerahome.libvlc.MediaList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainVideoActivity extends Activity {
    public final static String TAG = "LibVLCAndroidSample/MainActivity";

    DirectoryAdapter mAdapter;
    LibVLC mLibVLC;

    private String ipAddress = "192.168.1.112";
    private String userName = "root";
    private String password = "Axis206";
    private static String streamAddress = "http://root:Axis206@192.168.1.112/mjpg/video.mjpg";

    //private final String BIG_H264_FILE_ON_HTTP = "http://archive.org/download/rmE163ArabicSubHdarabRunnersTeamBingutopHangukSib.mkv/rmE163ArabicSubHdarabRunnersTeamBingutopHangukSib.mp4";

    //private final String BIG_H264_FILE_ON_HTTP = "rtsp://admin:s61p65d94r97@192.168.1.175/defaultPrimary?streamType=u";

    private final String BIG_H264_FILE_ON_HTTP = "rtsp://192.168.1.143:554/ch0_0.h264";

    private final String avigilon = "rtsp://admin:s61p65d94r97@192.168.1.175/defaultPrimary?streamType=u";

    private final String trendnet = "rtsp://192.168.1.143:554/ch0_0.h264";

    View.OnClickListener mSimpleListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0)
        {
            switch (arg0.getId()){

                case R.id.button1:
                    Intent intent = new Intent(MainVideoActivity.this, VideoActivity.class);
                    intent.putExtra(VideoActivity.LOCATION, avigilon);
                    startActivity(intent);
                    break;

                case R.id.button2:
                    Intent intent2 = new Intent(MainVideoActivity.this, VideoActivity.class);
                    intent2.putExtra(VideoActivity.LOCATION, streamAddress);
                    startActivity(intent2);
                    break;

                default:

                    break;

            }

//            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
//            intent.putExtra(VideoActivity.LOCATION, BIG_H264_FILE_ON_HTTP);
//            startActivity(intent);
        }

        public void Button1(){
            Intent intent = new Intent(MainVideoActivity.this, VideoActivity.class);
            intent.putExtra(VideoActivity.LOCATION, streamAddress);
            startActivity(intent);
        }

        public void onClick_old(View arg0) {
            // Build the path to the media file
            String amp3 = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/a.mp3";
            if(!new File(amp3).exists()) {
                Toast.makeText(
                        MainVideoActivity.this,
                        Environment.getExternalStorageDirectory()
                                .getAbsolutePath() + "/a.mp3 does not exist!",
                        Toast.LENGTH_LONG).show();
                return;
            }

            // LibVLC manages playback with media lists.
            // Let's get the primary default list that comes with it.
            MediaList list = mLibVLC.getPrimaryMediaList();

            // Clear the list for demonstration purposes.
            list.clear();

            // Add the file. Notice that paths _must_ be converted to locations.
            list.add(LibVLC.PathToURI(amp3));

            // Finally, play it!
            mLibVLC.playIndex(0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize the LibVLC multimedia framework.
        // This is required before doing anything with LibVLC.
        try {
            mLibVLC = LibVLC.getInstance();
            mLibVLC.init(MainVideoActivity.this);
        } catch(LibVlcException e) {
            Toast.makeText(MainVideoActivity.this,
                    "Error initializing the libVLC multimedia framework!",
                    Toast.LENGTH_LONG).show();
            finish();
        }

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.fragment_cameras);

        // Set up the UI elements.
        mAdapter = new DirectoryAdapter();
        Button load_a_mp3 = (Button) findViewById(R.id.button1);

        Button load_a_mp32 = (Button) findViewById(R.id.button2);

        load_a_mp3.setOnClickListener(mSimpleListener);

        load_a_mp32.setOnClickListener(mSimpleListener);

//        final ListView mediaView = (ListView) findViewById(R.id.mediaView);
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
//                    Intent intent = new Intent(MainVideoActivity.this, VideoActivity.class);
//                    intent.putExtra(VideoActivity.LOCATION, (String) mAdapter.getItem(position));
//                    startActivity(intent);
//                }
//            }
//        });

//        RadioButton radioAudio = (RadioButton)findViewById(R.id.radioAudio);
//        radioAudio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapter.setAudioMode(true);
//                mAdapter.refresh();
//            }
//        });
//        RadioButton radioVideo = (RadioButton)findViewById(R.id.radioVideo);
//        radioVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mAdapter.setAudioMode(false);
//                mAdapter.refresh();
//            }
//        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.action_settings:
//                Log.d(TAG, "Setting item selected.");
//                return true;
//            case R.id.action_refresh:
//                mAdapter.refresh();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
