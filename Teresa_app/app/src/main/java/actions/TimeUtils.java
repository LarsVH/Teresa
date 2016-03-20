package actions;

import android.os.CountDownTimer;
import com.jari.teresa.app.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Jari Van Melckebeke
 */
public class TimeUtils {
    private static Calendar now;
    private static SimpleDateFormat dateFormat;
    private static CountDownTimer timer;
    private static boolean timerAlert = false;

    public TimeUtils() {

    }

    public static String getCurrentTime(String text, MainActivity mainActivity) {
        long time = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("hh mm");
        String[] formated = dateFormat.format(new Date(time)).split(" ");
        if (formated[1].equals("00")) {
            return "It is " + formated[0] + " \'o clock";
        } else {
            return "It is " + formated[0] + " hour " + formated[1];
        }
    }

    public static String startTimer(String text, final MainActivity mainActivity) {
        //verwerking van invoer
        String args = text.substring(15);
        long time = 0;
        if (args.contains("hour") || args.contains("minutes") || args.contains("seconds")) {
            if (args.contains("hour")) {
                time += Integer.parseInt(args.substring(0, args.indexOf("hour"))) * 3600000;
                args = args.substring(args.indexOf("hour") + 4);
            }
            if (args.contains("minutes")) {
                time += Integer.parseInt(args.substring(0, args.indexOf("minutes"))) * 60000;
                args = args.substring(args.indexOf("minutes") + 6);
            }
            if (args.contains("seconds")) {
                time += Integer.parseInt(args.substring(0, args.indexOf("seconds"))) * 1000;
            }
            timer = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (timerAlert) {
                        if (millisUntilFinished % 3600000 == 0) {
                            mainActivity.say("Countdown: " + millisUntilFinished / 3600000 + " hour left and counting");
                        }
                        if (millisUntilFinished == 1800000) {
                            mainActivity.say("Countdown: half an our left and counting");
                        }
                        if (millisUntilFinished == 900000) {
                            mainActivity.say("Countdown: a quarter left and counting");
                        }
                        if (millisUntilFinished == 600000) {
                            mainActivity.say("Countdown: 10 minutes left and counting");
                        }
                        if (millisUntilFinished == 300000) {
                            mainActivity.say("Countdown: 5 minutes left and counting");
                        }
                        if (millisUntilFinished == 30000) {
                            mainActivity.say("Countdown: 30 seconds left");
                        }
                        if (millisUntilFinished == 15000) {
                            mainActivity.say("Countdwon: 15 seconds left");
                        }
                        if (millisUntilFinished % 1000 == 0 && millisUntilFinished <= 10000) {
                            mainActivity.say(String.valueOf(millisUntilFinished / 1000));
                        }
                    }
                }

                @Override
                public void onFinish() {
                    mainActivity.say("countdown finished");
                }
            };
            return "timer started at " + timer.toString();
        } else return "error: not enough arguments";
    }
}
