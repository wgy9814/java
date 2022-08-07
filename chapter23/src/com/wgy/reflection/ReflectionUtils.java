package chapter23.src.com.wgy.reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: wuguangyuan
 * @create-date: 2022/8/6 16:25
 */
public class ReflectionUtils {
    public static void main(String[] args) {

    }

    @Test
    public void api_02() throws ClassNotFoundException, NoSuchMethodException {
        //得到Class对象
        Class<?> personcls = Class.forName("chapter23.src.com.wgy.reflection.Person");
        //getDeclaredFields:获取本类中所有属性
        //说明：默认修饰符是0，public是1，private是2，protected是4，static是8，final是16
        Field[] declaredFields = personcls.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("本类中所有属性=" + declaredField.getName()
                    + "该属性的修饰符值=" + declaredField.getModifiers()
                    + "该属性的类型=" + declaredField.getType());
        }

        //getDeclaredMethods:获取本类中所有方法
        Method[] declaredMethods = personcls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("本类中所有方法=" + declaredMethod.getName()
                    + "该方法的访问修饰符值=" + declaredMethod.getModifiers()
                    + "该方法返回类型" + declaredMethod.getReturnType());

            //输出当前这个方法的形参数组情况
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该方法的形参类型=" + parameterType);

            }


        }


        //getDeclaredConstructors:获取本类中所有构造器
        Constructor<?>[] declaredConstructors = personcls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("=================");
            System.out.println("本类中所有构造器=" + declaredConstructor.getName());//这里老师只是输出

            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("该构造器的形参类型=" + parameterType);
            }


        }
    }
    //第一组方法API
    @Test
    public void api_01() throws ClassNotFoundException {
        //得到Class对象
        Class<?> personcls = Class.forName("chapter23.src.com.wgy.reflection.Person");
        // getName:获取全类名
        System.out.println(personcls.getName()) ;//com. hspedu.reflection.Personl
        //getSimpleName:获取简单类名
        System.out.println(personcls.getSimpleName());//Person
        //getFields:获取所有public修饰的属性，包含本类以及父类的
        Field[] fields = personcls. getFields();
        for (Field field : fields) {//增强for
            System.out.println("本类以及父类的属性=" + field.getName());
        }


        //getDeclaredFields：获取本类中所有属性
        Field[] declaredFields = personcls.getDeclaredFields();
        for (Field declaredField : declaredFields){
            System.out.println("本类中所有属性=" + declaredField.getName());
        }



        //getMethods：获取所有public修饰的方法，包含本类以及父类的
        Method[ ] methods = personcls.getMethods();
        for (Method method : methods) {
            System.out.println("本类以及父类的方法=" +method .getName());
        }



        //getDeclaredMethods：获取本类中所有方法
        Method[] declaredMethods = personcls.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods){
            System.out.println("本类中所有方法=" + declaredMethod.getName());
        }


        //getConstructors：获取所有public修饰的构造器，包含本类
        Constructor<?>[] constructors = personcls.getConstructors();
        for (Constructor<?> constructor : constructors){
            System.out.println("本类的构造器=" +constructor.getName());
        }


        //getDeclaredConstructors：获取本类中所有构造器
        Constructor<?>[] declaredConstructors = personcls.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors){
            System.out.println("本类中所有构造器="+ declaredConstructor.getName());//这里老师只是输出名
        }



        //getPackage：以getPackage形式返回包信息
        System.out.println(personcls.getPackage());//com.hspedu.reflection


        //getSuperClass：以Class形式返回父类信息
        Class<?>superclass = personcls.getSuperclass();
        System.out.println("父类的class对象=" +superclass);//



        //getInterfaces：以Class[]形式返回接口信息
        Class<?>[] interfaces = personcls.getInterfaces();
        for (Class<?>anInterface : interfaces) {
            System.out.println("接口信息=" +anInterface);
        }

        //getAnnotations：以Annotation[]形式返回注解信息
        Annotation[] annotations = personcls.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("注解信息=" +annotation);//注解
        }


    }
}

class A {
    public String hobby;
    public void hi() {
    }
    public A() {}
}

interface IA {

}
interface IB {

}

@Deprecated
class Person extends A implements IA , IB{
    //属性
    public String name;
    protected static int age; //4+8 = 12
    String job;
    private double sal;

    //构造器
    public Person() {}
    public Person(String name) {}

    //私有的
    private Person(String name,int age) {
    }


    //方法
    public void m1(String name, int age, double sal) {

    }
    protected String m2(){
        return null;
    }
    void m3(){

    }
    private void m4() {
    }

}
