package demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorCollector {
    public static void main(String[] args) throws ClassNotFoundException,NoSuchMethodException,IllegalAccessException,InvocationTargetException,InstantiationException{
        Class clazz = Class.forName("demo.reflect.ReflectTarget");
        // 1。获取所有的共有构造方法
        System.out.println("获取所有的共有构造方法");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray){
            System.out.println(c);
        }
        // 2.获取所有构造方法
        System.out.println("获取所有构造方法（包括：共有、私有、受保护、默认）");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray){
            System.out.println(c);
        }
        // 3.获取单个带参数的公有构造方法
        System.out.println("获取单个带参数的公有构造方法");
        Constructor con = clazz.getConstructor(String.class,int.class);
        System.out.println("con = " + con);
        // 4.获取单个私有的构造方法
        System.out.println("获取单个带参数的私有构造方法");
        con = clazz.getDeclaredConstructor(int.class);
        System.out.println("con = " + con);
        System.out.println("调用私有构造方法，创建实例");
        // 授予相应权限，忽略private修饰符
        con.setAccessible(true);
        ReflectTarget reflectTarget =(ReflectTarget) con.newInstance(10);

    }
}
