package com.wgy.interfacework;

public class Homework06 {
    public static void main(String[] args) {
        Person person = new Person("唐僧", new Horse());//不要浪费对象
        person.common();
        person.passRiver();

    }
}
