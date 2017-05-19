package com.lafite.demo.controller;

import com.google.gson.Gson;
import com.lafite.demo.base.NullTool;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-09 14:36
 **/
@Namespace("/user")
@ParentPackage("json-default")
@Scope("singleton")
@Results({@Result(name = "error", location = "/error/error.jsp"),
        @Result(name = "find_by_id_success", location = "/"),
        @Result(name = "find_all_success", location = "/"),
        @Result(name = "login_success", location = "/"),
        @Result(name = "register_success", location = "/"),
        @Result(name = "full_success", location = "/"),
        @Result(name = "current_success", location = "/"),
        @Result(name = "vaildate_name_success", location = "/")})
public class UserAction implements ServletRequestAware {

    @Resource(name = "userService")
    private IUserService userService;
    private HttpServletRequest request;

    String[] vaildataResult;
    private final Gson gson;

    public UserAction() {
        vaildataResult = "代码异常,登陆成功,用户名不存在,密码错误".split(",");
        gson = new Gson();
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    /**
     * 查询一个用户信息
     *
     * @return
     */
    @Action("findById")
    public String findById() {
        String userId = request.getParameter("userId");
        String result = "find_by_id_success";
        User user = null;
        PrintWriter writer = null;
        try {
            user = this.userService.findById(userId);

            HttpServletResponse response = ServletActionContext.getResponse();
            writer = response.getWriter();
            user = (User) NullTool.dealNull(user);
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
     *
     * @return
     */
    @Action("findAll")
    public String find() {
        String currentPage = request.getParameter("currentPage");
        String result = "find_all_success";
        PrintWriter writer = null;
        try {
            List<User> userList = this.userService.findAll();
            if (userList != null && userList.size() > 0) {
                for (User user : userList) {
                    user = (User) NullTool.dealNull(user);
                }
            }

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

    /**
     * 登陆验证
     * 0 -- 代码异常
     * 1 -- 登陆成功
     * 2 -- 用户名不存在
     * 3 -- 密码错误
     * @return
     * @throws Exception
     */
    @Action("login")
    public String login() {
        String result = "login_success";
        String loginName = request.getParameter("login_name");
        String password = request.getParameter("password");

        PrintWriter writer = null;
        try {
            int code = 0;
            List<User> loginList = this.userService.vaildate(loginName, password);
            if (loginList.size() == 0) {
                code = 2;
            } else if (loginList.get(0).getPassword().equals(password)) {
                code = 1;
                HttpSession session = request.getSession();
                session.setAttribute("login", loginList.get(0));
            } else {
                code = 3;
            }

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(vaildataResult[code]);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 获取当前用户信息
     * @return
     */
    @Action("current_user")
    public String currentUser () {
        String result = "current_success";
        HttpSession session = request.getSession();
        Object user = session.getAttribute("login");
        PrintWriter writer = null;
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            result = "error";
        }
        if (user == null) {
            writer.print("请先登录。");
        } else {
            User current = null;
            try {
                current = (User) NullTool.dealNull(user);
            } catch (Exception e) {
                result = "error";
            }
            writer.print(gson.toJson(current));
        }
        writer.flush();
        writer.close();
        return result;
    }

    /**
     * 用户注册/更新
     * @return
     */
    @Action("register")
    public String register() {
        String result = "register_success";
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user = user == null ? new User() : user;
        String loginName = request.getParameter("login_name");
        String password = request.getParameter("password");
        user.setName(request.getParameter("name"));
        user.setBirth(request.getParameter("birth"));
        user.setQq(request.getParameter("qq"));
        user.setPhone(request.getParameter("phone"));
        user.setSex(request.getParameter("sex"));
        user.setType(request.getParameter("type"));
        user.setLoginName(loginName);
        user.setPassword(password);

        PrintWriter writer = null;
        try {
            this.userService.register(user);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson("注册成功"));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 用户名重复性校验
     * @return
     */
    @Action("vaildate_name")
    public String vaildateLoginName() {
        String result = "vaildate_name_success";
        String userMame = request.getParameter("login_name");
        PrintWriter writer = null;
        try {
            int countName = this.userService.vaildateUserName(userMame);
            HttpServletResponse response = ServletActionContext.getResponse();
//            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(countName);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }
}
