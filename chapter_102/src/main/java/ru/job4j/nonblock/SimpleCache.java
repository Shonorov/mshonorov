package ru.job4j.nonblock;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Simple non-blocking cache realization.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SimpleCache {
    /**
     * Models store.
     */
    private ConcurrentHashMap<String, Model> models = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, Model> getModels() {
        return models;
    }

    /**
     * Add model to the store.
     * @param model model to add.
     */
    public void add(Model model) {
        models.put(model.getName(), model);
    }

    /**
     * Update current model with new data.
     * @param model model to update.
     */
    public void update(Model model) {
        models.computeIfPresent(model.getName(), (k, v) -> compareVersion(model) ? model : null);
    }

    /**
     * Remove model from store.
     * @param model model to remove.
     */
    public void delete(Model model) {
        models.remove(model.getName());
    }

    /**
     * Check if version of model is greater then existing.
     * @param model to check versions.
     * @return true if greater or even.
     */
    private boolean compareVersion(Model model) {
        Model current = models.get(model.getName());
        if (current.getVersion() >= model.getVersion()) {
            System.out.println(Thread.currentThread().getName() + " OptimisticLock");
        }
        return true;
    }
}
