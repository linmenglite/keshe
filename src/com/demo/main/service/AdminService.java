package com.demo.main.service;

import com.demo.main.dao.AdminDao;
import com.demo.main.entity.Admin;

import java.util.List;

public class AdminService {
    private final AdminDao adminDao = new AdminDao();

    public Admin selectByUsername(String username) {
        return adminDao.selectByUsername(username);
    }

    public boolean verifyLogin(Admin admin) {
        String username = admin.getUsername(),
            password = admin.getPassword();

        Admin databaseAdmin = selectByUsername(username);

        if (databaseAdmin == null) {
            return false;
        }

        return password.equals(databaseAdmin.getPassword());
    }
    public boolean verifyLogin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        return verifyLogin(admin);
    }

    public void updateById(Admin admin) {
        adminDao.updateById(admin);
    }

    public void updatePasswordById(String password, Integer id) {
        Admin admin = new Admin();
        admin.setPassword(password);
        admin.setId(id);
        updateById(admin);
    }

    public List<Admin> findAll() {
        return adminDao.selectAllByDesc();
    }

    public void deleteById(Integer id) {
        adminDao.deleteById(id);
    }

    public void add(Admin entity) {
        adminDao.insertOne(entity);
    }

    public Admin findById(Integer id) {
        return adminDao.selectOneById(id);
    }
}
