package actions;

import android.os.Process;
import com.jari.teresa.app.MainActivity;

/**
 * @author Jari Van Melckebeke
 */
public class Control {

    public Control() {

    }

    public static String shutDown(String text, final MainActivity mainActivity) {
        android.os.Process.killProcess(Process.myPid());
        System.exit(0);
        return "System shut down, goodbye";
    }

    public static String cancel(String text, final MainActivity mainActivity) {
        mainActivity.textToSpeech.shutdown();
        mainActivity.createTextToSpeech();
        return "canceled";
    }
}
