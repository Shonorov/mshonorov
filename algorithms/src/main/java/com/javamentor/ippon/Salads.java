package com.javamentor.ippon;

import java.util.*;

/**
 * Bob the Minion wants to make fruit salads for his very mean master.
 * He has 5 kinds of fruits: bananas, apricots, oranges, apples and pears.
 * For each fruit salad, he must blend exactly 3 different fruits.
 * Depending on the number of each fruit available, how many fruit salads can he make?
 * For example, if he has:
 *
 *     1 banana
 *     1 apricot
 *     5 oranges
 *     2 apples
 *     0 pear
 *
 * He can at best realize only 2 fruit salads: banana-orange-apple and apricot-orange-apple, after that, he will only have oranges...
 * Note : You can access the source code of the Fruit enumeration by the "Provided Class" tab above the text editor. This code is not editable.
 */
public class Salads {
    public int fruitSalad(EnumMap<Fruit, Integer> fruits) {
        int result = 0;
        List<Integer> list = new ArrayList<>(fruits.values());
        while (list.size() >= 3) {
            list.sort(Collections.reverseOrder());
            for (int i = 0; i < 3; i++) {
                int temp = list.get(i);
                    list.set(i, --temp);
            }
            result++;
            list.removeIf(s -> s == 0);
        }
        return result;
    }

    public static void main(String[] args) {
        Salads salads = new Salads();
        //[Number of salads with 1 banana, 1 apricot, 4 apples and 2 pears] expected:<[2]>
        EnumMap<Fruit, Integer>  fruits1 = new EnumMap<>(Fruit.class);
        fruits1.put(Fruit.BANANA, 1);
        fruits1.put(Fruit.APRICOT, 1);
        fruits1.put(Fruit.APPLE, 4);
        fruits1.put(Fruit.PEAR, 2);
        System.out.println("--1 " + salads.fruitSalad(fruits1));
        //[Number of salads with 2 fruits of each kind] expected:<[3]>
        EnumMap<Fruit, Integer>  fruits2 = new EnumMap<>(Fruit.class);
        fruits2.put(Fruit.BANANA, 2);
        fruits2.put(Fruit.APRICOT, 2);
        fruits2.put(Fruit.APPLE, 2);
        fruits2.put(Fruit.PEAR, 2);
        fruits2.put(Fruit.ORANGE, 2);
        System.out.println("--2 " + salads.fruitSalad(fruits2));
        //[Number of salads with only apricots and apples] expected:<[0]>
        EnumMap<Fruit, Integer>  fruits3 = new EnumMap<>(Fruit.class);
        fruits3.put(Fruit.APRICOT, 2);
        fruits3.put(Fruit.APPLE, 2);
        System.out.println("--3 " + salads.fruitSalad(fruits3));
        //[Number of salads with 12 bananas, 5 apricots, 3 oranges, 2 apples and 1 pear] expected:<[5]>
        EnumMap<Fruit, Integer>  fruits4 = new EnumMap<>(Fruit.class);
        fruits4.put(Fruit.BANANA, 12);
        fruits4.put(Fruit.APRICOT, 5);
        fruits4.put(Fruit.APPLE, 2);
        fruits4.put(Fruit.PEAR, 1);
        fruits4.put(Fruit.ORANGE, 3);
        System.out.println("--4 " + salads.fruitSalad(fruits4));
    }
}
