package com.wgy.homework;

//有一个Car类，有属性temperature(温度)，
//车内有Air(空调)类，有吹风的功能flow,Air会监视车内的温度，
// 如果温度超过40度则吹冷气。如果温度低于0度则吹暖气，
// 如果在这之间则关掉空调。实例化具有不同温度的Car对象，
// 调用空调的flow方法，测试空调吹的风是否正确.
public class Homework07 {
    public static void main(String[] args) {
//		Car c1 = new Car();
//		Car c2 = new Car();
//		Car c3 = new Car();
//		c1.a.flow(41);
//		c2.a.flow(-10);
//		c3.a.flow(28);

        Car c1 = new Car(41);
        Car c2 = new Car(28);
        Car c3 = new Car(-10);
        c1.getAir().flow();
        c2.getAir().flow();
        c3.getAir().flow();
    }
}

class Car{//车类
    private double temperature;


    public Car(double temperature) {
        super();
        this.temperature = temperature;
    }


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


    class Air{//空调类
        public void flow(){//吹风功能
            if(temperature > 40){
                System.out.println("温度为：" + temperature + ",吹冷风");
            }else if(temperature < 0 ){
                System.out.println("温度为：" + temperature + ",吹暖气");
            }else{
                System.out.println("温度为：" + temperature + ",关闭空调");
            }
        }

    }

    //	Air a = new Air();
    //创建一个返回Air对象的方法
    public Air getAir(){
        return new Air();
    }
}
