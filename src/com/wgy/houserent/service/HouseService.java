package com.wgy.houserent.service;


import com.wgy.houserent.domain.House;

public class HouseService {
    private House[] houses;
    private int houseNums = 1;
    private int idCounter = 1;

    public HouseService(int size){
        houses = new House[size];

        houses[0] = new House(1,"jack","123","金水区",2000,"未出租");
    }

    //findbyId方法 返回House对象或null
    public House findById(int findId){

        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }
    //del方法 删除一个房屋信息
    public boolean del(int delId){
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()){
                index = i;
            }
        }
        if (index == -1){
            return false;
        }

        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--houseNums] = null;
        return true;
    }

    public boolean add(House newHouse){


        //判断是否还可以继续添加
        if (houseNums == houses.length){
            System.out.println("数组已满，不能再添加");
            return false;
        }


        houses[houseNums++] = newHouse;
        newHouse.setId(++idCounter);
        return true;
    }

    public House[] list(){
        return houses;
    }

}