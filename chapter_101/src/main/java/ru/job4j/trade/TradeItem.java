package ru.job4j.trade;

import java.time.LocalTime;

enum Type {
    ADD, DELETE
}
enum Action {
    BUY, SELL
}
/**
 * Stock market trade item.
 */
public class TradeItem implements Comparable<TradeItem> {
    /**
     * id - уникальный ключ заявки.
     * book - идентификатор ценной бумаги.
     * type - add/delete - выставить заявку на торги или снять
     * action - bid/ask - заявка имеет два действия. Заявка на покупка ценной бумаги или на продажу.
     * price - цена, по которой мы ходим сделать действия покупки или продажи.
     * volume - количество акций, которые мы ходим продать или купить.
     */
    private String id;
    private String book;
    private Type type;
    private Action action;
    private double price;
    private int volume;

    public TradeItem(String book, Type type, Action action, double price, int volume) {
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
        this.id = LocalTime.now().toString() + (short) (Math.random() * 100);
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Action getAction() {
        return action;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int compareTo(TradeItem o) {
        return Double.compare(o.price, this.price);
    }
}
