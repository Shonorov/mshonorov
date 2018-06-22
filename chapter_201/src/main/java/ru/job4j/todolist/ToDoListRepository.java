package ru.job4j.todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Repository layout for ToDoList web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ToDoListRepository implements Closeable {

    private static final ToDoListRepository INSTANCE = new ToDoListRepository();
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private Comparator<Item> comparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public static ToDoListRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void close() throws IOException {
        if (!factory.isClosed()) {
            factory.close();
        }
    }

    /**
     * Find all items in database.
     * @return list of all items.
     */
    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = session.createQuery("from Item").list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
            result.sort(comparator);
        }
        return result;
    }

    /**
     * Find only not done items in database.
     * @return list of undone items.
     */
    public List<Item> getOnlyCurrentItems() {
        List<Item> result = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = session.createQuery("from Item where done = false").list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
            result.sort(comparator);
        }
        return result;
    }

    /**
     * Create new item in database.
     * @param description description of the new item.
     * @return true if success.
     */
    public boolean createItem(String description) {
        boolean result = false;
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Item newItem = new Item(description);
        try {
            session.save(newItem);
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return result;
    }

    /**
     * Change item's done field.
     * @param id id of item.
     * @param done done parameter.
     * @return true if success.
     */
    public boolean setDone(String id, Boolean done) {
        boolean result = false;
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Item item = findByID(id).get();
        item.setId(Integer.valueOf(id));
        item.setDone(done);
        try {
            session.update(item);
            result = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return result;
    }

    /**
     * Find item by id.
     * @param id id to find.
     * @return optional of item.
     */
    public Optional<Item> findByID(String id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Item where id=:userid");
        query.setParameter("userid", Integer.valueOf(id));
        Optional<Item> result = Optional.of((Item) query.getSingleResult());
        transaction.commit();
        session.close();
        return result;
    }
}
