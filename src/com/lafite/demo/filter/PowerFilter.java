package com.lafite.demo.filter;

import com.lafite.demo.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author LafiteHao
 * @create 2017-05-10 10:10
 **/
public class PowerFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        String debug = request.getParameter("debug");

        if (debug != null && debug.equals("true")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("login");
            user = user == null ? new User() : user;
            String type = user.getType();

            if (path.contains("user")) {
                if (path.contains("user/register.action") || path.contains("user/login.action")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    if (type.equals("2")) {
                        response.sendRedirect("/login.html");
                    } else {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            } else if (path.contains("camera")){
                if (type.equals(0)) {
                    response.sendRedirect("/login.html");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else if (path.contains("daily")) {
                if (type.equals(1)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    response.sendRedirect("/login.html");
                }
            } else {
                response.sendRedirect("/login.html");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
