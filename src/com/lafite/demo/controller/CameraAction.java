package com.lafite.demo.controller;

import com.google.gson.Gson;
import com.lafite.demo.base.NullTool;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author LafiteHao
 * @create 2017-05-11 15:37
 **/
@Namespace("/camera")
@ParentPackage("json-default")
@Scope("singleton")
@Results({@Result(name = "error", location = "error/error.jsp"),
        @Result(name = "findInfo_success", location = "/"),
        @Result(name = "findByName_success", location = "/"),
        @Result(name = "findAll_success", location = "/"),
        @Result(name = "remove_success", location = "/"),
        @Result(name = "save_success", location = "/")})
public class CameraAction implements ServletRequestAware {
    @Resource(name = "cameraService")
    private ICameraService cameraService;
    private HttpServletRequest request;
    private final Gson gson;

    public CameraAction() {
        gson = new Gson();
    }

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
        try {
            Camera camera = this.cameraService.findById(Integer.parseInt(id));
            if (camera != null) {
                camera = (Camera) NullTool.dealNull(camera);
            }
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
     * 查看全部摄像头
     * @return
     */
    @Action("findAll")
    public String findAll () {
        String result = "findAll_success";
        PrintWriter writer = null;
        try {
            List<Camera> cameraList = this.cameraService.findAll();
            if (cameraList != null && cameraList.size() > 0) {
                for (Camera camera : cameraList) {
                    camera = (Camera) NullTool.dealNull(camera);
                }
            }
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
    @Action("findByName")
    public String findByName () {
        String result = "findByName_success";
        String name = request.getParameter("name");
        PrintWriter writer = null;
        try {
            List<Camera> cameraList = this.cameraService.findByName(name);
            if (cameraList != null && cameraList.size() > 0) {
                for (Camera camera : cameraList) {
                    camera = (Camera) NullTool.dealNull(camera);
                }
            }
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
        PrintWriter writer = null;
        try {
            this.cameraService.delete(Integer.parseInt(id));
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
     * 保存摄像头信息
     * @return
     */
    @Action("save")
    public String save () {
        String result = "save_success";
        Camera camera = new Camera();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM hh:mm:ss");
        String id = request.getParameter("id");
        if (id != null) {
            camera.setId(Integer.parseInt(id    ));
        }
        camera.setName(request.getParameter("name"));
        camera.setPlace(request.getParameter("place"));
        camera.setUrl(request.getParameter("url"));
        camera.setCode(request.getParameter("code"));
        PrintWriter writer = null;
        try {
            camera.setTime(new Date(new java.util.Date().getTime()));
            this.cameraService.save(camera);
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
}
