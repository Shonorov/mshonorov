package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Class for editing item.
 */
    class EditItem extends BaseAction {

    public EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID заявки которую нужно изменить:");
        Item replace = tracker.findByID(id);
        if (replace == null) {
            System.out.printf("%s " + id + "%s", "-------Заявка ", " не найдена-------------");
        } else {
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание заявки :");
            replace.setName(name);
            replace.setDesc(desc);
            tracker.replace(id, replace);
            System.out.println("-------Заявка " + id + " изменена-------------");
        }
    }
}

/**
 * Contains all user menu actions.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MenuTracker {
    /**
     * input - user input
     * tracker - class with actions algorithms.
     * actions - array with action classes.
     * counter - array with actions current position.
     */
    private Input input;
    private Tracker tracker;
    private int position = 0;
    private ArrayList<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Get menu indexes.
     * @return menu index array.
     */
    public ArrayList<Integer> getMenuIndex() {
        ArrayList<Integer> result = new ArrayList<>();
        for (UserAction userAction : actions) {
            result.add(userAction.key());
        }
        return result;
    }
    /**
     * Fills menu items with action classes.
     * this.new AddItem(); - internal class call.
     * new MenuTracker.ShowItems(); - internal static class call.
     * new EditItem(); - neighbour (same file) class call.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem(position++, "Add new Item"));
        this.actions.add(new MenuTracker.ShowItems(position++, "Show all items"));
        this.actions.add(new EditItem(position++, "Edit item"));
        this.actions.add(new DeleteItem(position++, "Delete item"));
        this.actions.add(new FindByID(position++, "Find item by Id"));
        this.actions.add(new FindByName(position++, "Find items by name"));
    }

    /**
     * Executes action class actions.
     * @param key to select.
     */
    public void select(String key) {
        this.actions.get(Integer.valueOf(key)).execute(this.input, this.tracker);
    }

    /**
     * Showing all menu items with IDs.
     */
    public void show() {
        System.out.println("Menu:");
        for (UserAction userAction : actions) {
            System.out.println(userAction.info());
        }
    }

    /**
     * Class for adding new item.
     */
    private class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки:");
            String desc = input.ask("Введите описание заявки:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.printf("%s" + item.getName() + "%s%n", "------------Новая заявка с именем : '", "' создана-----------");
        }
    }
    /**
     * Class for showing all items.
     */
    private static class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------Вывод всех заявок--------------");
            System.out.printf("%s\t\t\t\t\t%s\t%s\t%s\r%n", "id", "name", "desc", "created");
            ArrayList<Item> items = tracker.findAll();
            for (Item item : items) {
                System.out.println(item.print());
            }
            System.out.println("------------Список заявок выведен----------");
        }
    }


    /**
     * Class for deleting items.
     */
    private class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------Удаление заявки-------------");
            String id = input.ask("Введите ID заявки которую нужно удалить:");
            Item delete = tracker.findByID(id);
            if (delete == null) {
                System.out.printf("%s " + id + " %s", "-------Заявка ", " не найдена-------------");
            } else {
                tracker.delete(id);
                System.out.printf("%s " + id + " %s", "-------Заявка ", " удалена-------------");
            }
        }
    }

    /**
     * Class for finding item by ID.
     */
    private class FindByID extends BaseAction {

        public FindByID(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------Поиск заявки по ID-------------");
            String id = input.ask("Введите ID заявки которую нужно найти:");
            Item result = tracker.findByID(id);
            if (result == null) {
                System.out.printf("%s " + id + " %s", "-------Заявка ", " не найдена-------------");
            } else {
                System.out.printf("%s\t\t\t\t\t%s\t%s\t%s\r", "id", "name", "desc", "created");
                System.out.println(tracker.findByID(id).print());
                System.out.println("-------Заявка найдена-------------");
            }
        }
    }

    /**
     * Class for finding items by name.
     */
    private class FindByName extends BaseAction {

        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("-------Поиск заявки по имени------");
            String name = input.ask("Введите имя заявки которую нужно найти:");
            ArrayList<Item> result = tracker.findByName(name);
            if (result.size() == 0) {
                System.out.println("-------Заявки не найдены-------------");
            } else {
                System.out.printf("%s\t\t\t\t\t%s\t%s\t%s\r%n", "id", "name", "desc", "created");
                for (Item item : result) {
                    System.out.println(item.print());
                }
                System.out.println("-------Заявки найдены-------------");
            }
        }
    }
}
