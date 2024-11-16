package com.todo;

import org.example.CompletedTask;
import org.example.HibernateUtil;
import org.example.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskRepository {

    public void addTask(Task task) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Task", Task.class).list();
        }
    }

    public void completeTask(int taskId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            if (task != null) {
                CompletedTask completedTask = new CompletedTask(task.getDescription(), String.valueOf(System.currentTimeMillis()));
                session.save(completedTask);
                session.delete(task);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}

