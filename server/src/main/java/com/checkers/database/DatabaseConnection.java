package com.checkers.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseConnection {
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
            session = sessionFactory.openSession();
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }

    // public List<?> query(String queryStr, Class<?> queryClass)
    // {
    //     List<DatabaseEntry> result;
    //     try {
    //         Session session = sessionFactory.openSession();
    //         session.beginTransaction();
    //         result = session.createQuery( "from VariantEntry", queryClass ).getResultList();
    //         session.getTransaction().commit();
    //         session.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return result;
    // }
}
