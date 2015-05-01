package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by Mi on 4/30/2015.
 */
public class LocationDao {

    EntityManager entityManager = new EntityManagerImpl();

    public Location findById(Class<Location> LocationClass, int id) {
        return entityManager.findById(LocationClass, id);
    }

    public List<Location> findAll(Class<Location> LocationClass) {
        return entityManager.findAll(LocationClass);
    }

    public Location insert(Location Location) {
        return entityManager.insert(Location);
    }

    public Location update(Location Location) {
        return entityManager.update(Location);
    }

    public void delete(Location Location) {
        entityManager.delete(Location);
    }
}
