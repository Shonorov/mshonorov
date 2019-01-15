package com.javamentor.quick;

import java.util.Arrays;

/**
 * Array of int quick sort algorithm example.
 * O(n*log n) time complexity average and O(n^2) worst.
 * No buffer array is needed.
 */
public class SimpleQuickSort {

    public int[] sort(int[] array) {
        System.out.println("Unsorted: " + Arrays.toString(array));
        innerSort(array, 0, array.length - 1);
        System.out.println("Sorted:   " + Arrays.toString(array));
        return array;
    }

    private void innerSort(int[] array, int low, int high) {
        //завершить выполнение если длина массива равна 0
        if (array.length == 0) {
            return;
        }
        //завершить выполнение если уже нечего делить
        if (low >= high) {
            return;
        }
        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            //меняем местами
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j) {
            innerSort(array, low, j);
        }
        if (high > i) {
            innerSort(array, i, high);
        }
    }
}
