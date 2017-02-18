package brunel.fyp.david.lightscamerahome;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by David-Desktop on 16/02/2017.
 */

public class HomeFragment extends Fragment{

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
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

}
