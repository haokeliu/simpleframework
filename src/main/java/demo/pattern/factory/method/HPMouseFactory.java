package demo.pattern.factory.method;

import demo.pattern.factory.entity.HPMouse;
import demo.pattern.factory.entity.Mouse;

public class HPMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new HPMouse();
    }
}
