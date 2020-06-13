package club.haokeliu.service.combine;

import club.haokeliu.entity.dto.MainPageInfoDTO;
import club.haokeliu.entity.dto.Result;

public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
