import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Timer;

import static org.junit.Assert.*;

/**
 * @author Jari Van Melckebeke
 */
public class ActionTest {
    private Date date;
    @Before
    public void startUp(){
        date = new Date();
    }

    @Test
    public void testDoAction() throws Exception {
        assert Action.doAction("how late is it").equals(String.valueOf(date.getTime()));
        assert Action.doAction("what time is it").equals(String.valueOf(date.getTime()));
        assert Action.doAction("show notifications").equals("you have 12 new mails");
    }
}