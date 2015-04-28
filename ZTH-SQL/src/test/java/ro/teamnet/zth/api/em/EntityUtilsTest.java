package ro.teamnet.zth.api.em;
import ro.teamnet.zth.appl.domain.Department;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mi on 4/28/2015.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        Department department = new Department();
        String tableName = EntityUtils.getTableName(Department.class);

        assertEquals("Table name should be departments!", "departments", tableName);
    }

}
