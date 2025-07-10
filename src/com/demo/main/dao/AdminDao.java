package com.demo.main.dao;

import com.demo.main.entity.Admin;
import com.demo.main.utils.JDBCUtil;
import com.demo.main.utils.ResultSetUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao {

    private final JDBCUtil jdbcUtil = new JDBCUtil("`admin`", "id");

    public Admin selectByUsername(String username) {
        String sql = "select * from `admin` where username = ?";

        ResultSet resultSet = JDBCUtil.query(sql, username);

        try {
            if (!resultSet.next()) {
                return null;
            }
            Admin admin = new Admin();
            admin.setId(resultSet.getInt(1));
            admin.setUsername(resultSet.getString(2));
            admin.setPassword(resultSet.getString(3));
            admin.setPhone(resultSet.getString(4));
            admin.setAddress(resultSet.getString(5));
            return admin;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateById(Admin admin) {
        jdbcUtil.updateSelective(admin);
    }

    public List<Admin> selectAllByDesc() {
        return ResultSetUtil.mapToList(jdbcUtil.selectDescById(), Admin.class);
    }

    public void insertOne(Admin entity) {
        jdbcUtil.insertSelective(entity);
    }

    public void deleteById(Integer id) {
        jdbcUtil.deleteById(id);
    }

    public Admin selectOneById(Integer id) {
        return ResultSetUtil.mapToSingle(jdbcUtil.selectById(id), Admin.class);
    }
}
