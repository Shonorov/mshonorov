package ru.job4j.tracker;
/**
 * Class for editing item.
 */
     class EditItem implements UserAction {
    @Override
    public int key() {
        return 2;
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

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
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
     */
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

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
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindByID();
        this.actions[5] = new FindByName();
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
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Введите имя заявки:");
            String desc = input.ask("Введите описание заявки:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new item");
        }
    }
    /**
     * Class for showing all items.
     */
    private static class ShowItems implements UserAction {
        @Override
        public int key() {
            return 1;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }


    /**
     * Class for deleting items.
     */
    private class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * Class for finding item by ID.
     */
    private class FindByID implements UserAction {
        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    /**
     * Class for finding items by name.
     */
    private class FindByName implements UserAction {
        @Override
        public int key() {
            return 5;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }
}
