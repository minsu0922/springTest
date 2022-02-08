package com.example.springexam.domian.repository.jdbc;

import com.example.springexam.domian.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDaoJdbcImpl4")
public class userDaoJdbcImpl4 extends userDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<user> selectMany() {

        String sql = "SELECT * FROM m_user";

        userResultSetExtractor extractor = new userResultSetExtractor();

        return jdbc.query(sql, extractor);

    }
}
