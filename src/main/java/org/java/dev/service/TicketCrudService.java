package org.java.dev.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.java.dev.configuration.hibernate.Datasource;
import org.java.dev.entity.TicketEntity;

import javax.transaction.Transactional;
import java.util.List;

public class TicketCrudService {
    private final Datasource datasource;

    public TicketCrudService(Datasource datasource) {
        this.datasource = datasource;
    }

    public TicketEntity create(TicketEntity ticketEntity) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        Long id = (Long) session.save(ticketEntity);
        transaction.commit();
        session.close();
        ticketEntity.setId(id);
        return ticketEntity;
    }

    @Transactional
    public TicketEntity getById(Long id) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity = session.get(TicketEntity.class, id);
        transaction.commit();
        session.close();
        return ticketEntity;
    }

    public void update(TicketEntity ticketEntity) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticketEntity);
        transaction.commit();
        session.close();
    }

    public int delete(Long id) {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = "delete from TicketEntity t where t.id=:id";
        Query<String> query = session.createQuery(queryString);
        query.setParameter("id", id);
        int rowCount = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowCount;
    }

    public List<TicketEntity> findAll() {
        Session session = datasource.openSession();
        Transaction transaction = session.beginTransaction();
        List<TicketEntity> ticketEntities;
        ticketEntities = session.createQuery("select c from TicketEntity c", TicketEntity.class).getResultList();
        Hibernate.initialize(ticketEntities.get(0).getClientEntity());
        transaction.commit();
        session.close();
        return ticketEntities;
    }
}