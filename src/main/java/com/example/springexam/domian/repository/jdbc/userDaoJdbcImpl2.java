package com.example.springexam.domian.repository.jdbc;

import com.example.springexam.domian.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDaoJdbcImpl2")
public class userDaoJdbcImpl2 extends userDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public user selectOne(String userId){
        String sql = "SELECT * FROM m_user WHERE user_id = ?";
        userRowMapper rowMapper = new userRowMapper();
        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    @Override
    public List<user> selectMany(){
        String sql = "SELECT * FROM m_user";
        RowMapper<user> rowMapper = new userRowMapper();
        return jdbc.query(sql, rowMapper);
    }
}
