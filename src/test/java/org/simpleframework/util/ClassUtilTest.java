package org.simpleframework.util;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ClassUtilTest {

    @SneakyThrows
    @DisplayName("提取目标类方法：extractPackageClassTest") // 标识用例名字
    @Test
    public void extractPackageClassTest(){
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("club.haokeliu.entity");
        System.out.println(classSet);
        Assertions.assertEquals(4,classSet.size());
    }
}
