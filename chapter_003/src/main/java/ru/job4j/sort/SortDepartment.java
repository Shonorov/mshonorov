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
        NavigableSet<String> full = addDepartments(array).descendingSet();
        String[] toSort = full.toArray(new String[full.size()]);
        for (int i = 0; i < toSort.length - 1; i++) {
            for (int j = 0; j < toSort.length - 1; j++) {
                if ((toSort[j].length() > toSort[j + 1].length()) && ((toSort[j].substring(0, toSort[j + 1].length())).equals(toSort[j + 1]))) {
                    String temp = toSort[j];
                    toSort[j] = toSort[j + 1];
                    toSort[j + 1] = temp;
                }
            }
        }
        return toSort;
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

