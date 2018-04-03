package ru.job4j.nonblock;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Simple non-blocking cache realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleCache {



    private ConcurrentHashMap<String, Model> models = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, Model> getModels() {
        return models;
    }

    public void add(Model model) {
        models.put(model.getName(), model);
    }

    public void update(Model model) {
        if (compareVersion(model)) {
//            System.out.println(Thread.currentThread().getName() + " " + model.getVersion());
            models.replace(model.getName(), model);
        }
    }

    public void delete(Model model) {
        models.remove(model.getName());
    }

    private synchronized boolean compareVersion(Model model) {
        if (!models.containsKey(model.getName())) {
            throw new NoSuchElementException();
        }
        Model current = models.get(model.getName());
        if (current.getVersion() >= model.getVersion()) {
//            throw new OptimisticLockException();
            throw new RuntimeException();
        }
//        System.out.println(current.getVersion());
        current.setVersion(model.getVersion());
        return true;
    }
}
