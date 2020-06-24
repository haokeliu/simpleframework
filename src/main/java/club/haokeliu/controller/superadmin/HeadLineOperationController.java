package club.haokeliu.controller.superadmin;

import club.haokeliu.entity.bo.HeadLine;
import club.haokeliu.entity.dto.Result;
import club.haokeliu.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HeadLineOperationController {
    private HeadLineService headLineService;

    public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse resp){
        // TODO:参数校验以及请求参数转化
        return headLineService.addHeadLine(new HeadLine());
    }
    public Result<Boolean> removeHeadLine(int headLineId){
        // TODO:参数校验以及请求参数转化
        return headLineService.removeHeadLine(1);
    }
    public Result<Boolean> modifyHeadLine(HeadLine headLine){
        // TODO:参数校验以及请求参数转化
        return headLineService.modifyHeadLine(new HeadLine());
    }
    public Result<HeadLine> queryHeadLineById(int headLineId){
        // TODO:参数校验以及请求参数转化
        return headLineService.queryHeadLineById(1);
    }
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition, int pageIndex, int pageSize){
        // TODO:参数校验以及请求参数转化
        return headLineService.queryHeadLine(null, 1, 100);
    }

}
