package database;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jari Van Melckebeke
 */
public class ManageCommandTest {
    ManageCommand manageCommand;

    @Before
    public void setUp() throws Exception {
        manageCommand = new ManageCommand();
    }

    @Test
    public void testGetLibrary() throws Exception {
        System.out.println(manageCommand.getLibrary().toString());
    }

    @Test
    public void testListCommands() throws Exception {
        assert manageCommand.getMethod("what time is it").equals(manageCommand.getMethod("how late is it"));
    }

    @Test
    public void testGetMethod() throws Exception {
        System.out.println();
    }
}