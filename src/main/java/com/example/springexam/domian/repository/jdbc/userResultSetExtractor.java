package com.example.springexam.domian.repository.jdbc;

import com.example.springexam.domian.model.user;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.net.PortUnreachableException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userResultSetExtractor implements ResultSetExtractor<List<user>> {

    @Override
    public List<user> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<user> userList = new ArrayList<>();
        while (rs.next()){
            user user = new user();
            user.setUserId(rs.getString("user_id"));
            user.setPassword(rs.getString("password"));
            user.setUserName(rs.getString("user_name"));
            user.setBirthday(rs.getDate("birthday"));
            user.setAge(rs.getInt("age"));
            user.setMarriage(rs.getBoolean("marriage"));
            user.setRole(rs.getString("role"));

            userList.add(user);
        }
        if (userList.size()==0){
            throw new EmptyResultDataAccessException(1);
        }
        return userList;
    }
}
