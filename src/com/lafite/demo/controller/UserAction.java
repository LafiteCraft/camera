package com.lafite.demo.controller;

import com.google.gson.Gson;
import com.lafite.demo.entity.Login;
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
@Results({@Result(name = "error", location = "error/error.jsp"),
        @Result(name = "find_by_id_success", location = "/"),
        @Result(name = "find_all_success", location = "/"),
        @Result(name = "login_success", location = "/"),
        @Result(name = "register_success", location = "/"),
        @Result(name = "full_success", location = "/"),
        @Result(name = "vaildate_name_success", location = "/")})
public class UserAction implements ServletRequestAware {

    @Resource(name = "userService")
    private IUserService userService;
    private HttpServletRequest request;

    String[] vaildataResult;

    public UserAction() {
        vaildataResult = "代码异常,登陆成功,用户名不存在,密码错误".split(",");
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
     *
     * @return
     */
    @Action("findAll")
    public String find() {
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
        Gson gson = new Gson();
        String result = "login_success";
        String loginName = request.getParameter("login_name");
        String password = request.getParameter("password");

        PrintWriter writer = null;
        try {
            int code = 0;
            List<Login> loginList = this.userService.vaildate(loginName, password);
            if (loginList.size() == 0) {
                code = 2;
            } else if (loginList.get(0).getPassword().equals(password)) {
                code = 1;
                HttpSession session = request.getSession();
                Login login = new Login();
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
     * 用户注册
     * @return
     */
    @Action("register")
    public String register() {
        String result = "register_success";
        String loginName = request.getParameter("login_name");
        String password = request.getParameter("password");
        Login login = new Login();
        login.setLoginName(loginName);
        login.setPassword(password);
        Gson gson = new Gson();
        PrintWriter writer = null;
        try {
            this.userService.register(login);
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
        String login_name = request.getParameter("login_name");
        PrintWriter writer = null;
        try {
            int countName = this.userService.vaildateLoginName(login_name);
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

    /**
     * 完善用户信息，其实也就是写入user表一些数据
     * @return
     */
    @Action("full_info")
    public String fullInfo () {
        String result = "full_success";
        String loginId = request.getParameter("login_id");
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String sex = request.getParameter("sex");
        String qq = request.getParameter("qq");
        String phone = request.getParameter("phone");

        Login login = new Login();
        User user = new User();
        user.setName(name);
        user.setBirth(birth);
        user.setSex(sex);
        user.setQq(qq);
        user.setPhone(phone);
        login.setUser(user);

        try {
            this.userService.fullInfo(login);
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }
}
