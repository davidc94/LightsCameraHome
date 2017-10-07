package brunel.fyp.david.lightscamerahome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by David-Desktop on 16/02/2017.
 */

public class CamerasFragment extends Fragment{

    private String ipAddress = "192.168.1.112";
    private String userName = "root";
    private String password = "Axis206";

    //private String streamAddress1 = "http://root:Axis206@192.168.1.112/mjpg/video.mjpg";
    //private String streamAddress1 = "rtsp://192.168.1.166/axis-media/media.amp";
    private String streamAddress1 = "http://home:chell69@192.168.1.166/mjpg/1/video.mjpg";

    private String imageAddress1 = "http://192.168.1.112/axis-cgi/jpg/image.cgi?resolution=640x480";

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

        //creating view with a layout
        View view = inflater.inflate(R.layout.fragment_cameras, container, false);
        //creating new button
        button1 = (Button) view.findViewById(R.id.button1);

//        button1.setBackground(createDrawableFromUrl(imageAddress1));

        //on click listener for button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VLCActivity.class);
                        //send stream address to new VLCActivity
                        intent.putExtra("videoUrl", streamAddress1);
                        //starting intent
                        startActivity(intent);
            }
        });

        button2 = (Button) view.findViewById(R.id.button2);

        return view;

    }

//    public static Drawable createDrawableFromUrl(String imageWebAddress)
//    {
//        //new drawable
//        Drawable drawable = null;
//
//        try
//        {
//            //input stream to connect to url
//            InputStream inputStream = new URL(imageWebAddress).openStream();
//            //setting drawable to the one from the url
//            drawable = Drawable.createFromStream(inputStream, null);
//            //closing input stream
//            inputStream.close();
//        }
//        catch (MalformedURLException ex) { }
//        catch (IOException ex) { }
//
//        return drawable;
//    }



}
