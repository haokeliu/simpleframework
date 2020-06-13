package club.haokeliu.service.combine.impl;

import club.haokeliu.entity.bo.HeadLine;
import club.haokeliu.entity.bo.ShopCategory;
import club.haokeliu.entity.dto.MainPageInfoDTO;
import club.haokeliu.entity.dto.Result;
import club.haokeliu.service.combine.HeadLineShopCategoryCombineService;
import club.haokeliu.service.solo.HeadLineService;
import club.haokeliu.service.solo.ShopCategoryService;

import java.util.List;

public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {
    private HeadLineService headLineService;
    private ShopCategoryService shopCategoryService;
    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        // 1.获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> HeadLineResult = headLineService.queryHeadLine(headLineCondition, 1,4);
        // 2.获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult  =shopCategoryService.queryShopCategory(shopCategoryCondition,1,100);
        // 3.合并两者并返回
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(HeadLineResult,shopCategoryResult);
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
