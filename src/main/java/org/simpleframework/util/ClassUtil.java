package org.simpleframework.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类的集合
     *
     * @param packageName 包名
     * @return 类集合
     *
     * 获取到类的加载器 : 获取项目发布的实际路径
     * 通过类加载器获取到加载的资源
     * 依据不同的资源类型，采用不同的方式获取资源的集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) throws UnsupportedEncodingException {
        // 1.获取到类的加载器 : 获取项目发布的实际路径
        ClassLoader classLoader = getClassLoader();
        // 2.通过类加载器获取到加载的资源
        URL url = classLoader.getResource(packageName.replace(".","/"));

        if (url == null){
            log.warn("package 里获取不到信息" + packageName);
            return null;
        }
        // 3.依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
        // 过滤出文件类型的资源
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)){
            classSet = new HashSet<Class<?>>();
            //乱码 编码 /D:/Program Files/CodeBase/simpleframework/target/classes/club/haokeliu/entity
            // 我的路径带" "导致转码后成了 %20导致后续路径无法识别
            String encode = URLDecoder.decode(url.getPath(), "UTF-8");
            System.out.println(encode);
            File packageDirectory = new File(encode);
            extractClassFile(classSet, packageDirectory, packageName);
        }
        //TODO 此处可加入针对其他类型资源的处理

        return classSet;
    }

    /**
     * 递归获取目标Package里面的所有class文件（包括子package里的class文件）
     *
     * @param classSet 装载目标类的集合
     * @param packageDirectory 文件或者目录
     * @param packageName 包名
     *
     * @return 类集合
     */
    private static void extractClassFile(Set<Class<?>> classSet, File packageDirectory, String packageName) {
        if (packageDirectory.isDirectory() == false){
            return;
        }
        // 如果是一个文件夹,则调用其isFiles方法获取文件夹下的文件或文件夹
        File[] files = packageDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()){
                    return true;
                } else {
                    // 获取文件的绝对值路径
                    String absoluteFilePath = file.getAbsolutePath();
                    if (absoluteFilePath.endsWith(".class")){
                        // 若是class文件，直接加载
                        addToClassSet(absoluteFilePath);
                    }
                }
                return false;
            }
            // 根据class文件的绝对值路径，获取并生成class对象，并放入classSet中
            private void addToClassSet(String absoluteFilePath) {
                // 1.从class文件的绝对值路径里提取出包含了package的类名
                // File.separator 是文件路径的分隔符，Java语言是跨平台的语言，所有会适配各个平台的分隔符
                absoluteFilePath = absoluteFilePath.replace(File.separator,".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0,className.lastIndexOf("."));
                // 2.通过反射机制获取对应的Class对象并加入到classSet里
                Class<?> targetClass = loadClass(className);
                classSet.add(targetClass);
            }
        });
        if (files != null){
            for (File f : files){
                // foreach 如果遍历空数组会抛出异常 NullPointException
                // 递归调用
                extractClassFile(classSet, f , packageName);
            }
        }
    }

    /**
     * 获取CLass对象
     *
     * @param className class全名 = package + 类全名
     * @return Class
     */
    public static Class<?> loadClass(String className){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("load class error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取ClassLoader
     *
     * @return 当前ClassLoader
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    @SneakyThrows
    public static void main(String[] args) {
        extractPackageClass("club.haokeliu.entity");
    }
}
