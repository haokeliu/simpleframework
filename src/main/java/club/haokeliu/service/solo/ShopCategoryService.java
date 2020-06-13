package club.haokeliu.service.solo;

import club.haokeliu.entity.bo.ShopCategory;
import club.haokeliu.entity.dto.Result;

import java.util.List;

public interface ShopCategoryService {

    Result<Boolean> addShopCategory(ShopCategory shopCategory);
    Result<Boolean> removeShopCategory(int shopCategoryId);
    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
    Result<ShopCategory> queryShopCategoryById(int shopCategory);
    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition,int pageIndex,int pageSize);
}
