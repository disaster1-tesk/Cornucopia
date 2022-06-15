package com.disaster.High;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HighDemo7 {
    byte[] bytes = new byte[new Random().nextInt(1024*100)];

    public static void main(String[] args) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<HighDemo7> lists = new ArrayList();
        while (true){
            lists.add(new HighDemo7());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
