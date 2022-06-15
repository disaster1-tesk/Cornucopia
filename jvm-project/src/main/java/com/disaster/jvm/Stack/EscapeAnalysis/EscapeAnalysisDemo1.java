package com.disaster.jvm.Stack.EscapeAnalysis;

public class EscapeAnalysisDemo1 {
    private volatile static EscapeAnalysisDemo1 test=null;

    //不加volatile可能会出现指令重排序，导致其他线程获取的这个对象是一个没有没有new完全的对象
    public static EscapeAnalysisDemo1 getInstance() {
        if (test == null) {
            synchronized (EscapeAnalysisDemo1.class) {
                if (test == null) {
                    test = new EscapeAnalysisDemo1();
                }
            }
        }
        return test;
    }
    public static void main(String[] args) {
        EscapeAnalysisDemo1.getInstance().test();
    }
    public void test(){
        StringBuffer stringBuffer = test1();
        System.out.println(stringBuffer);
    }
    //出现了逃逸
    private StringBuffer test1() {
        StringBuffer sb = new StringBuffer();
        sb.append("a");
        sb.append("b");
        return sb;
    }
    //这么写sb不会发生逃逸
    private String test2(){
        StringBuffer sb = new StringBuffer();
        sb.append("a");
        sb.append("b");
        return sb.toString();
    }

}
