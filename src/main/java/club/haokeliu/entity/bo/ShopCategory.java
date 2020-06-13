package club.haokeliu.entity.bo;

import lombok.Data;

import java.util.Date;

@Data
public class ShopCategory {
    private Long shopCategoryId;
    private String ShopCategoryName;
    private String ShopCategoryDesc;
    private String ShopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date lastsEditTime;
    private ShopCategory parent;
}
