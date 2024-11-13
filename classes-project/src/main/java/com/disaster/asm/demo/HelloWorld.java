package com.disaster.asm.demo;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * The type Hello world.
 */
public class HelloWorld {
    private String name;
    private int age;

    /**
     * Instantiates a new Hello world.
     */
    public HelloWorld() {
        super();
    }

    @Override
    public String toString() {
        return "This is a HelloWorld object.";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Say hello string.
     *
     * @param message the message
     * @return the string
     */
    public String sayHello(String message) {
        return "Hello World!" + message;
    }

    /**
     * Test int.
     *
     * @param name   the name
     * @param age    the age
     * @param idCard the id card
     * @param obj    the obj
     * @return the int
     */
    public int test(String name, int age, long idCard, Object obj) {
        int hashCode = 0;
        hashCode += name.hashCode();
        hashCode += age;
        hashCode += (int) (idCard % Integer.MAX_VALUE);
        hashCode += obj.hashCode();
        return hashCode;
    }

    /**
     * Add int.
     *
     * @param a the a
     * @param b the b
     * @return the int
     * @throws InterruptedException the interrupted exception
     */
    public int add(int a, int b) throws InterruptedException {
        int c = a + b;
        Random rand = new Random(System.currentTimeMillis());
        int num = rand.nextInt(300);
        Thread.sleep(100 + num);
        return c;
    }

    /**
     * Sub int.
     *
     * @param a the a
     * @param b the b
     * @return the int
     * @throws InterruptedException the interrupted exception
     */
    public int sub(int a, int b) throws InterruptedException {
        int c = a - b;
        Random rand = new Random(System.currentTimeMillis());
        int num = rand.nextInt(400);
        Thread.sleep(100 + num);
        return c;
    }


    /**
     * Test.
     *
     * @param a the a
     * @param b the b
     */
    public void test(int a, int b) {
        int c = Math.max(a, b);
        System.out.println(c);
    }


    public static void output(PrintStream printStream, int val) {
        System.out.println(val);
        printStream.println("ParameterUtils: " + val);
    }

}
