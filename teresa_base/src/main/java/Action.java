import com.eclipsesource.json.JsonObject;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private static boolean playMusic = true;

    private Action() {
    }

    /**
     * deze methode zorgt voor de uitvoer van commands
     *
     * @param command het commando zonder 'teresa'
     */
    public static String doAction(String command) throws Exception {
        resources = new Resources();
        streak = new Streak();
        HashMap<String, String> questionDatabase = new Resources().getQuestionDatabase();
        String returnType = "";
        if (command.toUpperCase().equals("TERESA")) {
            return "yes, i am listening";
        }
        try {
            Method actionMethod = Class.forName("Action").getMethod(questionDatabase.get(command.toUpperCase()));
            return (String) actionMethod.invoke(returnType);
        } catch (NoSuchMethodException e) {
            Output.speak("error 0 0 2 : feature not yet implemented");
        } catch (ClassNotFoundException ignored) {
        } catch (InvocationTargetException | IllegalAccessException e) {
            Output.speak("error 0 0 3: invocation error or illegal acces exception");
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
     * deze methode vraagt naar de database om alle verjaardagen op te lijsten
     *
     * @return alle verjaardagen
     * @throws IOException
     */
    public static String getBirthdays() throws Exception {
        String out = "";
        resources = new Resources();
        Map<String, Calendar> map = resources.getBirthDayDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM");
        Set<String> keys = map.keySet();
        for (int i = 0; i < map.size(); i++) {
            Calendar birthday = resources.getBirthDayDatabase().values().toArray(new Calendar[resources.getBirthDayDatabase().values().size()])[i];
            out += keys.toArray()[i] + " ages on " + dateFormat.format(birthday.getTime()) + ".\n";
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
        return "goodbye";
    }


    /**
     * roept de eerste 10 tasks op uit de google calendar, en filterd deze als de events gelijk zijn
     *
     * @return tasks
     */
    public static String getTasks() {
        DateTime now = new DateTime(System.currentTimeMillis());
        String out = "";
        try {
            Events events = resources.getCalendar().events().list("primary")
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
            List<Event> items = events.getItems();
            if (items.size() == 0) {
                return "no upcoming events found";
            } else {
                for (Event event : items) {
                    if (!out.contains(event.getSummary())) {
                        out += event.getSummary() + ".";
                    }
                }
            }
        } catch (IOException e) {
            return "input output exception in get day tasks";
        }
        return out;
    }

    public static String getAllContacts() throws Exception {
        ManagePerson managePerson = new ManagePerson();
        return managePerson.listPersons();
    }
}
