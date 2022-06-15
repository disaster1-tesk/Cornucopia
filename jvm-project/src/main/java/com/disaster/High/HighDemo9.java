package com.disaster.High;

import java.util.ArrayList;
import java.util.List;
/*
-XX:+HeapDumpBeforeFullGC -XX:HeapDumpPath=d:\student.hprof
 */
public class HighDemo9 {
    static List<Webpage> webpages = new ArrayList<>();
    static void createpage(){
        for (int i = 0; i < 100; i++) {
            webpages.add(new Webpage("https://www."+String.valueOf(i)+".com",String.valueOf(i)));
        }
    }
    public static void main(String[] args) {
        createpage();
        Student st1 = new Student(3,"disaster1");
        Student st2 = new Student(5,"disaster2");
        Student st3 = new Student(7,"disaster3");
        for (int i = 0; i < webpages.size(); i++) {
            if (i%st1.getId()==0){
                st1.visit(webpages.get(i));
            }
            if (i%st2.getId()==0){
                st2.visit(webpages.get(i));
            }
            if (i%st3.getId()==0){
                st3.visit(webpages.get(i));
            }
        }
        webpages.clear();
        System.gc();
    }
}

class Student {
    private int id;
    private String name;
    private ArrayList<Webpage> webpages = new ArrayList<Webpage>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getlistsize(){
        System.out.println(webpages.size());
    }
    public void visit(Webpage webpage) {
        if (webpage != null) {
            webpages.add(webpage);
        }
    }
}

class Webpage {
    private String URL;
    private String Content;

    public Webpage(String URL, String content) {
        this.URL = URL;
        Content = content;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getURL() {
        return URL;
    }

    public String getContent() {
        return Content;
    }
}