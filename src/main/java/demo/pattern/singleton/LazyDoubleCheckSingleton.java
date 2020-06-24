package demo.pattern.singleton;

/**
 * 懒汉模式：在被客户端首次调用时才创建唯一实例
 * 加入双重检查锁机制的懒汉模式能确保线程安全
 */
public class LazyDoubleCheckSingleton {

    /**
     * 不是在类加载时初始化，而是在需要时初始化getInstance()
     * final 和 volatile不兼容
     */
    private volatile static LazyDoubleCheckSingleton instance;

    /**
     *  私有的无参构造函数
     */
    private LazyDoubleCheckSingleton(){}

    private static LazyDoubleCheckSingleton getInstance(){

        // 第一次检查
        if (instance == null) {

            /**
             * 同步
             * 如果两个线程同时进入 instance == null 的代码块，就需要使用
             * synchronized关键字，让对象的创建过程串行化执行
             *
             * LazyDoubleCheckSingleton.class对象有且仅有一个，所以上锁后
             * 只有一个线程做创建工作
             */
            synchronized (LazyDoubleCheckSingleton.class){
                /**
                 * 上接刚才情况，第一个线程进入并完成对象创建，
                 * 第二个对象获取对象锁后再判断当前instance是否为null
                 */
                if (instance == null) {
                    /**
                     * 顺序可能颠倒，加入volatile关键字可禁止指令重排
                     * memory = allocate(); 1.分配对象内存空间
                     * instance(memory);    2.初始化对象
                     * instance = memory    3.设置instance指向刚分配的内存地址，次是instance != null
                     */
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
