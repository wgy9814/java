package com.wgy.interfacework;

class Person{
    private String name;
    private Vehicles vehicles;//接口可以作为属性？
    // 接口like父类，可以接收实现了该接口的类的对象

    public Person(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }
    //要求5：具体要求封装成方法
    public void passRiver(){
//        Boat boat = VehiclesFactory.getBoat();
//        boat.work();//重复了,用vercile，且不能浪费构造器的vehicle

        //判断一下，当前的vehicles属性是null，就获取一艘船Boat
        // boat = VehiclesFactory.getBoat();
        //boat.work();
        //如何防止始终使用的是传入的马 instanceOf
        // if (vehicles == null) {
        //vehicles instanceof Boat 是判断 当前的 vehicles是不是Boat
        // (1)vehicles =oul: vehicles instanceof Boat => false
        // (2) vehicles =马对象 : vehicles instanceof Boat => false
        // (3)vehicles = 船对象: vehicles instanceof Boat => true

        if (!(vehicles instanceof Boat)){
            //重要！if(vehicles==null)，则如果是马也判断不出来
            vehicles = VehiclesFactory.getBoat();
        }
        vehicles.work();


    }
    public void common(){
        //得到马儿
        //判断一下，当前的vehicles属性是null，就获取一匹马
        if (!(vehicles instanceof Horse)){
            //这里使用的是多态
            vehicles = VehiclesFactory.getHorse();
        }
        vehicles.work();
    }
}
