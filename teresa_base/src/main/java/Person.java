import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

/**
 * @author Jari Van Melckebeke
 */

@Entity
@Table(appliesTo = "teresaDB")
public class Person implements java.io.Serializable {
    private int personId;
    private String personName;
    private String personSurname;
    private char personGender;
    private String personEmail;
    private int birthdayDay;
    private int birthdayMonth;
    private int birthdayYear;


}
