package brunel.fyp.david.lightscamerahome;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by David-Desktop on 16/02/2017.
 */

public class LightsFragment extends Fragment{

    public static LightsFragment newInstance (int instance){
        Bundle args = new Bundle();
        args.putInt("argsInstance", instance);
        LightsFragment lightsFragment = new LightsFragment();
        lightsFragment.setArguments(args);
        return lightsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_lights, container, false);

    }

}
