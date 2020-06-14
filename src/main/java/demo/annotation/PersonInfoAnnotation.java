package demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfoAnnotation {
    // name
    public String name();
    // age
    public int age() default 18;
    // gender
    public String gender() default "male";
    // language
    public String[] language();
}
