package actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jari Van Melckebeke
 */
public class TimeUtils {
    /**
     * date format of hh mm
     */
    private static SimpleDateFormat dateFormat;

    /**
     * the countdown timer
     */

    /**
     * boolean for alert on specific points for the countdown timer
     */
    private static boolean timerAlert = false;

    /**
     * the remaining time for the countdown timer
     */
    private static long remainingTime;

    /**
     * the stopwatch
     */
    private static Timer stopwatch;

    /**
     * the elapsed time in seconds for the stopwatch
     */
    private static long elapsedTime;


    /**
     * basic constructor
     */
    public TimeUtils() {

    }

    /**
     * method to get the current time
     *
     * @param text in this method ignored
     * @return the time in hh mm
     */
    public static String getTime(String text) {
        long time = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("hh mm");
        String[] formatted = dateFormat.format(new Date(time)).split(" ");
        if (formatted[1].equals("00")) {
            return "It is " + formatted[0] + " \'o clock";
        } else {
            return "It is " + formatted[0] + " hour " + formatted[1];
        }
    }

    /**
     * method to start a stopwatch
     *
     * @param text not used in this method
     * @return confirmation
     */
    public static String startStopwatch(String text) {
        stopwatch = new Timer();
        stopwatch.schedule(new TimerTask() {
            @Override
            public void run() {
                elapsedTime += 1;
            }
        }, 1000);
        return "stopwatch started";
    }

    /**
     * method to stop the stopwatch
     *
     * @param text not used in this method
     * @return the elapsed time
     */
    public static String stopStopwatch(String text) {
        stopwatch.cancel();
        return "stopwatch stopped at " + elapsedTime + " seconds";
    }
}
