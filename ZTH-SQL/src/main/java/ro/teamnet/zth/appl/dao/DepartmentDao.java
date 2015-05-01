package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by Mi on 4/30/2015.
 */
public class DepartmentDao {

    EntityManager entityManager = new EntityManagerImpl();

    public Department findById(Class<Department> departmentClass, int id) {
        return entityManager.findById(departmentClass, id);
    }

    public List<Department> findAll(Class<Department> departmentClass) {
        return entityManager.findAll(departmentClass);
    }

    public Department insert(Department department) {
        return entityManager.insert(department);
    }

    public Department update(Department department) {
        return entityManager.update(department);
    }

    public void delete(Department department) {
        entityManager.delete(department);
    }
}
