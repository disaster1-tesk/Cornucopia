package com.disaster.jvm.Stack.EscapeAnalysis;

public class EscapeAnalysisDemo2 {
    public static void main(String[] args) {
        EscapeAnalysisDemo2 test = new EscapeAnalysisDemo2();
        long timeMillis = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            test.test();
        }
        long l = System.currentTimeMillis();
        System.out.println("消耗的时间:"+(l-timeMillis)+"秒");
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void test(){
        user user = new user();
    }

}
class user{
}