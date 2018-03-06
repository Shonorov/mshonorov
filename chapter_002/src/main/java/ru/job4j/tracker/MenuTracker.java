package ru.job4j.tracker;
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
            System.out.println("-------Заявка " + id + " не найдена-------------");
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
    private UserAction[] actions = new UserAction[6];
    private int position = 0;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Get menu indexes.
     * @return menu index array.
     */
    public int[] getMenuIndex() {
        int[] result = new int[actions.length];
        for (int i = 0; i < actions.length; i++) {
            result[i] = actions[i].key();
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
        this.actions[position] = this.new AddItem(position++, "Add new Item");
        this.actions[position] = new MenuTracker.ShowItems(position++, "Show all items");
        this.actions[position] = new EditItem(position++, "Edit item");
        this.actions[position] = new DeleteItem(position++, "Delete item");
        this.actions[position] = new FindByID(position++, "Find item by Id");
        this.actions[position] = new FindByName(position++, "Find items by name");
    }

    /**
     * Executes action class actions.
     * @param key
     */
    public void select(String key) {

        this.actions[Integer.valueOf(key)].execute(this.input, this.tracker);
    }

    /**
     * Showing all menu items with IDs.
     */
    public void show() {
        System.out.println("Menu:");
        for (int i = 0; i < actions.length; i++) {
            if (this.actions[i] != null) {
                System.out.println(this.actions[i].info());
            }
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
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
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
            System.out.println(String.format("%s\t\t\t\t\t%s\t%s\t%s\r", "id", "name", "desc", "created"));
            Item[] items = tracker.findAll();
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
                System.out.println("-------Заявка " + id + " не найдена-------------");
            } else {
                tracker.delete(id);
                System.out.println("-------Заявка " + id + " удалена-------------");
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
                System.out.println("-------Заявка " + id + " не найдена-------------");
            } else {
                System.out.println(String.format("%s\t\t\t\t\t%s\t%s\t%s\r", "id", "name", "desc", "created"));
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
            Item[] result = tracker.findByName(name);
            if (result.length == 0) {
                System.out.println("-------Заявки не найдены-------------");
            } else {
                System.out.println(String.format("%s\t\t\t\t\t%s\t%s\t%s\r", "id", "name", "desc", "created"));
                for (Item item : result) {
                    System.out.println(item.print());
                }
                System.out.println("-------Заявки найдены-------------");
            }
        }
    }
}
