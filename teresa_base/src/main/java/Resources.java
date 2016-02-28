import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;

/**
 * @author Jari Van Melckebeke
 */
public class Resources {
    private HashMap<String, String> questionDatabase;
    private HashMap<String, Calendar> birthDayDatabase;
    private Connection connection;
    private Statement statement;

    /**
     * de setup voor Resources
     */
    public Resources() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "tanzania");
            statement = connection.createStatement();
            statement.execute("use teresaDB");
            questionDatabase = setupQuestionDatabase();
            birthDayDatabase = setupBirthdays();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("exception");
        }
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
    private HashMap<String, Calendar> setupBirthdays() {
        HashMap<String, Calendar> database = new HashMap<String, Calendar>();
        try {
            ResultSet set = statement.executeQuery("select * from birthdays");
            while (set.next()) {
                Calendar calendar = Calendar.getInstance();
                //System.out.println(Calendar.MARCH);
                int day = set.getInt("birthday_day");
                int month = set.getInt("birthday_moth");
                int year = set.getInt("birthday_year");
                String name = set.getString("birthday_name") + " " + set.getString("birthday_surname");
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, getMonth(month));
                calendar.set(Calendar.DAY_OF_MONTH, day);
                database.put(name, calendar);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        //System.out.println(database.get("Amber Waegeman"));
        return database;
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

    public HashMap<String, Calendar> getBirthDayDatabase() {
        return birthDayDatabase;
    }
}
