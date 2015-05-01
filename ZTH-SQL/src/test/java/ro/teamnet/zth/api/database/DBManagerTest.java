package ro.teamnet.zth.api.database;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Mi on 4/28/2015.
 */
public class DBManagerTest {

    @Test
    public  void testDBManager() {
        assertNotNull(DBManager.getConnection());
        assertTrue(DBManager.checkConnection());
    }
}
