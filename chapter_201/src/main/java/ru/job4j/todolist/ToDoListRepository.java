package ru.job4j.todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.io.Closeable;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
     * Wrapper template method.
     * @param command function to execute.
     * @param <T> generic function parameter value.
     * @return method return type.
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    /**
     * Find all items in database.
     * @return list of all items.
     */
    public List<Item> getAllItems() {
        return this.tx(
                session -> {
                    List<Item> result = session.createQuery("from Item").list();
                    result.sort(comparator);
                    return result;
                }
        );
    }

    /**
     * Find only not done items in database.
     * @return list of undone items.
     */
    public List<Item> getOnlyCurrentItems() {
        return this.tx(
                session -> {
                    List<Item> result = session.createQuery("from Item where done = false").list();
                    result.sort(comparator);
                    return result;
                }
        );
    }

    /**
     * Create new item in database.
     * @param description description of the new item.
     * @return true if success.
     */
    public boolean createItem(String description) {
        return this.tx(
                session -> {
                    session.save(new Item(description));
                    return true;
                }
        );
    }

    /**
     * Change item's done field.
     * @param id id of item.
     * @param done done parameter.
     * @return true if success.
     */
    public boolean setDone(String id, Boolean done) {
        return this.tx(
                session -> {
                    Item item = findByID(id).get();
                    item.setId(Integer.valueOf(id));
                    item.setDone(done);
                    session.update(item);
                    return true;
                }
        );
    }

    /**
     * Find item by id.
     * @param id id to find.
     * @return optional of item.
     */
    public Optional<Item> findByID(String id) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from Item where id=:userid");
                    query.setParameter("userid", Integer.valueOf(id));
                    return Optional.of((Item) query.getSingleResult());
                }
        );
    }
}
