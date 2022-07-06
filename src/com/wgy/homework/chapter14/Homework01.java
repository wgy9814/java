package com.wgy.homework.chapter14;


import java.util.ArrayList;

/**
 * Created by 此生辽阔 on 2021/7/25 21:00
 */
public class Homework01 {
    public static void main(String[] args) {
        news news = new news("新冠确诊病例超干方，数百万印度教信徒赴恒河“圣浴”引民众担忧");
        news news2 = new news("男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生");
        ArrayList arrayList = new ArrayList();
        arrayList.add(news );
        arrayList.add(news2 );
        for(int i=arrayList.size();i>0;i--)
        {
            news obj=(news)arrayList.get(i-1);
            System.out.println(obj.getTitle().substring(0,15)+"...");
        }
    }
}

class news{
    String title;
    String type;

    @Override
    public String toString() {
        return "news{" +
                "title='" + title + '\'' +
                '}';
    }

    public news(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

