package com.demo.main.dao;

import com.demo.main.entity.User;
import com.demo.main.utils.JDBCUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final JDBCUtil jdbcUtil = new JDBCUtil("`user`", "id");
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User selectByUsername(String username) {
        String sql = "select * from `user` where username = ?";

        ResultSet resultSet = JDBCUtil.query(sql, username);
        List<User> users = resultSetToUserList(resultSet);
        return (users.size() > 0) ? users.get(0) : null;
    }

    /**
     * 添加用户
     */
    public void insertOne(User user) {
        String sql = "INSERT INTO `user` (username, `password`, phone) VALUES (?, ?, ?)";
        JDBCUtil.insert(sql, true, user.getUsername(), user.getPassword(), user.getPhone());
    }

    private List<User> resultSetToUserList(ResultSet resultSet) {
        try {
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setPhone(resultSet.getString(4));
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> selectAll() {
        String sql = "select * from `user`";
        ResultSet resultSet = JDBCUtil.query(sql);
        return resultSetToUserList(resultSet);
    }

    public void deleteOne(int id) {
        String sql = "delete from `user` where id = ?";
        JDBCUtil.execute(sql, id);
    }

    public List<User> selectByUsernameLike(String username) {
        String sql = "select * from `user` where username like '%" + username + "%'";
        ResultSet resultSet = JDBCUtil.query(sql);
        return resultSetToUserList(resultSet);
    }

    public User selectOne(int id) {
        String sql = "select * from `user` where `id` = ?";
        ResultSet resultSet = JDBCUtil.query(sql, id);
        return resultSetToUserList(resultSet).get(0);
    }

    public void updateOne(User user) {
        String sql = "update `user` set `username`=?, password=?, phone=? WHERE id=?";
        JDBCUtil.execute(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getId());
    }

    public void updateById(User admin) {
        jdbcUtil.updateSelective(admin);
    }
}
