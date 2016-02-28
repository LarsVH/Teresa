import com.eclipsesource.json.JsonObject;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Jari Van Melckebeke
 */
public class Action {

    private static Resources resources;
    private static Streak streak;
    private static File musicFile;
    private static AdvancedPlayer player;
    private static int trackNumber = 0;
    private static Thread musicThread = null;

    private Action() {
    }

    /**
     * deze methode zorgt voor de uitvoer van commands
     *
     * @param command het commando zonder 'teresa'
     */
    public static String doAction(String command) throws Exception {
        resources = new Resources();
        HashMap<String, String> questionDatabase = new Resources().getQuestionDatabase();
        String returnType = "";
        if (command.toUpperCase().equals("TERESA")) {
            return "yes, i am listening";
        }
        try {
            Method actionMethod = Class.forName("Action").getMethod(questionDatabase.get(command.toUpperCase()));
            return (String) actionMethod.invoke(returnType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            for (String say : questionDatabase.keySet()) {
                System.out.println(say);
                if (say.contains(command) && !(command.equals("") || command.equals(" "))) {
                    return "Say it again";
                }
            }
            return Action.doAction(new Input().getCommand(true));
        }
        return Action.doAction(new Input().getCommand(true));
    }

    /**
     * deze methode zorgt ervoor dat TERESA de tijd weet
     *
     * @return de tijd in spreekformaat
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        //System.out.println(dateFormat.format(calendar.getTime()));
        if (!dateFormat.format(calendar.getTime()).substring(3).equals("00"))
            return "it is " + dateFormat.format(calendar.getTime()).substring(0, 2) + " hour " + dateFormat.format(calendar.getTime()).substring(3);
        else return "it is " + dateFormat.format(calendar.getTime()).substring(0, 2) + "\'o clock";
    }

    /**
     * deze methode zorgt ervoor dat TERESA de context sluit
     *
     * @return niets
     * @throws Exception
     */
    public static String goodbye() throws Exception {
        streak.close();
        Output.speak("youre welcome");

        return Action.doAction(new Input().getCommand(false));
    }

    /**
     * deze methode roept het weer von het volgende uur op
     *
     * @return het weer van het eerstvolgende uur
     */
    public static String showWeather() {
        ForecastIO forecastIO = new ForecastIO("50.8767", "4.0598", "78d83bb68f21b2ba86082370c54f0b54");
        JsonObject curr = forecastIO.getHourly();
        String output;
        System.out.println(curr);
        return curr.get("summary").toString();
    }

    /**
     * deze methode vraagt naar google calendars om alle verjaardagen op te lijsten
     *
     * @return alle verjaardagen
     * @throws IOException
     */
    public static String getBirthdays() throws IOException {
        String out = "";
        resources = new Resources();
        HashMap<String, Calendar> map = resources.getBirthDayDatabase();
        //System.out.println(map.get("Amber Waegeman").get(Calendar.YEAR));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM");
        Set<String> keys = map.keySet();
        for (int i = 0; i < map.size(); i++) {
            Calendar birthday = map.get(keys.toArray()[i]);
            Calendar nowCal = Calendar.getInstance();
            //System.out.println(birthday.get(Calendar.YEAR));
            int age = nowCal.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            out += keys.toArray()[i] + " gets " + age +
                    " on " + dateFormat.format(birthday.getTime()) + ".\n";
        }
        return out;
    }

    /**
     * deze methode sluit TERESA volledig af
     *
     * @return niets
     */
    public static String quit() {
        System.exit(0);
        return null;
    }

    public static String startTrack() throws JavaLayerException, FileNotFoundException {
        if (musicThread != null)
            musicThread.interrupt();
        musicThread = null;
        musicFile = new File("~/Music/2016").listFiles()[trackNumber];
        System.out.println(musicFile.toString());
        FileInputStream fis = new FileInputStream(musicFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new AdvancedPlayer(bis);
        Runnable r = new Runnable() {
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        };
        musicThread = new Thread(r);
        musicThread.start();
        return musicFile.getName() + " has started";
    }

    public static String stopTrack() throws FileNotFoundException, JavaLayerException, InterruptedException {
        return musicFile.getName() + " has stopped";
    }

    public static String nextTrack() throws FileNotFoundException, JavaLayerException, InterruptedException {
        stopTrack();
        trackNumber++;
        return startTrack();
    }

    public static String prevTrack() throws FileNotFoundException, JavaLayerException, InterruptedException {
        stopTrack();
        if (trackNumber > 0) {
            trackNumber--;
        }
        return startTrack();
    }
}
