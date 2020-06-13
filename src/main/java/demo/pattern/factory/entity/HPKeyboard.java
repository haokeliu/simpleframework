package demo.pattern.factory.entity;

public class HPKeyboard implements Keyboard {
    @Override
    public void sayHello() {
        System.out.println("我是惠普键盘");
    }
}
