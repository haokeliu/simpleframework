package club.haokeliu;

import club.haokeliu.entity.bo.HeadLine;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/hello")
public class helloServlet extends HttpServlet {
    @Override
    public void init(){
        System.out.println("初始化Servlet");
    }
    @Override
    public void destroy(){
        System.out.println("destroy Servlet");
    }
    @Override
    protected void service(HttpServletRequest req,HttpServletResponse resq) {
        System.out.println("是我执行了doGet方法");
        try {
            doGet(req, resq);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "我的简易框架";
        req.setAttribute("name",name);
        log.debug("名字是小明");
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);

    }
}