package brunel.fyp.david.lightscamerahome;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView messageView;
    private BottomBar bottomBar;
    public FragNavController fragNavController;

    private final int TAB_HOME = FragNavController.TAB1;
    private final int TAB_CAMERAS = FragNavController.TAB2;
    private final int TAB_LIGHTS = FragNavController.TAB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating list of fragments
        List<Fragment> fragments = new ArrayList<>(3);

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


//        messageView = (TextView) findViewById(R.id.messageView);
//
//        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
//        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                messageView.setText(TabMessage.get(tabId, false));
//            }
//        });
//
//        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
//            @Override
//            public void onTabReSelected(@IdRes int tabId) {
//                Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
//
//                //use if statement checks to determine which tab is selected
//                if (tabId == R.id.tab_lights){
//
//                    setContentView(R.layout.activity_cameras);
//                }
//            }
//        });
    }


    @Override
    public void onBackPressed() {
        //new AlertDialog.Builder(this)
        new AlertDialog.Builder(this, R.style.DialogStyle)
                //.setIcon(android.R.drawable.ic_dialog_alert)
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


//        @Override
//        protected void onSaveInstanceState(Bundle outState){
//            super.onSaveInstanceState(outState);
//            bottomBar.onSaveInstanceState(outState);
//        }

}
