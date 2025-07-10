package com.demo.main.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommonUtil {
    private static String contextPath;

    public static final List<String[]> userPermissions = new ArrayList<>();
    public static final List<String[]> adminPermissions = new ArrayList<>();
    public static final Map<String, List<String[]>> permissions = new ConcurrentHashMap<>();

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String pContextPath) {
        contextPath = pContextPath;
    }

    static {
        // 用户 user
        userPermissions.add(new String[]{"图书查询", "/router?page=book"});
        userPermissions.add(new String[]{"借阅记录", "/router?page=borrowing"});
        userPermissions.add(new String[]{"个人信息", "/router?page=user_profile"});
        // 管理员 admin
        adminPermissions.add(new String[]{"用户管理", "/router?page=admin_user_management"});
        adminPermissions.add(new String[]{"添加用户", "/router?page=admin_add_user"});
        adminPermissions.add(new String[]{"管理员管理", "/router?page=admin_admin_management"});
        adminPermissions.add(new String[]{"管理员添加", "/router?page=admin_admin_add"});
        adminPermissions.add(new String[]{"类别管理", "/router?page=admin_book_type_management"});
        adminPermissions.add(new String[]{"添加类别", "/router?page=admin_book_type_insert"});
        adminPermissions.add(new String[]{"图书管理", "/router?page=admin_book_management"});
        adminPermissions.add(new String[]{"添加图书", "/router?page=admin_add_book"});
        adminPermissions.add(new String[]{"借阅记录", "/router?page=borrowing"});
        adminPermissions.add(new String[]{"个人信息", "/router?page=user_profile"});
    }
}
