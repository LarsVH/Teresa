import java.sql.*;
import java.util.HashMap;

/**
 * @author Jari Van Melckebeke
 */
public class Resources {
    private HashMap<String, String> database = getDatabaseFields();

    /**
     * deze methode zorgt voor de aanlevering van de database
     *
     * @return de database met question - actie
     */
    private HashMap<String, String> getDatabaseFields() {
        HashMap<String, String> database = new HashMap<String, String>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "tanzania");
            Statement statement = connection.createStatement();
            statement.execute("use teresaDB");
            ResultSet set = statement.executeQuery("select * from questions");
            while (set.next()) {
                database.put(set.getString("question"), set.getString("qAction"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return database;
    }

    public HashMap<String, String> getDatabase() {
        return database;
    }
}
