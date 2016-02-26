import com.eclipsesource.json.JsonObject;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * @author Jari Van Melckebeke
 */
public class Action {
    /**
     * deze methode zorgt voor de aanmaak van bepaalde Actions na een command
     */
    private Action() {

    }

    /**
     * deze methode zorgt voor de uitvoer van commands
     *
     * @param command het commando zonder 'teresa'
     */
    public static String doAction(String command) {
        HashMap<String, String> questionDatabase = new Resources().getDatabase();
        String returnType = "";
        String str = null;
        try {
            System.out.println(command);
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
        }
        return returnType;
    }

    /**
     * deze methode zorgt ervoor dat TERESA de tijd weet
     *
     * @return de tijd in spreekformaat
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        System.out.println(dateFormat.format(calendar.getTime()));
        if (!dateFormat.format(calendar.getTime()).substring(3).equals("00"))
            return "it is " + dateFormat.format(calendar.getTime()).substring(0, 2) + " hour " + dateFormat.format(calendar.getTime()).substring(3);
        else return "it is " + dateFormat.format(calendar.getTime()).substring(0, 2) + "\'o clock";
    }

    /**
     * deze methode zorgt ervoor dat TERESA sluit en 'youre welcome" zegt
     *
     * @return niets
     * @throws Exception
     */
    public static String goodbye() throws Exception {
        Output.speak("youre welcome");
        System.exit(0);
        return "";
    }

    public static String showWeather() {
        ForecastIO forecastIO = new ForecastIO("50.8767","4.0598","78d83bb68f21b2ba86082370c54f0b54");
        JsonObject curr = forecastIO.getHourly();
        String output;
        System.out.println(curr);
        return curr.get("summary").toString();
    }

    public static String getBirthdays() throws IOException, ServiceException {
        return "";
    }
}
