package brunel.fyp.david.lightscamerahome;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.IVideoPlayer;
import org.videolan.libvlc.LibVlcException;

//import org.videolan.libvlc.IVideoPlayer;
//import org.videolan.libvlc.LibVlcException;

/**
 * Created by David-Desktop on 09/03/2017.
 */

public class VLCActivity extends Activity implements IVideoPlayer {
    private static final String TAG = VLCActivity.class.getSimpleName();

    // size of the video
    private int mVideoHeight;
    private int mVideoWidth;
    private int mVideoVisibleHeight;
    private int mVideoVisibleWidth;
    private int mSarNum;
    private int mSarDen;

    private SurfaceView mSurfaceView;
    private FrameLayout mSurfaceFrame;
    private SurfaceHolder mSurfaceHolder;
    private Surface mSurface = null;

    private LibVLC mLibVLC;

    private String mMediaUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "VideoVLC -- onCreate -- START ------------");
        setContentView(R.layout.activity_vlc);

        //new surfaceview and getting holder
        mSurfaceView = (SurfaceView) findViewById(R.id.player_surface);
        mSurfaceHolder = mSurfaceView.getHolder();

        //new frame layout
        mSurfaceFrame = (FrameLayout) findViewById(R.id.player_surface_frame);
        //setting url to string passed from cameras fragment intent
        mMediaUrl = getIntent().getExtras().getString("videoUrl");

        try {
            //new instance of LibVLC class
            mLibVLC = new LibVLC();
            //setting audio track
            mLibVLC.setAout(mLibVLC.AOUT_AUDIOTRACK);
            //setting video feed
            mLibVLC.setVout(mLibVLC.VOUT_ANDROID_SURFACE);
            //using hardware acceleration
            mLibVLC.setHardwareAcceleration(LibVLC.HW_ACCELERATION_FULL);

            mLibVLC.init(getApplicationContext());
        } catch (LibVlcException e){
            Log.e(TAG, e.toString());
        }

        //getting surface from holder
        mSurface = mSurfaceHolder.getSurface();
        //setting video player to surface
        mLibVLC.attachSurface(mSurface, VLCActivity.this);
        //setting which url to play
        mLibVLC.playMRL(mMediaUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // MediaCodec opaque direct rendering should not be used anymore since there is no surface to attach.
        mLibVLC.stop();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_video_vlc, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void eventHardwareAccelerationError() {
        Log.e(TAG, "eventHardwareAccelerationError()!");
        return;
    }

    @Override
    public void setSurfaceLayout(final int width, final int height, int visible_width, int visible_height, final int sar_num, int sar_den){
        Log.d(TAG, "setSurfaceSize -- START");
        if (width * height == 0)
            return;

        // store video size
        mVideoHeight = height;
        mVideoWidth = width;
        mVideoVisibleHeight = visible_height;
        mVideoVisibleWidth = visible_width;
        mSarNum = sar_num;
        mSarDen = sar_den;

        Log.d(TAG, "setSurfaceSize -- mMediaUrl: " + mMediaUrl + " mVideoHeight: " + mVideoHeight + " mVideoWidth: " + mVideoWidth + " mVideoVisibleHeight: " + mVideoVisibleHeight + " mVideoVisibleWidth: " + mVideoVisibleWidth + " mSarNum: " + mSarNum + " mSarDen: " + mSarDen);
    }

    @Override
    public int configureSurface(android.view.Surface surface, int i, int i1, int i2){
        return -1;
    }

}
