package brunel.fyp.david.lightscamerahome;

/**
 * Created by iiro on 7.6.2016.
 */
public class TabMessage {
    public static String get(int menuItemId, boolean isReselection) {
        String message = "Content for ";

        switch (menuItemId) {
//            case R.id.tab_recents:
//                message += "recents";
//                break;
            case R.id.tab_cameras:
                message += "cameras";
                break;
            case R.id.tab_home:
                message += "home";
                break;
            case R.id.tab_lights:
                message += "lights";
                break;
//            case R.id.tab_food:
//                message += "food";
//                break;
        }

        if (isReselection) {
            message += " WAS RESELECTED! YAY!";
        }

        return message;
    }
}
