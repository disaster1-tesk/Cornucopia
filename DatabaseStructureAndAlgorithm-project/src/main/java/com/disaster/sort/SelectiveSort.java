package com.disaster.sort;

import java.util.Arrays;

/**
 * The type Selective sort.
 *
 * @author disaster
 * @version 1.0
 */
public class SelectiveSort {

    public static void main(String[] args) {
        int[] ints = new int[]{1, 5, 2, 3, 4, 30, 6, 23, 55};
        int[] sort = sort(ints);
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
        int[] ints = Arrays.copyOf(sourceArray, sourceArray.length);
        // 总共要经过N-1轮比较
        for (int i = 0; i < ints.length - 1; i++) {
            int min = i;
            //每轮需要比较的次数N-i
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[j] < ints[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int tmp = ints[i];
                ints[i] = ints[min];
                ints[min] = tmp;
            }
        }
        return ints;
    }
}
