package com.example.springexam.domian.repository.jdbc;


import com.example.springexam.domian.model.user;
import com.example.springexam.domian.repository.userDao;
import com.example.springexam.domian.service.restService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class restServiceImpl implements restService {

    @Autowired
    @Qualifier("userDaoJdbcImpl")
    userDao dao;

    @Override
    public boolean insert(user user) {
        int result = dao.insertOne(user);

        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public user selectOne(String userId) {
        return dao.selectOne(userId);
    }

    @Override
    public List<user> selectMany() {
        return dao.selectMany();
    }

    @Override
    public boolean update(user user) {
        int result = dao.updateOne(user);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean delete(String userId) {
        int result = dao.deleteOne(userId);
        if (result == 0) {
            return false;
        } else {
            return true;
        }

    }
}
