package demo.pattern.factory.simple;

import demo.pattern.factory.entity.DellMouse;
import demo.pattern.factory.entity.HPMouse;
import demo.pattern.factory.entity.Mouse;

public class MouseFactory {
    public static Mouse createMouse(int type){
        // 隐藏对象创建过程
        /**
         * 简单工厂模式:隐藏对象创建过程
         * 适用场景：
         *        1. 需要创建的对象较少
         *        2. 客户端不关心对象的创建过程
         * 优缺点：
         *       优点：可以对创建对象进行"加工"，对客户端隐藏细节
         *       缺点：因创建逻辑复杂、创建对象过多将导致代码臃肿
         *            增加、删除子类将违反开闭原则
         *      开闭原则：一个软件实体，应对扩展开发，对修改关闭
         *             应该通过扩展来实现变化，而不是通过修改已有代码来实现变化。
         * **/
        switch (type){
            case 0: return new DellMouse();
            case 1: return new HPMouse();
            default: return new DellMouse();
        }
    }

    public static void main(String[] args) {
        Mouse mouse = MouseFactory.createMouse(1);
        mouse.sayHi();
    }
}
