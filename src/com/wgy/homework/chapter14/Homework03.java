package com.wgy.homework.chapter14;

import java.util.*;



/**
 * Created by 此生辽阔 on 2021/7/25 21:18
 */
public class Homework03 {
    public static void main(String[] args) {
        //(1)
        Map<String,Integer> m= new HashMap();
        m.put("jack",650);
        m.put("tom",1200);
        m.put("smith",2900);

        //(2)
        m.put("jack",2600);
        //(3)
        Set<String> strings = m.keySet();
        for(String s:strings)
        {
            m.put(s,m.get(s)+100);
        }

        //(4)

        for(String s:strings)
        {
            System.out.println(s);
        }
        //(5)
        Collection<Integer> values = m.values();
        Iterator<Integer> iterator ;
        iterator = values.iterator();
        while (iterator.hasNext()) {
            Integer next =  iterator.next();
            System.out.println(next);
        }
    }
}
