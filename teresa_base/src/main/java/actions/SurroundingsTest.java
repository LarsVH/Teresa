package actions;

/**
 * @author Jari Van Melckebeke
 */
public class SurroundingsTest {

    Surroundings surroundings;

    @org.junit.Before
    public void setUp() throws Exception {
        surroundings = new Surroundings();

    }

    @org.junit.Test
    public void testShowWeather() throws Exception {
        surroundings.showWeather("");
    }
}