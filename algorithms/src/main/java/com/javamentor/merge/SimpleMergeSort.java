package com.javamentor.merge;

import java.util.Arrays;

/**
 * Array of int merge sort algorithm example.
 * O(n*log n) time complexity always. Buffer array is needed.
 */
public class SimpleMergeSort {

    public int[] sort(int[] array) {
        System.out.println("Unsorted: " + Arrays.toString(array));
        int[] result = innerSort(array);
        System.out.println("Sorted:   " + Arrays.toString(result));
        return result;
    }

    /**
     * Sort int array.
     * @param arr input array.
     * @return sorted array.
     */
    private int[] innerSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] arr1 = Arrays.copyOfRange(arr, 0, mid);
        int[] arr2 = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(innerSort(arr1), innerSort(arr2));
    }

    /**
     * Merge two sorted arrays.
     * @param arr1 input array 1.
     * @param arr2 input array 2.
     * @return merged array.
     */
    private int[] merge(int[] arr1, int[] arr2) {
        int length = arr1.length + arr2.length;
        int[] arr = new int[length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < length; i++) {
            if (i1 == arr1.length) {
                arr[i] = arr2[i2++];
            } else if (i2 == arr2.length) {
                arr[i] = arr1[i1++];
            } else {
                if (arr1[i1] < arr2[i2]) {
                    arr[i] = arr1[i1++];
                } else {
                    arr[i] = arr2[i2++];
                }
            }
        }
        return arr;
    }
}
