package com.lafite.demo.controller;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;

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
        @Result(name = "find_by_id_success", location = "/"),
        @Result(name = "find_all_success", location = "/"),
        @Result(name = "login_success", location = "/"),
        @Result(name = "register_success", location = "/"),
        @Result(name = "full_success", location = "/"),
        @Result(name = "vaildate_name_success", location = "/")})
public class DailyAction {
}
