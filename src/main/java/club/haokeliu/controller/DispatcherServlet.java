package club.haokeliu.controller;

import club.haokeliu.controller.frontend.MainPageController;
import club.haokeliu.controller.superadmin.HeadLineOperationController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
*
* 拦截所有请求
* 解析请求
* 派发给对应的Controller里的方法进行处理
*
* */
//拦截所有请求
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("request path os" + req.getServletPath());
        System.out.println("request method is :" + req.getMethod());
        if(req.getServletPath() == "/frontend/getmainpageinfo" && req.getMethod() == "GET"){
            new MainPageController().getMainPageInfo(req, resp);
        }else if (req.getServletPath() == "/superadmin/addheadline" &&req.getMethod() == "POST"){
            new HeadLineOperationController().addHeadLine(req, resp);
        }
    }
}
