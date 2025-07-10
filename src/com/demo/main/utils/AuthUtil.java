package com.demo.main.utils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class AuthUtil {
    private final ServletRequest request;
    private final ServletResponse response;

    private final HttpServletRequest httpRequest;
    private final HttpServletResponse httpResponse;

    private final HttpSession session;

    private static String contextPath;

    // session key
    public static final String USERID = "session-userid", USERNAME = "session-username", ROLE = "session-role";

    public AuthUtil(ServletRequest request, ServletResponse response) {
        this((HttpServletRequest) request, (HttpServletResponse) response);
    }

    public AuthUtil(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
        this.request = httpRequest;
        this.response = httpResponse;
        this.session = httpRequest.getSession();
//        AuthUtil.contextPath = httpRequest.getContextPath();
    }

    public void login(String username, Serializable id, String role) {
        session.setAttribute(USERID, id);
        session.setAttribute(USERNAME, username);
        session.setAttribute(ROLE, role);
    }

    public void logout() {
        session.removeAttribute(USERID);
        session.removeAttribute(USERNAME);
        session.removeAttribute(ROLE);
    }

    public boolean checkLogin() {
        return session.getAttribute(USERID) != null;
    }

    public String getUsername() {
        return (String) session.getAttribute(USERNAME);
    }

    public Serializable getUserid() {
        return (Serializable) session.getAttribute(USERID);
    }

    public String getRole() {
        return (String) session.getAttribute(ROLE);
    }

    public String getContextPath() {
        return httpRequest.getContextPath();
    }

    public String getServletPath() {
        return httpRequest.getServletPath();
    }

    public static void setContextPath(String contextPath) {
        AuthUtil.contextPath = contextPath;
    }
}
