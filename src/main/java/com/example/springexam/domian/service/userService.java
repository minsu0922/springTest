package com.example.springexam.domian.service;

import com.example.springexam.domian.model.user;
import com.example.springexam.domian.repository.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    userDao dao;

    public boolean insert(user user){

        int rowNumber = dao.insertOne(user);

        boolean result = false;

        if(rowNumber > 0){
            result = true;
        }
        return result;

    }

    public int count(){
        return dao.count();
    }

    public List<user> selectMany() {
        return dao.selectMany();
    }

    public user selectOne(String userId){
        return dao.selectOne(userId);
    }

    public boolean updateOne(user user){
        boolean result = false;
        int rowNumber = dao.updateOne(user);
        if(rowNumber > 0){
            result = true;
        }
        return result;
    }

    public boolean deleteOne(String userId){
        int rowNumber = dao.deleteOne(userId);

        boolean result = false;
        if(rowNumber >0){
            result = true;
        }
        return result;
    }
}
