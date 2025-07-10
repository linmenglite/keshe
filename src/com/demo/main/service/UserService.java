package com.demo.main.service;

import com.demo.main.dao.UserDao;
import com.demo.main.entity.User;
import com.demo.main.utils.Result;

import java.util.List;

public class UserService {
    public UserDao userDao = new UserDao();

    public User selectByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    public boolean verifyLogin(User user) {
        String username = user.getUsername(),
                password = user.getPassword();

        User databaseUser = selectByUsername(username);

        if (databaseUser == null) {
            return false;
        }

        return password.equals(databaseUser.getPassword());
    }

    public boolean verifyLogin(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return verifyLogin(user);
    }

    public Result<String> register(String username, String password, String phone) {

        if (selectByUsername(username) != null) {
            return new Result<>(403, "注册失败");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        userDao.insertOne(user);
        return new Result<>(200, "注册成功");
    }

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    public void deleteOne(int id) {
        userDao.deleteOne(id);
    }

    public List<User> selectByUsernameLike(String username) {
        return userDao.selectByUsernameLike(username);
    }

    public User selectOne(int id) {
        return userDao.selectOne(id);
    }

    public void updateOne(User user) {
        userDao.updateOne(user);
    }

    public void updatePasswordById(String password, Integer id) {
        User user = new User();
        user.setPassword(password);
        user.setId(id);
        userDao.updateById(user);
    }
}
