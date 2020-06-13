package club.haokeliu.controller.superadmin;

import club.haokeliu.entity.bo.ShopCategory;
import club.haokeliu.entity.dto.Result;
import club.haokeliu.service.solo.ShopCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShopCategoryOperationController {

    private ShopCategoryService shopCategoryService;
    //添加
    public Result<Boolean> addShopCategory(HttpServletRequest req, HttpServletResponse resp){
        //TODO
        return shopCategoryService.addShopCategory(new ShopCategory());
    }
    //删除
    public   Result<Boolean>  removeShopCategory(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.removeShopCategory(1);
    }
    //修改
    public   Result<Boolean>  modifyShopCategory(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.modifyShopCategory(new ShopCategory());
    }
    //查询
    public  Result<ShopCategory>  queryShopCategoryById(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.queryShopCategoryById(1);
    }
    //获取头条列表
    public   Result<List<ShopCategory>>  queryShopCategory(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.queryShopCategory(null,1,100);
    }
}
