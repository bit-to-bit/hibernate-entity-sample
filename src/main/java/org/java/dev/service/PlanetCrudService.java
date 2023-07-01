package org.java.dev.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.java.dev.configuration.hibernate.Datasource;
import org.java.dev.entity.PlanetEntity;

import java.util.List;

public class PlanetCrudService {
    private final Datasource datasource;

    public PlanetCrudService(Datasource datasource) {
        this.datasource = datasource;
    }

    public PlanetEntity create(PlanetEntity planetEntity) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        String id = (String) session.save(planetEntity);
        transaction.commit();
        session.close();
        planetEntity.setId(id);
        return planetEntity;
    }

    public PlanetEntity getById(String id) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        PlanetEntity planetEntity = session.get(PlanetEntity.class, id);
        transaction.commit();
        session.close();
        return planetEntity;
    }

    public void update(PlanetEntity planetEntity) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(planetEntity);
        transaction.commit();
        session.close();
    }

    public int delete(String id) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "delete from PlanetEntity c where c.id=:id";
        Query<String> query = session.createQuery(queryString);
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount;
    }

    public List<PlanetEntity> findAll() {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        List<PlanetEntity> planetEntities;
        planetEntities = session.createQuery("select c from PlanetEntity c", PlanetEntity.class).getResultList();
        transaction.commit();
        session.close();
        return planetEntities;
    }
}
