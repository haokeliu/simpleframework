package demo.pattern.singleton;

/**
 * 饿汉-单例模式
 * 在类初始化时就创建实例，有需要调用 getStarvingSingleton()时返回实例
 * 在初始化阶段创建实例，所以线程安全
 * 类加载的方式是按需加载，且只加载一次。因此，在上述单例类被加载时，
 * 就会实例化一个对象并交给自己的引用，供系统使用。
 * 换句话说，在线程访问单例对象之前就已经创建好了。
 * 再加上，由于一个类在整个生命周期中只会被加载一次，因此该单例类只会创建一个实例，
 * 也就是说，线程每次都只能也必定只可以拿到这个唯一的对象。
 * 因此就说，饿汉式单例天生就是线程安全的。
 * 优点:
 *      类加载时完成初始化,获取对象的速度较快.
 * 缺点:
 *      类加载较慢.
 */
public class StarvingSingleton {
    /**
     * final 修饰的变量要求必须有初始值且不能被修改
     * private 修饰让变量不被暴露
     * static 修饰变量才能被静态方法当作返回值
     *
     */
    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();

    private StarvingSingleton(){ }

    /**
     * 静态私有方法—在类加载时已经初始化
     * @return StarvingSingleton返回单例实例
     */
    public static StarvingSingleton getStarvingSingleton(){
        return starvingSingleton;
    }

    public static void main(String[] args) {
        System.out.println(StarvingSingleton.getStarvingSingleton());
        System.out.println(StarvingSingleton.getStarvingSingleton());
    }
}
