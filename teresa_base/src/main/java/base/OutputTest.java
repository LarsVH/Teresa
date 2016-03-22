package base;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by jariv on 20/03/2016.
 */
public class OutputTest {
    private Output output;

    @Before
    public void setUp() {
        output = new Output();
    }

    @Test
    public void sayTest() {
        output.say("");
    }

}