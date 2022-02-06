package com.example.springexam.domian.repository.jdbc;

import com.example.springexam.domian.model.user;
import com.example.springexam.domian.repository.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDaoJdbcImpl")
public class userDaoJdbcImpl implements userDao {

    @Autowired
    JdbcTemplate jdbc;

    public int count() throws DataAccessException {
        return 0;
    }

    public int insertOne(user user) throws DataAccessException{
        return 0;
    }
    public String selectOne(String userId) throws DataAccessException{
        return null;
    }
    public List<user> selectMany() throws DataAccessException{
        return null;
    }
    public int updateOne(user user) throws DataAccessException{
        return 0;
    }
    public int delete(String userId) throws DataAccessException{
        return 0;
    }
    public void userCsvout() throws DataAccessException{
    }
}
