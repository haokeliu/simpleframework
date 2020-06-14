package demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface CourseInfoAnnotation {
    // courseName
    public String courseName();
    // courseTag
    public String courseTag();
    // profile
    public String courseProfile();
    //index
    public int courseIndex() default 1001;
}
