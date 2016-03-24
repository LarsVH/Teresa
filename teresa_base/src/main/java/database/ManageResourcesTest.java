package database;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jari Van Melckebeke
 */
public class ManageResourcesTest {

    ManageResources manageResources;

    @Before
    public void setUp() throws Exception {
        manageResources = new ManageResources();
    }

    @Test
    public void testCreateNew() throws Exception {
        manageResources.createNew();
    }
}