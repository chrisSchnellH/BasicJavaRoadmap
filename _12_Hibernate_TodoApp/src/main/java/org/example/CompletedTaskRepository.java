package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompletedTaskRepository {

    public List<CompletedTask> getAllCompletedTasks() {
        Transaction transaction = null;
        List<CompletedTask> tasks = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();

            // Query to get all completed tasks
            Query<CompletedTask> query = session.createQuery("from CompletedTask", CompletedTask.class);
            tasks = query.list();

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tasks;
    }
}

