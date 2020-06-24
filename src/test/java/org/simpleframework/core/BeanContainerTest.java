package org.simpleframework.core;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BeanContainerTest {
    private static BeanContainer beanContainer;

    /**
     * @BeforeAll 会让Unit会在所有unit执行前执行一次初始化
     */
    @DisplayName("加载目标类及其实例到BeanContainer：LoadBeansTest")
    @BeforeAll
    static void init(){
        beanContainer = beanContainer.getInstance();
    }

    @SneakyThrows
    @Test
    public void loadBeansTest(){
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("club.haokeliu");
        Assertions.assertEquals(6,beanContainer.size());
        Assertions.assertEquals(true,beanContainer.isLoaded());
    }
}
