package org.simpleframework.core;

import club.haokeliu.controller.DispatcherServlet;
import club.haokeliu.controller.frontend.MainPageController;
import club.haokeliu.service.solo.HeadLineService;
import club.haokeliu.service.solo.impl.HeadLineServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Controller;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    public void loadBeansTest(){
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("club.haokeliu");
        Assertions.assertEquals(6,beanContainer.size());
        Assertions.assertEquals(true,beanContainer.isLoaded());
    }

    @DisplayName("根据类获取其实例：getBeanTest")
    @Test
    @Order(2)
    public void getBeanTest(){
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
        DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertEquals(null, dispatcherServlet);
    }

    @DisplayName("根据注解获取其实例：getClassByAnnotationTest")
    @Test
    @Order(3)
    public void getClassByAnnotationTest(){
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(3, beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("根据接口获取其实例：getClassBySuper")
    @Test
    @Order(4)
    public void getClassesBySuperTest(){
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(true, beanContainer.getClassesBySuper(HeadLineService.class).contains(HeadLineServiceImpl.class));

    }
}
