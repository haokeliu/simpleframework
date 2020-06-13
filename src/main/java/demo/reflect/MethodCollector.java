package demo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 1.获取对象
        Class reflectTargetClass = Class.forName("demo.reflect.ReflectTarget");
        // 2.获取所有公有方法
        System.out.println("获取所有公有方法，public、父类和Object");
        Method[] methods = reflectTargetClass.getMethods();
        for (Method m : methods){
            System.out.println(m);
        }
        // 3.获取该类的所有方法
        System.out.println("获取所有方法(public、default、protect、private)");
        methods = reflectTargetClass.getDeclaredMethods();
        for (Method m : methods){
            System.out.println(m);
        }
        // 4.获取单个公有方法
        System.out.println("获取公有show方法");
        Method m = reflectTargetClass.getMethod("show1", String.class);
        System.out.println(m);
        ReflectTarget reflectTarget = (ReflectTarget) reflectTargetClass.getConstructor().newInstance();
        System.out.println("执行show1方法");
        m.invoke(reflectTarget, "带反射方法一号");
        // 6.获取私有的成员方法
        System.out.println("获取私有的成员方法");
        m = reflectTargetClass.getDeclaredMethod("show4", int.class);
        // 设置访问权限
        m.setAccessible(true);
        System.out.println(m);
        String result = (String) m.invoke(reflectTarget, 100);
        System.out.println(result);

    }
}
