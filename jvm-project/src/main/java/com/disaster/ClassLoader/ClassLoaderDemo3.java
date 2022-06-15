package com.disaster.ClassLoader;

import org.junit.Test;
//-XX:+TraceClassLoading
public class ClassLoaderDemo3 {
    public static void main(String[] args) {

    }
    @Test
    public void test(){
        System.out.println(Son.NUM);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("hello world");
        System.out.println(stringBuffer.reverse());
    }
}
class Son extends Father{
  public static int NUM = 1;
}
abstract class Father{

}