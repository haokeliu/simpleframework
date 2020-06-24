package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建线程安全，抵御反射及序列化的Bean单例
 */

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     * 加载Bean的注解列表
     * （只能存放注解标签）
     */
    private static final List<Class< ? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class,
                            Service.class, Repository.class);

    /**
     * 存放所有被配置标记的目标对象的Map
     * key : Class
     * Value : Object
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap();

    /**
     * Bean实例的数量
     *
     * @return 数量
     */
    public int size(){
        return beanMap.size();
    }

    /**
     * 获取Bean容器实例
     *
     * @return BeanContainer
     */
    public static BeanContainer getInstance(){
        return ContainerHolder.HOLDER.instance;
    }
    private enum ContainerHolder{
        HOLDER;
        private BeanContainer instance;
        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     * 容器是否以及加载过Bean
     */
    private boolean loaded = false;

    public boolean isLoaded(){
        return loaded;
    }

    /**
     * 扫描加载所有Bean
     *
     * @param basePackage 包名
     */
    public synchronized void loadBeans(String basePackage) throws UnsupportedEncodingException {
        //判断bean容器是否被加载过
        if (isLoaded()){
            log.warn("BeanContainer has been loaded.");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(basePackage);
        if (classSet == null || classSet.isEmpty()){
            log.warn("extract noting from basePackage " + basePackage );
            return;
        }
        for (Class<?> clazz : classSet){
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION){
                // 如果类上面标记了定义的注解
                if (clazz.isAnnotationPresent(annotation)){
                    // 将目标类本身作为Key，目标类的实例作为Value，放入beanMap中
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }
}
