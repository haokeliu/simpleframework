package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.util.*;
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

    /**
     * 添加一个class对象及其Bean实例
     *
     * @Param clazz Class对象
     * @Param bean Bean实例
     *
     * @Return 原有的Bean实例，没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean){
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     *
     * @param clazz Class对象
     *
     * @return 删除的Bean实例,没有则返回Null
     */
    public Object removeBean(Class<?> clazz){
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     *
     * @param clazz CLass对象
     * @return Bean实例
     */
    public Object getBean(Class<?> clazz){
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return Class集合
     */
    public Set<Class<?>> getClasses(){
        return beanMap.keySet();
    }

    /**
     * 获取容器管理的所有Bean对象集合
     *
     * @return Bean集合
     */
    public HashSet<Object> getBeans(){
        return new HashSet<>(beanMap.values());
    }

    /**
     * 根据注解筛选出Bean的Class集合
     *
     * @param annotation 注解
     * @return Class 集合
     */
    public Set< Class<?> > getClassesByAnnotation(Class<? extends Annotation> annotation){
        //1.获取beanMap的所有Class对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2.通过注解筛选出被注解标记的Class对象，并添加到classSet中
        Set< Class<?> > classSet = new HashSet<>();
        for (Class<?> clazz : keySet){
            // 类是否有相关的注解标记
            if (clazz.isAnnotationPresent(annotation)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
     * 通过接口或者父类获取实现类或者子类的Class集合，不包括本身
     *
     * @param interfaceOrClass 接口Class或者父类Class
     * @return Class集合
     */
    public Set< Class<?> > getClassesBySuper(Class<?> interfaceOrClass){
        //1.获取beanMap的所有Class对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)){
            log.warn("nothing in beanMap");
            return null;
        }
        //2.判读KeySet里的元素是否是传入的接口或者类的子类，
        // 如果时，添加到classSet里
        Set< Class<?> > classSet = new HashSet<>();
        for (Class<?> clazz : keySet){
            // 判断KeySet里的元素是否是传入的接口或者类的子类
            // isAssignableFrom : Determines if the class or interface represented by this
            // 确定此表示的类或接口
            // A 是否起源于 B A.isAssignableFrom(B)
            // 可以判断父类或者超类
            // 也可判断非直接继承关系，中间可以隔代 interface/extends
            // 判断自己是自己是返回true，该方法需要超类
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)){
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

}
