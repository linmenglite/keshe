package com.demo.main.listener;

import com.demo.main.utils.AuthUtil;
import com.demo.main.utils.CommonUtil;
import com.demo.main.utils.Identity;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.stream.Collectors;

@WebListener
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String contextPath = sce.getServletContext().getContextPath();
        AuthUtil.setContextPath(contextPath);
        CommonUtil.setContextPath(contextPath);

        List<String[]> userPermissions = CommonUtil.userPermissions.stream()
                .peek(item -> item[1] = CommonUtil.getContextPath() + item[1])
                .collect(Collectors.toList());

        List<String[]> adminPermissions = CommonUtil.adminPermissions.stream()
                .peek(item -> item[1] = CommonUtil.getContextPath() + item[1])
                .collect(Collectors.toList());

        CommonUtil.permissions.put(Identity.user, userPermissions);
        CommonUtil.permissions.put(Identity.admin, adminPermissions);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}