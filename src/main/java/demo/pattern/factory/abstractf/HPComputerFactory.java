package demo.pattern.factory.abstractf;

import demo.pattern.factory.entity.HPKeyboard;
import demo.pattern.factory.entity.HPMouse;
import demo.pattern.factory.entity.Keyboard;
import demo.pattern.factory.entity.Mouse;

public class HPComputerFactory implements ComputerFactory {
    @Override
    public Mouse createMouse() {
        return new HPMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new HPKeyboard();
    }
}
