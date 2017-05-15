package com.lafite.demo.controller;

import com.google.gson.Gson;
import com.lafite.demo.entity.Daily;
import com.lafite.demo.entity.User;
import com.lafite.demo.service.IDailyService;
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
 * 日报模块
 *
 * @author LafiteHao
 * @create 2017-05-10 17:24
 **/
@Namespace("/daily")
@ParentPackage("json-default")
@Scope("singleton")
@Results({@Result(name = "error", location = "view/error.jsp"),
        @Result(name = "findInfo_success", location = "/"),
        @Result(name = "findAll_success", location = "/"),
        @Result(name = "findByTitle_success", location = "/"),
        @Result(name = "remove_success", location = "/"),
        @Result(name = "refer_success", location = "/"),
        @Result(name = "save_success", location = "/")})
public class DailyAction implements ServletRequestAware {

    @Resource(name = "dailyService")
    private IDailyService dailyService;
    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    /**
     * 查看单条日报
     * @return
     */
    @Action("findInfo")
    public String findInfo () {
        String result = "findInfo_success";
        String id = request.getParameter("daily_id");
        PrintWriter writer = null;
        Gson gson = new Gson();
        try {
            Daily daily = this.dailyService.findById(id);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson(daily));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 查看全部日报
     * @return
     */
    @Action("findAll")
    public String findAll () {
        String result = "findAll_success";
        PrintWriter writer = null;
        Gson gson = new Gson();
        try {
            List<Daily> dailyList = this.dailyService.findAll();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson(dailyList));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 通过标题查找
     * @return
     */
    @Action("findByTitle")
    public String findByTitle () {
        String result = "findByTitle_success";
        String title = request.getParameter("title");
        PrintWriter writer = null;
        Gson gson = new Gson();
        try {
            List<Daily> dailyList = this.dailyService.findByTitle(title);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson(dailyList));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 删除日报，不好意思，我实在懒得做逻辑删除。
     * @return
     */
    @Action("remove")
    public String remove () {
        String result = "remove_success";
        String id = request.getParameter("daily_id");
        try {
            this.dailyService.remove(Integer.parseInt(id));
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 保存日报
     * @return
     */
    @Action("save")
    public String save () {
        String result = "save_success";
        Daily daily = new Daily();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("login   ");
        daily.setContent(request.getParameter("content"));
        daily.setUserByPersonId(user);
        daily.setPersonId(user.getId());
        daily.setPersonName(user.getName());
        daily.setTitle(request.getParameter("title"));
        try {
            this.dailyService.save(daily);
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 添加日报反馈
     * @return
     */
    @Action("refer")
    public String refer () {
        String result = "refer_success";
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String feedback = request.getParameter("feedback");
        User user = (User) session.getAttribute("user");

        Daily daily = new Daily();
        daily.setId(Integer.parseInt(id));
        daily.setFeedback(feedback);
        daily.setUserByInquirerId(user);

        try {
            this.dailyService.save(daily);
        } catch (Exception e) {
            result = "error";
        }

        return result;
    }
}
