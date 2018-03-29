package ru.job4j.sort;

import java.util.*;

/**
 * Class to sort departments structure.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class SortDepartment {
    /**
     * Department delimiter.
     */
    private static final String DELIM = "\\";

    /**
     * Sorts array in ascending order.
     * @param array array to sort.
     * @return sorted array.
     */
    public String[] sortAscending(String[] array) {
        TreeSet<String> full = addDepartments(array);
        return full.toArray(new String[full.size()]);
    }
    /**
     * Sorts array in descending order.
     * @param array array to sort.
     * @return sorted array.
     */
    public String[] sortDescending(String[] array) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;
                if (o2.length() > o1.length() && o2.substring(0, o1.length()).equals(o1)) {
                    result = -1;
                } else if (o1.length() > o2.length() && o1.substring(0, o2.length()).equals(o2)) {
                    result = 1;
                } else {
                    result = o2.compareTo(o1);
                }
                return result;
            }
        };
        ArrayList<String> toSort = new ArrayList<>(addDepartments(array));
        toSort.sort(comparator);
        return toSort.toArray(new String[toSort.size()]);
    }
    /**
     * Adds missing departments from input array.
     * @param array array with departments.
     * @return full array.
     */
    private TreeSet<String> addDepartments(String[] array) {
        TreeSet<String> deps = new TreeSet<>();
        for (String data : array) {
            String toAdd = data;
            deps.add(toAdd);
            while (toAdd.contains(DELIM)) {
                toAdd = toAdd.substring(0, toAdd.lastIndexOf(DELIM));
                deps.add(toAdd);
            }
        }
        return deps;
    }
}

