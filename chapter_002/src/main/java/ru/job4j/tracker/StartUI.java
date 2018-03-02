package ru.job4j.tracker;
/**
 * Starts tracker UI.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Show all items menu index.
     */
    private static final String SHOW_ALL = "1";
    /**
     * Edit selected item menu index.
     */
    private static final String EDIT = "2";
    /**
     * Delete selected item menu index.
     */
    private static final String DELETE = "3";
    /**
     * Find item by ID menu index.
     */
    private static final String FIND_ID = "4";
    /**
     * Find item by name menu index.
     */
    private static final String FIND_NAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_ID.equals(answer)) {
                this.findById();
            } else if (FIND_NAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }
    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Shows all present tasks.
     */
    private void showAllItems() {
        System.out.println("------------Вывод всех заявок--------------");
        System.out.println(String.format("%s\t\t\t\t\t%s\t%s\t%s\r", "id", "name", "desc", "created"));
        Item[] items = tracker.findAll();
        for (Item item : items) {
            System.out.println(item.print());
        }
        System.out.println("------------Список заявок выведен----------");
    }
    /**
     * Edit selected item.
     */
    private void editItem() {
        System.out.println("-------Изменение заявки-------------");
        String id = this.input.ask("Введите ID заявки которую нужно изменить:");
        Item replace = tracker.findByID(id);
        if (replace == null) {
            System.out.println("-------Заявка " + id + " не найдена-------------");
        } else {
            String name = this.input.ask("Введите новое имя заявки :");
            String desc = this.input.ask("Введите новое описание заявки :");
            replace.setName(name);
            replace.setDesc(desc);
            tracker.replace(id, replace);
            System.out.println("-------Заявка " + id + " изменена-------------");
        }
    }
    /**
     * Delete selected item.
     */
    private void deleteItem() {
        System.out.println("-------Удаление заявки-------------");
        String id = this.input.ask("Введите ID заявки которую нужно удалить:");
        Item delete = tracker.findByID(id);
        if (delete == null) {
            System.out.println("-------Заявка " + id + " не найдена-------------");
        } else {
            tracker.delete(id);
            System.out.println("-------Заявка " + id + " удалена-------------");
        }
    }
    /**
     * Find item by ID
     */
    private void findById() {
        System.out.println("-------Поиск заявки по ID-------------");
        String id = this.input.ask("Введите ID заявки которую нужно найти:");
        Item result = tracker.findByID(id);
        if (result == null) {
            System.out.println("-------Заявка " + id + " не найдена-------------");
        } else {
            System.out.println(String.format("%s\t\t\t\t\t%s\t%s\t%s\r", "id", "name", "desc", "created"));
            System.out.println(tracker.findByID(id).print());
            System.out.println("-------Заявка найдена-------------");
        }
    }
    /**
     * Find item by name.
     */
    private void findByName() {
        System.out.println("-------Поиск заявки по имени------");
        String name = this.input.ask("Введите имя заявки которую нужно найти:");
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
    /**
     * Shows user menu.
     */
    private void showMenu() {
        System.out.println("Меню:");
        System.out.println(ADD + ". Add new Item");
        System.out.println(SHOW_ALL + ". Show all items");
        System.out.println(EDIT + ". Edit item");
        System.out.println(DELETE + ". Delete item");
        System.out.println(FIND_ID + ". Find item by Id");
        System.out.println(FIND_NAME + ". Find items by name");
        System.out.println(EXIT + ". Exit Program");
    }

    /**
     * Запускт программы.
     * @param args input arguments.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}