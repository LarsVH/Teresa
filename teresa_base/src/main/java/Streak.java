import java.util.ArrayList;

/**
 * @author Jari Van Melckebeke
 */
public class Streak {
    ArrayList<String> summary;

    public Streak() {
        summary = new ArrayList<>();
    }

    /**
     * deze methode zorgt ervoor dat een gegeven conversatie-deel aan de summary wordt toegevoegd
     *
     * @param str
     */
    public void update(String str) {
        summary.add(str);
    }

    /**
     * de methode zal de verzamelde conversatie gegevens verwijderen
     */
    public void close() {
        summary.clear();
    }
}
