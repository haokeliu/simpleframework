package org.simpleframework.inject;

import club.haokeliu.controller.frontend.MainPageController;
import club.haokeliu.service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import club.haokeliu.service.combine.impl.HeadLineShopCategoryCombineServiceImpl2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;

import java.io.UnsupportedEncodingException;


public class DependencyInjectorTest {

    @Test
    @DisplayName("依赖注入doIoc")
    public void doIocTest() throws UnsupportedEncodingException {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("club.haokeliu");
        Assertions.assertEquals(true, beanContainer.isLoaded());
        MainPageController mainPageController = (MainPageController)beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, mainPageController instanceof MainPageController);
        Assertions.assertEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIOC();
        Assertions.assertNotEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        Assertions.assertEquals(true, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl);
        Assertions.assertEquals(false, mainPageController.getHeadLineShopCategoryCombineService() instanceof HeadLineShopCategoryCombineServiceImpl2);
    }
}
