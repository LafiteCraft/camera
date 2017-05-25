package com.lafite.demo.controller;

import com.google.gson.Gson;
import com.lafite.demo.base.NullTool;
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
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
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
@Results({@Result(name = "error", location = "error/error.jsp"),
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
    private final Gson gson;

    public DailyAction() {
        gson = new Gson();
    }

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
        String jsonResult = "";
        try {
            Daily daily = this.dailyService.findById(id);
            if (daily != null) {
                //报送类型
                daily.setType(IDailyService.types[Integer.parseInt(daily.getType())]);
                daily = (Daily) NullTool.dealNull(daily);
                jsonResult = gson.toJson(daily);
            }
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(jsonResult);
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
        String jsonResult = "";
        String type = request.getParameter("type");
        List<Daily> dailyList = null;
        try {
            if (type == null) {
                dailyList = this.dailyService.findAll();
            } else {
                dailyList = this.dailyService.findByType(type);
            }
            jsonResult = getString(jsonResult, dailyList);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(jsonResult);
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
        String jsonResult = "";
        try {
            List<Daily> dailyList = this.dailyService.findByTitle(title);
            jsonResult = getString(jsonResult, dailyList);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(jsonResult);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    private String getString(String jsonResult, List<Daily> dailyList) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if(dailyList != null && dailyList.size() > 0) {
            for (Daily daily : dailyList) {
                String type = daily.getType();
                if (type != null) {
                    daily.setType(IDailyService.types[Integer.parseInt(type)]);
                }
                daily = (Daily) NullTool.dealNull(daily);
            }
            jsonResult = gson.toJson(dailyList);
        }
        return jsonResult;
    }

    /**
     * 删除日报，不好意思，我实在懒得做逻辑删除。
     * @return
     */
    @Action("remove")
    public String remove () {
        String result = "remove_success";
        String id = request.getParameter("daily_id");
        PrintWriter writer = null;
        try {
            this.dailyService.remove(Integer.parseInt(id));
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print("删除成功");
            writer.flush();
            writer.close();

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
        User user = (User) session.getAttribute("login");
        String id = request.getParameter("id");
        if (id != null) {
            daily.setId(Integer.parseInt(id));
        }
        String type = request.getParameter("type");
        daily.setType(type != null ? type : "0");
        daily.setContent(request.getParameter("content"));
        daily.setTime(new Date(new java.util.Date().getTime()));
        daily.setUserByPersonId(user);
        daily.setPersonId(user.getId());
        daily.setPersonName(user.getName());
        daily.setTitle(request.getParameter("title"));
        PrintWriter writer = null;
        try {
            this.dailyService.save(daily);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print("保存成功");
            writer.flush();
            writer.close();
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
        User user = (User) session.getAttribute("login");

        PrintWriter writer = null;
        try {
            Daily daily = this.dailyService.findById(id);
            daily.setId(Integer.parseInt(id));
            daily.setFeedback(feedback);
            daily.setUserByInquirerId(user);
            daily.setInquirerName(user.getName());
            daily.setIsRefer("1");
            this.dailyService.save(daily);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print("添加日报反馈成功");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }

        return result;
    }
}
