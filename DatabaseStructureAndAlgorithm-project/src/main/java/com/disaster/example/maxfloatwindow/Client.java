package com.disaster.example.maxfloatwindow;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


/*
给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 */
public class Client {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] list = maxSlidingWindow1(nums, k);
        for (int i : list) {
            System.out.println(i);
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] temp = new int[k];
        List<Integer> result = new ArrayList();
        int tempInt = 0;
        int startIndex = 0;
        if (nums.length <= 0) {
            return new int[]{};
        } else if (nums.length < k) {
            tempInt = nums[0];
            for (int i = 0; i < nums.length; i++) {
                if (temp[i] > tempInt) {
                    tempInt = temp[i];
                }
            }
            return new int[]{tempInt};
        } else {
            while (true) {
                if (startIndex == nums.length - k + 1) {
                    break;
                }
                System.arraycopy(nums, startIndex, temp, 0, k);
                tempInt = temp[0];
                for (int i = 1; i < k; i++) {
                    if (temp[i] > tempInt) {
                        tempInt = temp[i];
                    }
                }
                result.add(tempInt);
                startIndex++;
            }
            int[] d = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                d[i] = result.get(i);
            }
            return d;
        }
    }

    public static int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && i - queue.peek() >= k) {
                queue.poll();
            }
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.offer(i);
            if (i >= k - 1) {
                res[j++] = nums[queue.peek()];
            }
        }

        return res;
    }
}
