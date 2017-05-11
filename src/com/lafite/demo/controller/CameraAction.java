package com.lafite.demo.controller;

import com.google.gson.Gson;
import com.lafite.demo.entity.Camera;
import com.lafite.demo.service.ICameraService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-11 15:37
 **/
@Namespace("/camera")
@ParentPackage("json-default")
@Scope("singleton")
@Results({@Result(name = "error", location = "view/error.jsp"),
        @Result(name = "findInfo_success", location = "/"),
        @Result(name = "remove_success", location = "/"),
        @Result(name = "save_success", location = "/")})
public class CameraAction implements ServletRequestAware {
    @Resource(name = "cameraService")
    private ICameraService cameraService;
    private HttpServletRequest request;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    /**
     * 查看单个摄像头
     * @return
     */
    @Action("findInfo")
    public String findInfo () {
        String result = "findInfo_success";
        String id = request.getParameter("camera_id");
        PrintWriter writer = null;
        Gson gson = new Gson();
        try {
            Camera camera = this.cameraService.findById(Integer.parseInt(id));
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson(camera));
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
            List<Camera> cameraList = this.cameraService.findAll();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson(cameraList));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 通过名字查找
     * @return
     */
    @Action("findByTitle")
    public String findByTitle () {
        String result = "findByTitle_success";
        String name = request.getParameter("name");
        PrintWriter writer = null;
        Gson gson = new Gson();
        try {
            List<Camera> cameraList = this.cameraService.findByName(name);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.print(gson.toJson(cameraList));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    /**
     * 删除摄像头，不好意思，我实在懒得做逻辑删除。
     * @return
     */
    @Action("remove")
    public String remove () {
        String result = "remove_success";
        String id = request.getParameter("camera_id");
        try {
            this.cameraService.delete(Integer.parseInt(id));
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
        Camera camera = new Camera();
        camera.setName(request.getParameter("name"));
        camera.setPlace(request.getParameter("place"));
        camera.setUrl(request.getParameter("url"));
        try {
            this.cameraService.save(camera);
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }
}
