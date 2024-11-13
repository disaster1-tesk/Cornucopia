package com.disaster.mode.createtype.prototypemodel.Serializable;


import java.io.*;

public class Person implements Serializable {
    private String name;
    private String age;
    public Son son;

    public Person(String name, String age, Son son){
        this(name, age);
        this.son = son;
    }
    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", son=" + son +
                '}';
    }
    public Object deepCopy(){
        //创建流对象
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        Person person = null;
        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            person = (Person)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bos!=null&&oos!=null&&bis!=null&&ois!=null){
                    bos.close();
                    oos.close();
                    bis.close();
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return person;
    }
}
