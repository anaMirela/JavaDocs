package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

/**
 * Created by Mi on 4/30/2015.
 */
public class DepartmentDaoTest {

    @Test
    public void testFindById(){
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.findById(Department.class, 1100);
    }


}
