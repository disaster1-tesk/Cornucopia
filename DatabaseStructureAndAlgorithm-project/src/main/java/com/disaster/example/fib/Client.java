package com.disaster.example.fib;


public class Client {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(fib(20));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        long startTime1 = System.currentTimeMillis();
        System.out.println(fib1(20));
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime1);

        long startTime2 = System.currentTimeMillis();
        System.out.println(fib2(20));
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2);

    }

    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return (fib(n - 1) + fib(n - 2)) % 1000000007;
    }

    public static int fib1(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    public static int fib2(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }

}