package com.lafite.demo.controller;

import com.google.gson.Gson;
import com.lafite.demo.entity.User;
import com.lafite.demo.service.IUserService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:36
 **/
@Namespace("/user")
@ParentPackage("json-default")
@Scope("singleton")
@Results({@Result(name = "error", location = "view/error.jsp"),@Result(name = "find_by_id_success", location = "/"),@Result(name = "find_all_success", location = "/")})
public class UserAction implements ServletRequestAware {

    @Resource(name = "userService")
    private IUserService userService;
    private HttpServletRequest request;

    String[] vaildataResult;

    public UserAction () {
        vaildataResult = "代码异常,登陆成功,用户名不存在,密码错误".split(",");
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    /**
     * 查询一个用户信息
     * @return
     */
    @Action("findById")
    public String findById () {
        String userId = request.getParameter("userId");
        String result = "find_by_id_success";
        Gson gson = new Gson();
        User user = null;
        PrintWriter writer = null;
        try {
            user = this.userService.findById(userId);

            HttpServletResponse response = ServletActionContext.getResponse();
            writer = response.getWriter();
            writer.print(gson.toJson(user));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 查询多个用户信息
     * @return
     */
    @Action("findAll")
    public String find () {
        String currentPage = request.getParameter("currentPage");
        Gson gson = new Gson();
        String result = "find_all_success";
        PrintWriter writer = null;
        try {
            List<User> userList = this.userService.findAll();

            HttpServletResponse response = ServletActionContext.getResponse();
            writer = response.getWriter();
            writer.print(gson.toJson(userList));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    @Action("login")
    public String login () {
        Gson gson = new Gson();
        String result = "login_success";
        String loginName = request.getParameter("login_name");
        String password = request.getParameter("password");

        PrintWriter writer = null;
        try {
            int code = this.userService.vaildate(loginName, password);
            if (code == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("login_name", loginName);
            }
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson(vaildataResult[code]));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }
}
