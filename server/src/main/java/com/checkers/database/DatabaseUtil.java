package com.checkers.database;

import java.util.List;

import org.hibernate.Session;

public final class DatabaseUtil {
    private DatabaseUtil() {}

    public static VariantEntry getVariantByName(String name) {
        try {
            Session session = DatabaseConnection.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            VariantEntry entry = session.createQuery("SELECT v FROM VariantEntry v WHERE type = :type", VariantEntry.class)
                .setParameter("type", name)
                .getSingleResult();
            session.getTransaction().commit();
            session.close();
            return entry;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<MoveEntry> getMovesByMatchId(int matchId) {
        try {
            Session session = DatabaseConnection.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            List<MoveEntry> entries = session.createQuery("SELECT m FROM MoveEntry m WHERE m.match_id = :id", MoveEntry.class)
                .setParameter("id", matchId)
                .getResultList();
            session.getTransaction().commit();
            session.close();
            return entries;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public static MatchEntry getMatchById(int matchId) {
        try {
            Session session = DatabaseConnection.getInstance().getSessionFactory().openSession();
            session.beginTransaction();
            MatchEntry entry = session.createQuery("SELECT m FROM MatchEntry m WHERE ID = :id", MatchEntry.class)
                .setParameter("id", matchId)
                .getSingleResult();
            session.getTransaction().commit();
            session.close();
            return entry;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void persist(DatabaseEntry entry) {
        DatabaseConnection.getInstance().persist(entry);
    }
}
