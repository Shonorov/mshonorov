package ru.job4j.trade;

import java.util.HashMap;
import java.util.Map;
/**
 * Stock market.
 * @author Mikhail Shonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Market {
    /**
     * Trade glasses list.
     * Book name.
     * Trade glass.
     */
    Map<String, TradeGlass> glasses = new HashMap<>();

    /**
     * Add item to the map.
     * @param book issuer name.
     * @param type  item type ADD/DELETE.
     * @param action item action BUY/SELL.
     * @param price price amount.
     * @param volume number of units.
     * @return true if success.
     */
    public boolean addItem(String book, Type type, Action action, double price, int volume) {
        if (glasses.containsKey(book)) {
            glasses.get(book).addItem(new TradeItem(book, type, action, price, volume));
        } else {
            glasses.put(book, new TradeGlass(book));
            glasses.get(book).addItem(new TradeItem(book, type, action, price, volume));
        }
        return true;
    }

    /**
     * Print all glasses.
     */
    public void print() {
        for (TradeGlass glass : glasses.values()) {
            System.out.println(glass.getBook());
            System.out.println(String.format("%s\t\t%s\t%s", "Продажа", "Цена", "Покупка"));
            System.out.println(glass);
        }
    }
}
