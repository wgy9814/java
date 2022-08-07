package chapter23.src.com.wgy;

import java.io.Serializable;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/6 13:28
 */
public class Car implements Serializable {
    public String brand ="宝马";
    public int price = 500000;
    public String color ="白色";


    public String tostring() {
        return "Car{" +
                "brand='" + brand + '"'+
                ",price=" + price +
                ",color= '" +
                color + '\''+
                '}';
    }

}
