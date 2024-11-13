package com.disaster.basic.queue;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.concurrent.Delayed;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * DelayQueue 并发队列是一个无界阻塞延迟队列 ，队列中的每个元素都有个过期时间，
 * 当从队列获取元素时，只有过期元素才会出队列。队列头元素是最快要过期的元素。
 *
 * @author disaster
 * @version 1.0
 */
public class DelayQueue {
    @SneakyThrows
    public static void main(String[] args) {
        java.util.concurrent.DelayQueue<IDelay> delayQueue = new java.util.concurrent.DelayQueue<>();
        java.util.concurrent.ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            IDelay iDelay = new IDelay(current.nextInt(10000), "task" + i);
            delayQueue.offer(iDelay);
        }
        IDelay iDelay = null;
        try {
            for (; ; ) {
                while ((iDelay = delayQueue.take()) != null) {
                    System.out.println(iDelay.toString());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Data
    static class IDelay implements Delayed {
        private long delayTime;
        private long expire;
        private String taskName;

        public IDelay(long delayTime, String taskName) {
            this.delayTime = delayTime;
            this.expire = System.currentTimeMillis() + delayTime;
            this.taskName = taskName;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expire - System.currentTimeMillis(), MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(MILLISECONDS) - o.getDelay(MILLISECONDS));
        }
    }
}
