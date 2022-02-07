package com.example.springexam.domian.repository;

import com.example.springexam.domian.model.user;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface userDao {

    public int count() throws DataAccessException;
    public int insertOne(user user) throws DataAccessException;
    public user selectOne(String userId) throws DataAccessException;
    public List<user> selectMany() throws DataAccessException;
    public int updateOne(user user) throws DataAccessException;
    public int deleteOne(String userId) throws DataAccessException;
    public void userCsvout() throws DataAccessException;

}
