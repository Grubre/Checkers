package com.checkers.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import lombok.Getter;

public class DatabaseConnection {
    @Getter
    private SessionFactory sessionFactory;

    public void connect() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.out.println("Failed to create session factory");
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public void persist(DatabaseEntry entry)
    {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(entry);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
