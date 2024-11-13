package com.disaster.sort;

import java.util.Arrays;


/**
 * The type Bubble sort.冒泡排序
 *
 * @author disaster
 * @version 1.0
 */
public class BubbleSort {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[] ints = new int[]{1, 5, 2, 3, 4, 30, 6, 23, 55};
        int[] sort = sort1(ints);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i : sort) {
            sb.append(i);
            sb.append(",");
        }
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1);
        sb.append("]");
        System.out.println("sb = " + sb);
    }

    private static int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 1; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    private static int[] sort1(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }
}
