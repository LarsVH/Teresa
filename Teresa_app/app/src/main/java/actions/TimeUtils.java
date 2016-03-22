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
<<<<<<< HEAD
    private static Calendar now;
=======
    /**
     * date format of hh mm
     */
>>>>>>> 62ef87e885572db29ebb9c776e64176e32619ffe
    private static SimpleDateFormat dateFormat;
    private static CountDownTimer timer;
    private static boolean timerAlert = false;

<<<<<<< HEAD
=======
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
>>>>>>> 62ef87e885572db29ebb9c776e64176e32619ffe
    public TimeUtils() {

    }

    public static String getCurrentTime(String text, MainActivity mainActivity) {
        long time = System.currentTimeMillis();
        dateFormat = new SimpleDateFormat("hh mm");
        String[] formatted = dateFormat.format(new Date(time)).split(" ");
        if (formatted[1].equals("00")) {
            return "It is " + formatted[0] + " \'o clock";
        } else {
            return "It is " + formatted[0] + " hour " + formatted[1];
        }
    }

    public static String startTimer(String text, final MainActivity mainActivity) {
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
<<<<<<< HEAD
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
=======
                        switch (millis) {
                            case 1800000:
                                mainActivity.say("Countdown: half an our left and counting");
                                break;
                            case 900000:
                                mainActivity.say("Countdown: a quarter left and counting");
                                break;
                            case 600000:
                                mainActivity.say("Countdown: 10 minutes left and counting");
                                break;
                            case 300000:
                                mainActivity.say("Countdown: 5 minutes left and counting");
                                break;
                            case 30000:
                                mainActivity.say("Countdown: 30 seconds left");
                                break;
                            case 15000:
                                mainActivity.say("Countdown: 15 seconds left");
                                break;
                            default:
                                if (millis % 3600000 == 0) {
                                    mainActivity.say("Countdown: " + millisUntilFinished / 3600000 + " hour left and counting");
                                } else if (millis % 1000 == 0 && millis <= 10000) {
                                    mainActivity.say(String.valueOf(millisUntilFinished / 1000));
                                }
>>>>>>> 62ef87e885572db29ebb9c776e64176e32619ffe
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
<<<<<<< HEAD
=======

    /**
     * method to ask how much time is left
     *
     * @param text         in this method ignored
     * @param mainActivity in this method ignored
     * @return how much time is left
     */
    public String getTimeLeft(String text, final MainActivity mainActivity) {
        int hours = 0, minutes = 0, seconds = 0;
        long remain = 0;
        if (remainingTime >= 3600000) {
            remain = remainingTime - (remainingTime % 3600000);
            remainingTime -= remain;
            hours = (int) (remainingTime / 3600000);
        }
        if (remain >= 60000) {
            minutes = (int) ((remain - (remain % 60000)) / 60000);
            remain = remain - (remain % 60000);
        }
        if (remain >= 1000) {
            seconds = Math.round(remain / 1000);
        }
        if (hours != 0 && minutes != 0 && seconds != 0)
            return "there's " + hours + " hour, " + minutes + " minutes and " + seconds + " seconds left";
        else if (hours != 0 && minutes != 0) return "there's " + hours + " hour and " + minutes + " minutes left";
        else if (hours != 0 && seconds != 0) return "there's " + hours + " hours and " + seconds + " seconds left";
        else if (hours == 0 && minutes != 0 && seconds != 0)
            return "there's " + minutes + " minutes and " + seconds + " seconds left";
        else if (hours == 0 && minutes != 0) return "there's " + minutes + " minutes left";
        else return "there's " + seconds + " seconds left";
    }

    /**
     * method to set a notification for the countdown timer
     *
     * @param text         the whole input
     * @param mainActivity not used in this
     * @return timer alert confirmed
     */
    public static String setNotificationsForTimer(String text, final MainActivity mainActivity) {
        if (text.contains(" ON")) timerAlert = true;
        else if (text.contains(" OFF")) timerAlert = false;
        return "timer alert set to " + timerAlert;
    }

    /**
     * method to stop the timer
     *
     * @param text         not used in this method
     * @param mainActivity not used in this method
     * @return confirmation
     */
    public static String stopTimer(String text, final MainActivity mainActivity) {
        timer.cancel();
        timer = null;
        return "timer stopped";
    }

    /**
     * method to start a stopwatch
     *
     * @param text         not used in this method
     * @param mainActivity not used in this method
     * @return confirmation
     */
    public static String startStopwatch(String text, final MainActivity mainActivity) {
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
     * @param text         not used in this method
     * @param mainActivity not used in this method
     * @return the elapsed time
     */
    public static String stopStopwatch(String text, final MainActivity mainActivity) {
        stopwatch.cancel();
        return "stopwatch stopped at " + elapsedTime + " seconds";
    }
>>>>>>> 62ef87e885572db29ebb9c776e64176e32619ffe
}
