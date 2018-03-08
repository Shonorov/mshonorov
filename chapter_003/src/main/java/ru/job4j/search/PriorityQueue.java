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
        if (tasks.size() == 0) {
            tasks.add(task);
        } else if (priority < tasks.getFirst().getPriority()) {
            tasks.addFirst(task);
            return;
        } else if (priority > tasks.getLast().getPriority()) {
            tasks.addLast(task);
            return;
        } else {
            for (int i = 1; i < tasks.size() - 1; i++) {
                if (tasks.get(i).getPriority() < priority && tasks.get(i + 1).getPriority() > priority) {
                    tasks.add(i + 1, task);
                    return;
                }
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
