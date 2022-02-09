package com.example.springexam.domian.service;

import com.example.springexam.domian.model.user;

import java.util.List;

public interface restService {

    public boolean insert(user user);

    public user selectOne(String userId);

    public List<user> selectMany();

    public boolean update(user user);

    public boolean delete(String userId);

}
