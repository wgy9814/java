package com.wgy.interfacework;


/**
 * @version 1.0
 * @ author sookie
 */





/*
class VehiclesFactory{
    Horse horse=new Horse();
    Boat boat = new Boat();
    public Horse getHorse(){ return horse};
    public Boat getBoat(){return boat};
太冗余，直接return
}*/
class VehiclesFactory{
    //不想再创建对象，用static
    //再优化：白龙马只有一匹，可以不用new，一直一匹马---->用饿汉式
    //改前：
    // public static Horse getHorse(){ return new Horse();}
    //改后：
    private static Horse horse=new Horse();
    private VehiclesFactory() {}//构造器也要私有化
    public static Horse getHorse(){
        return horse;
    }
    public static Boat getBoat(){
        return new Boat();
    }
}
class fly{}

