import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Jari Van Melckebeke
 */
public class ActionTest {
    private SimpleDateFormat dateFormat;
    private Calendar calendar;

    @Before
    public void startUp() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    @Test
    public void testDoAction() throws Exception {
        /*System.out.println("it is " + dateFormat.format(calendar.getTime()).substring(0,2) + " hour "+dateFormat.format(calendar.getTime()).substring(3));
        //System.out.println(Action.doAction("how late is it"));
        //assert Action.doAction("how late is it").equals("it is " + dateFormat.format(calendar.getTime()).substring(0, 2) + " hour " + dateFormat.format(calendar.getTime()).substring(3));
        System.out.println(Action.doAction("list all birthdays"));*/
        //System.out.println(Action.doAction("what do i have to do today"));
    }
}