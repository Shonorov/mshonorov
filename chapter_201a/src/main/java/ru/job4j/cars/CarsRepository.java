package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.cars.model.*;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
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
            tx.commit();
            session.close();
        }
    }

    /**
     * Save Car object to database.
     * @param car object to save.
     * @return generated id.
     */
    public Serializable createCar(Car car) {
        return this.tx(
                session -> session.save(car)
        );
    }

    /**
     * Find car by id.
     * @param id id to find.
     * @return optional of car.
     */
    public Optional<Car> findCarById(String id) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from Car where id=:carid");
                    query.setParameter("carid", Integer.valueOf(id));
                    return Optional.of((Car) query.getSingleResult());
                }
        );
    }

    /**
     * Save Body object to database.
     * @param body object to save.
     * @return generated id.
     */
    public Serializable createBody(Body body) {
        return this.tx(
                session -> session.save(body)
        );
    }

    /**
     * Save Engine object to database.
     * @param engine object to save.
     * @return generated id.
     */
    public Serializable createEngine(Engine engine) {
        return this.tx(
                session -> session.save(engine)
        );
    }

    /**
     * Save GearBox object to database.
     * @param gearbox object to save.
     * @return generated id.
     */
    public Serializable createGearBox(GearBox gearbox) {
        return this.tx(
                session -> session.save(gearbox)
        );
    }

    /**
     * Save Model object to database.
     * @param model object to save.
     * @return generated id.
     */
    public Serializable createModel(Model model) {
        return this.tx(
                session -> session.save(model)
        );
    }

    /**
     * Save Manufacturer object to database.
     * @param manufacturer object to save.
     * @return generated id.
     */
    public Serializable createManufacturer(Manufacturer manufacturer) {
        return this.tx(
                session -> session.save(manufacturer)
        );
    }

}
