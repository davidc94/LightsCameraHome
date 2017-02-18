package brunel.fyp.david.lightscamerahome;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by David-Desktop on 16/02/2017.
 */

public class CamerasFragment extends Fragment{

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
        return inflater.inflate(R.layout.fragment_cameras, container, false);

    }

}
