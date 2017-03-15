package brunel.fyp.david.lightscamerahome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomBar bottomBar;
    public FragNavController fragNavController;

    private final int TAB_HOME = FragNavController.TAB1;
    private final int TAB_CAMERAS = FragNavController.TAB2;
    private final int TAB_LIGHTS = FragNavController.TAB3;

    public static List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating list of fragments
        //List<Fragment> fragments = new ArrayList<>(3);
        fragments = new ArrayList<>(3);

        //adding fragments to list
        fragments.add(HomeFragment.newInstance(0));
        fragments.add(CamerasFragment.newInstance(0));
        fragments.add(LightsFragment.newInstance(0));

        //linking fragments to the container
        fragNavController = new FragNavController(getSupportFragmentManager(),R.id.container,fragments);

        //creating bottom nav
        //bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setItems(R.xml.bottombar_tabs_three);

        //listener for tab selection
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tab_home:
                        fragNavController.switchTab(TAB_HOME);
                        break;
                    case R.id.tab_cameras:
                        fragNavController.switchTab(TAB_CAMERAS);
                        break;
                    case R.id.tab_lights:
                        fragNavController.switchTab(TAB_LIGHTS);
                        break;
                }
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if(tabId == R.id.tab_home){
                    fragNavController.clearStack();
                }
            }
        });

    }

    public void pushFrags(){
        fragNavController.push(HomeFragment.newInstance(0));
        fragNavController.push(CamerasFragment.newInstance(0));
        fragNavController.push(LightsFragment.newInstance(0));
    }

    public void popFrags(){
        fragNavController.pop();
        fragNavController.pop();
        fragNavController.pop();
    }

    //new code for replacing fragment doesnt work on older library
//    public void switchLights(){
//        fragNavController.switchTab(TAB_LIGHTS);
//    }
//
//    public void switchCamera(){
//        fragNavController.switchTab(TAB_CAMERAS);
//    }

    @Override
    public void onBackPressed() {
        //new AlertDialog.Builder(this)
        new AlertDialog.Builder(this, R.style.DialogStyle)
                .setTitle("Quit App")
                .setMessage("Are you sure you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
