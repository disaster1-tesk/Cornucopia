package com.disaster.mode.createtype.prototypemodel.Clone.ShallowCopy;

public class Person implements Cloneable{
    private String name;
    private String age;
    public Person son;
    public Person(String name, String age,Person son){
        this(name, age);
        this.son = son;
    }
    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person getSon() {
        return son;
    }

    public void setSon(Person son) {
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = null;
        person = (Person) super.clone();
//        person.son = (Person) person.son.clone();
        return person;
    }
}
