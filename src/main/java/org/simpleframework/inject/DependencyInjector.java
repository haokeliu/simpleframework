package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {
    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    public DependencyInjector(){
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 执行IOC
     * 1.遍历Bean容器的所有Class对象
     * 2.遍历Class对象中的所有成员变量
     * 3.找出被Autowired标记的成员变量
     * 4.获取这些成员变量的类型
     * 5.获取这些成员变量的类型在容器里对应的实例
     * 6.通过反射将这些对应的成员变量实例注入到成员变量所在类的实例里
     */
    public void doIOC(){
        //1.遍历Bean容器的所有Class对象
        if (ValidationUtil.isEmpty(beanContainer.getClasses())){
            log.warn("empty classSet in BeanContainer");
            return;
        }
        for (Class<?> clazz : beanContainer.getClasses()){
            //2.遍历Class对象中的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)){
                continue;
            }
            for (Field field : fields){
                //3.找出被Autowired标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4.获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //5.获取这些成员变量的类型在容器里对应的实例
                    Object fieldValue = getFieldInstance(fieldClass, autowiredValue);
                    if (fieldValue == null){
                        throw new RuntimeException("unable to inject relevant type, target fieldClass is " + fieldClass + " target autowiredValue is" + autowiredValue);
                    }else {
                        //6.通过反射将这些对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, fieldValue, true);
                    }

                }
            }
        }

    }

    /**
     * 根据Class在beanContainer里获取其实例或者实现类
     *
     * @param fieldClass 目标Class对象
     * @param autowiredValue
     * @return 实例/实现类
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue != null){
            return fieldValue;
        }else {
            Class<?> implementedClass = getImplementClass(fieldClass, autowiredValue);
            if (implementedClass != null){
                return beanContainer.getBean(implementedClass);
            }else {
                return null;
            }
        }
    }

    /**
     * 获取接口的实现类
     *
     * @param fieldClass 目标接口类
     * @param autowiredValue
     * @return 接口的实现类
     */
    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        // 如果存在多个实现类 Qualifier指定使用哪个具体实现类
        if (!ValidationUtil.isEmpty(classSet)){
            if (ValidationUtil.isEmpty(autowiredValue)){
                if (classSet.size() == 1){
                    return classSet.iterator().next();
                }else {
                    // 如果针对目标Class有多个实现类，那么抛出异常交给用户处理
                    throw new RuntimeException("multiple implemented classes for " + fieldClass.getName() + "please set @Autowired value to pick one");
                }
            }else {
                for (Class<?> clazz : classSet){
                    // 当autowiredValue == 类名时 返回当前class
                    if (autowiredValue.equals(clazz.getSimpleName())){
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
