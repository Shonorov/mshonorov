package com.javamentor.binarySearch;

/**
 * Binary search in sorted int array example.
 */
public class SimpleBinarySearch {

    /**
     * Search position of search in array.
     * @param array sorted array to search in.
     * @param search value to search.
     * @return value in array or -1 if not found.
     */
    public int search(int[] array, int search) {
        int result = -1;
        if (array != null) {
            int low = 0, high = array.length, mid;
            while (low < high) {
                mid = (low + high) / 2;
                if (search == array[mid]) {
                    result = mid;
                    break;
                } else {
                    if (search < array[mid]) {
                        high = mid;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        return result;
    }
}
