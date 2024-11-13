package com.disaster.basic.queue;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * PriorityBlockingQueue 是带优先级的无界阻塞队列，每次出队都返回优先级最高或者最低的元素,内部数据结构采用Object数组
 * 其内部是使用平衡二叉树堆实现的，所以直接遍历队列元素不保证有序。默认使用对象的 compareTo 方法提供比较规则，
 * 如果你需要自定义比较规则则可以自定义 comparators
 */
public class PriorityBlockingQueue {

    @SneakyThrows
    public static void main(String[] args) {
//        priority();
        priorityTask();
    }

    private static void priorityTask() {
        java.util.concurrent.PriorityBlockingQueue<Task> priorityBlockingQueue =
                new java.util.concurrent.PriorityBlockingQueue<>(2);
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; i < 10; ++i) {
            priorityBlockingQueue.offer(Task.builder()
                    .priority(current.nextInt(10))
                    .taskName("taskName" + i)
                    .build());

        }
        while (!priorityBlockingQueue.isEmpty()){
            Task poll = priorityBlockingQueue.poll();
            if (Objects.nonNull(poll)){
                poll.doSomeThing();
            }
        }
    }

    private static void priority() throws InterruptedException {
        java.util.concurrent.PriorityBlockingQueue<Integer> priorityBlockingQueue =
                new java.util.concurrent.PriorityBlockingQueue<>(2);
        //put操作内部调用的是offer操作，由于是无界队列，所以不需要阻塞，由于是无界队列所以一直返回 trne
        priorityBlockingQueue.add(2);
        //put操作内部调用的是 offer操作，由于是无界队列，所以不需要阻塞
        priorityBlockingQueue.put(3);
        //offer操作的作用是在队列中插入一个元素，由于是无界队列所以一直返回 trne
        priorityBlockingQueue.offer(1);
        priorityBlockingQueue.offer(5);
        //peek获取队列中的head节点的数据，并不会移除数据
//        System.out.println(priorityBlockingQueue.peek());
        //take 操作的作用是获取队列内部堆树的根节点元素，如果队列为空则阻塞
//        System.out.println(priorityBlockingQueue.poll());
//        System.out.println((7 - 1) >>> 1);
        for (; ; ) {
            System.out.println(priorityBlockingQueue.take());
        }
    }

    private static <T> void siftUpUsingComparator(int k, T x, Object[] array,
                                                  Comparator<? super T> cmp) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = array[parent];
            if (cmp.compare(x, (T) e) >= 0)
                break;
            array[k] = e;
            k = parent;
        }
        array[k] = x;
    }

    @Data
    @Builder
    static class Task implements Comparable<Task> {
        private int priority = 0;
        private String taskName;

        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return 1;
            }
            return -1;
        }

        public void doSomeThing() {
            System.out.println(taskName + ":" + priority);
        }
    }
}
