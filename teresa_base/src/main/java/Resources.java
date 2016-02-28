import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;

import java.io.*;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.*;


/**
 * @author Jari Van Melckebeke
 */
public class Resources {
    private HashMap<String, String> questionDatabase;
    private Map<String, Calendar> birthDayDatabase;
    private com.google.api.services.calendar.Calendar calendar;
    private Connection connection;
    private Statement statement;

    /**
     * de setup voor Resources
     */
    public Resources() throws Exception {
        calendar = setupGoogleCalendar();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "tanzania");
            statement = connection.createStatement();
            statement.execute("use teresaDB");
            questionDatabase = setupQuestionDatabase();
            birthDayDatabase = setupBirthdays();
        } catch (SQLException e) {
            Output.speak("mysql exception");
        }
    }

    /**
     * deze methode sorteert een opgegeven Map naar values
     *
     * @param map de ongesorteerde map
     * @return een gesorteerde map op value
     */
    private static Map sortByValue(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry) (o1)).getValue())
                .compareTo(((Map.Entry) (o2)).getValue()));

        Map result = new LinkedHashMap<>();
        for (Object aList : list) {
            Map.Entry entry = (Map.Entry) aList;
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private com.google.api.services.calendar.Calendar setupGoogleCalendar() throws Exception {
        final String APP_NAME = "Teresa Calendar";
        final java.io.File DATA_STORE_DIR = new File(System.getProperty("user.home"), ".credentials/calendar-teresa.json");
        final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR_READONLY);

        FileDataStoreFactory DATA_STORE_FACTORY = null;
        HttpTransport HTTP_TRANSPORT = null;
        InputStream in = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/client_id.json");
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (GeneralSecurityException | IOException e) {
            Output.speak("exception in Google Calendar");
            System.exit(0);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline").build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");

        com.google.api.services.calendar.Calendar calendarService = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APP_NAME).build();
        return calendarService;
    }

    /**
     * deze methode zorgt voor de aanlevering van de vraagdatabase
     *
     * @return de database met question - actie
     */
    private HashMap<String, String> setupQuestionDatabase() {
        HashMap<String, String> database = new HashMap<String, String>();
        try {
            ResultSet set = statement.executeQuery("select * from questions");
            while (set.next()) {
                database.put(set.getString("question"), set.getString("qAction"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return database;
    }

    /**
     * deze methode zorgt voor de aanlevering van de verjaardagsdatabase
     *
     * @return de database met verjaardagen
     */
    private Map<String, Calendar> setupBirthdays() {
        TreeMap<String, Calendar> database = new TreeMap<String, Calendar>();
        try {
            ResultSet set = statement.executeQuery("select * from birthdays order by birthday_moth, birthday_day");
            while (set.next()) {
                Calendar calendar = Calendar.getInstance();
                int day = set.getInt("birthday_day");
                int month = set.getInt("birthday_moth");
                //int year = set.getInt("birthday_year");
                String name = set.getString("birthday_name") + " " + set.getString("birthday_surname");
                //calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, getMonth(month));
                calendar.set(Calendar.DAY_OF_MONTH, day);
                database.put(name, calendar);
                //System.out.println("name = " + name);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        //System.out.println(database.entrySet());
        //System.out.println(sortByValue(database).keySet());
        return sortByValue(database);
    }

    /**
     * deze methode roept de magic constant voor de maand op
     *
     * @param month de maand in normale cijfernotatie
     * @return de maand als constante
     */
    private int getMonth(int month) {
        switch (month) {
            case 1:
                return Calendar.JANUARY;
            case 2:
                return Calendar.FEBRUARY;
            case 3:
                return Calendar.MARCH;
            case 4:
                return Calendar.APRIL;
            case 5:
                return Calendar.MAY;
            case 6:
                return Calendar.JUNE;
            case 7:
                return Calendar.JULY;
            case 8:
                return Calendar.AUGUST;
            case 9:
                return Calendar.SEPTEMBER;
            case 10:
                return Calendar.OCTOBER;
            case 11:
                return Calendar.NOVEMBER;
            case 12:
                return Calendar.DECEMBER;
            default:
                return 0;
        }
    }

    public HashMap<String, String> getQuestionDatabase() {
        return questionDatabase;
    }

    public Map<String, Calendar> getBirthDayDatabase() {
        return birthDayDatabase;
    }

    public com.google.api.services.calendar.Calendar getCalendar() {
        return calendar;
    }
}
