package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.job4j.cars.model.*;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Repository layout for car shop web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CarsRepository implements Closeable {

    private static final CarsRepository INSTANCE = new CarsRepository();
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private Comparator<Item> comparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public CarsRepository() {
    }

    public static CarsRepository getInstance() {
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
            if (!tx.getStatus().equals(TransactionStatus.ROLLED_BACK)) {
                tx.commit();
            }
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
     * Find all items in database for last days.
     * @param days last days search.
     * @return list of items.
     */
    public List<Item> getAllItemsLastDay(Long days) {
        return this.tx(
                session -> {
                    LocalDateTime searchTime = LocalDateTime.now().minusDays(days);
                    Query query = session.createQuery("from Item i where i.created >= :searchdate");
                    query.setParameter("searchdate", searchTime);
                    List<Item> result = query.list();
                    result.sort(comparator);
                    return result;
                }
        );
    }

    /**
     * Find all items in database by photo.
     * @param withPhoto true if find with photo.
     * @return list of items.
     */
    public List<Item> getAllItemsByPhoto(Boolean withPhoto) {
        return this.tx(
                session -> {
                    List<Item> result;
                    if (withPhoto) {
                        result = session.createQuery("from Item i where i.car.photo != null").list();
                    } else {
                        result = session.createQuery("from Item i where i.car.photo = null").list();
                    }
                    result.sort(comparator);
                    return result;
                }
        );
    }

    /**
     * Get all items for one manufacturer.
     * @param manufacturer manufacturer name.
     * @return list of items.
     */
    public List<Item> getItemsByManufacturer(String manufacturer) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from Item i where i.car.manufacturer.name = :manufacturer");
                    query.setParameter("manufacturer", manufacturer);
                    List<Item> result = query.list();
                    result.sort(comparator);
                    return result;
                }
        );
    }

    /**
     * Find Item by id.
     * @param id id to find.
     * @return optional of Item.
     */
    public Optional<Item> findItemById(String id) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from Item where id=:itemid");
                    query.setParameter("itemid", Integer.valueOf(id));
                    return Optional.of((Item) query.getSingleResult());
                }
        );
    }

    /**
     * Save Item object to database.
     * @param item object to save.
     * @return generated id.
     */
    public Serializable createItem(Item item) {
        return this.tx(
                session -> session.save(item)
        );
    }

    /**
     * Set status of item.
     * @param id of item.
     * @param status new status.
     */
    public boolean setStatus(String id, boolean status) {
        return this.tx(
                session -> {
                    Item item = findItemById(id).get();
                    item.setId(Integer.valueOf(id));
                    item.setSold(status);
                    session.update(item);
                    return true;
                }
        );
    }

    /**
     * Get list of all manufacturers.
     * @return list of manufacturers.
     */
    public List<Manufacturer> getAllManufacturers() {
        return this.tx(
                session -> session.createQuery("from Manufacturer").list()
        );
    }

    /**
     * Save User object to database.
     * @param user object to save.
     * @return generated id.
     */
    public Serializable createUser(User user) {
        return this.tx(
                session -> session.save(user)
        );
    }

    /**
     * Find user by its id.
     * @param id to find.
     * @return optional of user.
     */
    public Optional<User> findUserByID(String id) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from User where id=:userid");
                    query.setParameter("userid", Integer.valueOf(id));
                    return Optional.of((User) query.getSingleResult());
                }
        );
    }

    /**
     * Get user by login and password.
     * @param login user login.
     * @param password user password.
     * @return optional of user.
     */
    public Optional<User> authenticate(String login, String password) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from User where login = :userlogin and password = :userpassword");
                    query.setParameter("userlogin", login);
                    query.setParameter("userpassword", password);
                    Optional<User> result;
                    if (query.getResultList().isEmpty()) {
                        result = Optional.empty();
                    } else {
                        result = Optional.of((User) query.getSingleResult());
                    }
                    return result;
                }
        );
    }

}
