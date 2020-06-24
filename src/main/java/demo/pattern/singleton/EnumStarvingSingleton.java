package demo.pattern.singleton;

/**
 *使用枚举，反射无法攻破
 *枚举的源码 => 不能通过反射来创建枚举对象
 * 枚举的私有函数无法通过反射来创建
 * 饿汉模式
 * 装备了枚举的饿汉模式能抵御反射和序列化的进攻，满足容器需求
 * 查看 ObjectInputStream 的 readObject 源码 了解枚举单例模式抵御序列化攻击
 */
public class EnumStarvingSingleton {

    private EnumStarvingSingleton(){ }

    public static EnumStarvingSingleton getInstance(){
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder{
       HOLDER;
       private EnumStarvingSingleton instance;

       // 私有枚举类
       ContainerHolder(){
           instance = new EnumStarvingSingleton();
       }
    }
}
