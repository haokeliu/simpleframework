package demo.pattern.singleton;

/**
 * 饿汉-单例模式
 * 在类初始化时就创建实例，有需要调用 getStarvingSingleton()时返回实例
 */
public class StarvingSingleton {
    private static final StarvingSingleton starvingSingleton = new StarvingSingleton();
    private StarvingSingleton(){ }

    /**
     *
     * @return StarvingSingleton返回单例实例
     */
    public static StarvingSingleton getStarvingSingleton(){
        return starvingSingleton;
    }

    public static void main(String[] args) {
        System.out.println(StarvingSingleton.getStarvingSingleton());
    }
}
