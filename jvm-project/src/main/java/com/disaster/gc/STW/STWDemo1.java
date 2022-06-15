package com.disaster.gc.STW;

import java.util.ArrayList;
import java.util.List;

public class STWDemo1 {
    public static void main(String[] args) {
        WorkThread workThread = new WorkThread();
        Thread printTimeThread = new Thread(new PrintTimeThread());
        printTimeThread.start();
        workThread.start ();
    }

    public static class WorkThread extends Thread {
        private List<Object> lists = new ArrayList<>();

        @Override
        public void run() {
            super.run();
            while (true) {
                for (int i = 0; i < 1000; i++) {
                    byte[] buffer = new byte[1024*100];
                    lists.add(buffer);
                }
                if (lists.size() > 10000) {
                    lists.clear();
                    System.gc();
                }
            }
        }
    }

    public static class PrintTimeThread implements Runnable {
        @Override
        public void run() {
            final  long startTime = System.currentTimeMillis();
            while (true){
                long t = System.currentTimeMillis()-startTime;
                System.out.println(t / 1000 + "." + t % 1000);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

