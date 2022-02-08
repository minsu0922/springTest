package com.example.springexam.domian.repository.jdbc;

import com.example.springexam.domian.model.user;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userRowMapper implements RowMapper<user> {

    @Override
    public user mapRow(ResultSet rs, int rowNum) throws SQLException{
        user user = new user();

        user.setUserId(rs.getString("user_id"));
        user.setPassword(rs.getString("password"));
        user.setUserName(rs.getString("user_name"));
        user.setBirthday(rs.getDate("birthday"));
        user.setAge(rs.getInt("age"));
        user.setMarriage(rs.getBoolean("marriage"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
