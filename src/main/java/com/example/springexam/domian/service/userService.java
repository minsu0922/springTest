package com.example.springexam.domian.service;

import com.example.springexam.domian.model.user;
import com.example.springexam.domian.repository.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Transactional
@Service
public class userService {

    @Autowired
//    @Qualifier("userDaoJdbcImpl3")
//    @Qualifier("userDaoJdbcImpl4")
//    @Qualifier("userDaoNamedJdbcImpl")
//    @Qualifier("userDaoJdbcImpl2")
    @Qualifier("userDaoJdbcImpl")
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

    public void userCsvOut() throws DataAccessException {
        dao.userCsvout();
    }

    public byte[] getFile(String fileName) throws IOException{
        FileSystem fs = FileSystems.getDefault();

        Path p = fs.getPath(fileName);

        byte[] bytes = Files.readAllBytes(p);
        return bytes;
    }
}
