package chapter19.src.com.wgy.file.homework;

import java.io.*;
import java.util.Properties;

/**
 * @author: wuguangyuan
 * @create-date: 2022/7/16 15:20
 */
public class Homework03 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Properties properties = new Properties();
        properties.load(new FileReader("chapter19\\src\\dog.properties"));
        properties.list(System.out);
        Dog dog = new Dog(properties.getProperty("name"), Integer.parseInt(properties.getProperty("age")), properties.getProperty("color"));
        System.out.println("dog对象信息" + dog);

        String filePath = "e:\\dog.dat";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        objectOutputStream.writeObject(dog);
        objectOutputStream.close();
        System.out.println("序列化输出完毕");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
        System.out.println("反序列化读取结果：" + objectInputStream.readObject());
        objectInputStream.close();



    }
}

class Dog implements Serializable {
    String name;
    int age;
    String color;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }


}