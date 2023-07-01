package org.java.dev.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.java.dev.configuration.hibernate.Datasource;
import org.java.dev.entity.ClientEntity;

import java.util.List;

public class ClientCrudService {
    private final Datasource datasource;

    public ClientCrudService(Datasource datasource) {
        this.datasource = datasource;
    }

    public ClientEntity create(ClientEntity clientEntity) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(clientEntity);
        transaction.commit();
        session.close();
        clientEntity.setId(id);
        return clientEntity;
    }

    public ClientEntity getById(Long id) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        ClientEntity clientEntity = session.get(ClientEntity.class, id);
        transaction.commit();
        session.close();
        return clientEntity;
    }

    public void update(ClientEntity clientEntity) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(clientEntity);
        transaction.commit();
        session.close();
    }

    public int delete(Long id) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "delete from ClientEntity c where c.id=:id";
        Query<String> query = session.createQuery(queryString);
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount;
    }

    public List<ClientEntity> findAll() {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        List<ClientEntity> clientEntities;
        clientEntities = session.createQuery("select c from ClientEntity c", ClientEntity.class).getResultList();
        transaction.commit();
        session.close();
        return clientEntities;
    }
}