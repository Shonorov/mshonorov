package ru.job4j.trade;

import java.util.*;

/**
 * Stock market trading glass realization.
 * @author Mikhail Shonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TradeGlass {
    /**
     * book - imitent name.
     * items - trade item list.
     */
    private String book;
    private ArrayList<TradeItem> buyItems = new ArrayList<>();
    private ArrayList<TradeItem> sellItems = new ArrayList<>();

    public TradeGlass(String book) {
        this.book = book;
    }

    public String getBook() {
        return book;
    }

    /**
     * Add item to the appropriate glass.
     * @param item item to add.
     * @return true if success.
     */
    public boolean addItem(TradeItem item) {
        boolean result = false;
        if (Action.BUY.equals(item.getAction())) {
            result = processItem(item, buyItems);
            Collections.sort(buyItems);
        } else if (Action.SELL.equals(item.getAction())) {
            result = processItem(item, sellItems);
            Collections.sort(sellItems);
        }
        return result;
    }

    /**
     * Remove item or proceed to calculations.
     * @param item to process.
     * @param list appropriate list.
     * @return true if success.
     */
    private boolean processItem(TradeItem item, ArrayList<TradeItem> list) {
        boolean result = false;
        if (Type.ADD.equals(item.getType())) {
            result = calculateAdd(item, list);
        } else if (Type.DELETE.equals(item.getType())) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getPrice() == item.getPrice() && list.get(i).getVolume() == item.getVolume()) {
                    list.remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Manages items if there are item with affordable price.
     * @param item to calculate.
     * @param list appropriate list.
     * @return true if success.
     */
    private boolean calculateAdd(TradeItem item, ArrayList<TradeItem> list) {
        boolean result = false;
        ArrayList<TradeItem> checkList = Action.SELL.equals(item.getAction()) ? buyItems : sellItems;
        for (int i = 0; i < checkList.size(); i++) {
            if (checkList.get(i).getPrice() >= item.getPrice()) {
                int difference = checkList.get(i).getVolume() - item.getVolume();
                if (difference < 0) {
                    checkList.remove(i);
                    item.setVolume(Math.abs(difference));
                    list.add(item);
                    result = true;
                    break;
                } else if (difference == 0) {
                    checkList.remove(i);
                    result = true;
                    break;
                } else if (difference > 0) {
                    checkList.get(i).setVolume(difference);
                    checkList.get(i).setPrice(item.getPrice());
                    result = true;
                    break;
                }
            }
        }
        if (!result) {
            list.add(item);
            result = true;
        }
        return result;
    }

    /**
     * Sum the same type items volumes before printing.
     * @param array to sort.
     * @return sorted map.
     */
    private NavigableMap<Double, Integer> printPrepare(ArrayList<TradeItem> array) {
        NavigableMap<Double, Integer> result = new TreeMap<>();
        for (TradeItem item : array) {
            if (result.containsKey(item.getPrice())) {
                result.replace(item.getPrice(), result.get(item.getPrice()) + item.getVolume());
            } else {
                result.put(item.getPrice(), item.getVolume());
            }
        }
        return result.descendingMap();
    }

    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(System.lineSeparator(), System.lineSeparator(), System.lineSeparator());

        for (Map.Entry<Double, Integer> item : printPrepare(buyItems).entrySet()) {
            result.add(String.format("\t\t\t%s\t%s", item.getKey(), item.getValue()));
        }
        for (Map.Entry<Double, Integer> item : printPrepare(sellItems).entrySet()) {
            result.add(String.format("%s\t\t\t%s", item.getValue(), item.getKey()));
        }
        return result.toString();
    }
}
