package com.disaster.basic.completablefuture;

import lombok.SneakyThrows;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

public class ZeroDependency {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void zeroDependency() {
        //1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "result1", executor);
        //2、CompletableFuture.completedFuture()直接创建一个已完成状态的CompletableFuture
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("result2");
        //3、先初始化一个未完成的CompletableFuture，然后通过complete()、completeExceptionally()，完成该CompletableFuture
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("success");

    }

    @SneakyThrows
    public static void oneDependency() {
        //1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            return "result1";
        }, executor);
        CompletableFuture<Object> cf3 = cf1.thenApply(result -> {
            System.out.println("result = " + result);
            System.out.println(Thread.currentThread().getId());
            return "result3";
        });
        System.out.println(cf3.get());
    }

    @SneakyThrows
    public static void twoDependency(){
        //1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            return "result1";
        }, executor);
        CompletableFuture<Object> cf3 = cf1.thenApply(result -> {
            System.out.println("result = " + result);
            System.out.println(Thread.currentThread().getId());
            return "result3";
        });
        CompletableFuture<Object> cf4 = cf1.thenCombine(cf3, (BiFunction<String, Object, Object>) (result1, result2) -> {
            System.out.println("result1 = " + result1);
            System.out.println("result2 = " + result2);
            return "result4";
        });
        System.out.println(cf4.get());
    }

    public static void moreDependency(){
        //1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            return "result1";
        }, executor);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            return "result2";
        }, executor);
        CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getId());
            return "result3";
        }, executor);
        CompletableFuture<Void> cf4 = CompletableFuture.allOf(cf1, cf2, cf3);
        CompletableFuture<String> result = cf4.thenApply(v -> {
            //这里的join并不会阻塞，因为传给thenApply的函数是在CF3、CF4、CF5全部完成时，才会执行 。
            String join = cf1.join();
            System.out.println("join = " + join);
            String join1 = cf2.join();
            System.out.println("join1 = " + join1);
            String join2 = cf3.join();
            System.out.println("join2 = " + join2);
            //根据result3、result4、result5组装最终result;
            return "result";
        });
    }

    public static void main(String[] args) {
        zeroDependency();
        oneDependency();
        twoDependency();
        moreDependency();
    }
}
