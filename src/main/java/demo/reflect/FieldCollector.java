package demo.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class FieldCollector {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 获取Class对象
        Class reflectTargetClass = Class.forName("demo.reflect.ReflectTarget");
        // 1.获取所有公有字段
        System.out.println("获取公有字段");
        Field[] fields = reflectTargetClass.getFields();
        for (Field f: fields) {
            System.out.println(f);
        }
        // 2.获取所有字段(私有、公有、默认、受保护的)
        System.out.println("获取公有字段(私有、公有、默认、受保护的)");
        fields = reflectTargetClass.getDeclaredFields();
        for (Field f: fields) {
            System.out.println(f);
        }
        // 3.获取单个特定公有的field
        System.out.println("获取单个特定公有的field");
        Field f = reflectTargetClass.getField("name");
        System.out.println("公有的field name ：" + f);
        ReflectTarget reflectTarget = (ReflectTarget) reflectTargetClass.getConstructor().newInstance();
        // 4.给获取到的field赋值
        f.set(reflectTarget,"待反射一号");
        // 5. 验证对应的name
        System.out.println("验证name： " + reflectTarget.name);
        // 6.获取单个私有Field
        System.out.println("获取单个私有Field");
        f = reflectTargetClass.getDeclaredField("targetInfo");
        System.out.println(f);
        // 对私有成员变量需要使用setAccessible(true)
        f.setAccessible(true);
        f.set(reflectTarget,"12345678");
        System.out.println("验证信息" + reflectTarget);
    }
}
