package com.disaster.timecomplexity.v1;

import java.util.Random;
import java.util.Scanner;

/*
 输入长度为 NN 的整数数组 nums ，判断此数组中是否有数字 77 ，若有则返回 true ，否则返回 false。

 时间复杂度
概念定义
根据定义，时间复杂度指输入数据大小为 NN 时，算法运行所需花费的时间。需要注意：

统计的是算法的「计算操作数量」，而不是「运行的绝对时间」。计算操作数量和运行绝对时间呈正相关关系，并不相等。算法运行时间受到「编程语言 、计算机处理器速度、运行环境」等多种因素影响。例如，同样的算法使用 Python 或 C++ 实现、使用 CPU 或 GPU 、使用本地 IDE 或力扣平台提交，运行时间都不同。
体现的是计算操作随数据大小 NN 变化时的变化情况。假设算法运行总共需要「 11 次操作」、「 100100 次操作」，此两情况的时间复杂度都为常数级 O(1)O(1) ；需要「 NN 次操作」、「 100N100N 次操作」的时间复杂度都为 O(N)O(N) 。

最佳情况 \Omega(1)Ω(1) ： nums = [7, a, b, c, ...] ，即当数组首个数字为 77 时，无论 nums 有多少元素，线性查找的循环次数都为 11 次；
最差情况 O(N)O(N) ： nums = [a, b, c, ...] 且 nums 中所有数字都不为 77 ，此时线性查找会遍历整个数组，循环 NN 次；
平均情况 \ThetaΘ ： 需要考虑输入数据的分布情况，计算所有数据情况下的平均时间复杂度；例如本题目，需要考虑数组长度、数组元素的取值范围等；
 */
public class Client {
    public static int count = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ints = {1, 2, 45, 45, 64345, 7, 5, 745, 45, 45};
        bubbleSort(ints);
        algorithm(10);
        System.out.println(count);
        for (int anInt : ints) {
            System.out.println("anInt = " + anInt);
        }
//        System.out.println(isExist(scanner));
    }

    //判断77是否存在
    private static boolean isExist(Scanner scanner) {
        int random = new Random().nextInt();
        int n = scanner.nextInt();
        Integer[] integers = new Integer[n];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = i + random;
        }
        for (Integer integer : integers) {
            if (integer == 77) {
                return true;
            }
        }
        return false;
    }

    //冒泡排序   指数O(N*2)
    public static int[] bubbleSort(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
        return nums;
    }

    /*
    指数 O(2^N)
    一般出现在递归中
     */
    public static int algorithm(int N) {
        if (N <= 0) return 1;
        int count_1 = algorithm(N - 1);
        int count_2 = algorithm(N - 1);
        count++;
        return count_1 + count_2;
    }
    /*
    指数O(N!)
    一般出现类似于全排列中
     */
    int algorithm(int N,int b) {
        if (N <= 0) return 1;
        int count = 0;
        for (int i = 0; i < N; i++) {
            count += algorithm(N - 1);
        }
        return count;
    }
    /*
    指数O(log n)
    一般出现在二分法、分治算法中
     */
    int algorithm(int N,boolean flag) {
        int count = 0;
        float i = N;
        while (i > 1) {
            i = i / 2;
            count++;
        }
        return count;
    }
    /*
    指数O(Nlog n)
    通常出现在排序中如：快速排序、归并排序
     */
    int algorithm(int N,float f) {
        int count = 0;
        float i = N;
        while (i > 1) {
            i = i / 2;
            for (int j = 0; j < N; j++)
                count++;
        }
        return count;
    }


}
