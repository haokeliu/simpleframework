package club.haokeliu.entity.dto;

import club.haokeliu.entity.bo.HeadLine;
import club.haokeliu.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
