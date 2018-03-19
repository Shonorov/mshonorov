package ru.job4j.search;

import java.util.LinkedList;
/**
 * Priority Queue simulation.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();
    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int priority = task.getPriority();
        int size = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (priority <= tasks.get(i).getPriority()) {
                tasks.add(i, task);
                break;
            }
        }
        if (tasks.size() == size) {
            tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
