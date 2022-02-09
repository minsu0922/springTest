package com.example.springexam.domian.repository.jdbc;

import com.example.springexam.domian.model.user;
import com.example.springexam.domian.repository.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("userDaoJdbcImpl")
public class userDaoJdbcImpl implements userDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public int count() throws DataAccessException {

        int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
        return count;
    }

    @Override
    public int insertOne(user user) throws DataAccessException{

        String password = passwordEncoder.encode(user.getPassword());

        int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
                + " password,"
                + " user_name,"
                + " birthday,"
                + " age,"
                + " marriage,"
                + " role)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?)",
                user.getUserId(),
                password,
                user.getUserName(),
                user.getBirthday(),
                user.getAge(),
                user.isMarriage(),
                user.getRole());

        return rowNumber;
    }
    @Override
    public user selectOne(String userId) throws DataAccessException{
        Map<String, Object> map =jdbc.queryForMap("SELECT  * FROM m_user " +
                "WHERE user_id = ?",userId);

        user user = new user();
        user.setUserId((String) map.get("user_id"));
        user.setPassword((String) map.get("password"));
        user.setUserName((String) map.get("user_name"));
        user.setBirthday((Date) map.get("birthday"));
        user.setAge((Integer) map.get("age"));
        user.setMarriage((boolean) map.get("marriage"));
        user.setRole((String) map.get("role"));
        return user;
    }
    @Override
    public List<user> selectMany() throws DataAccessException{

    List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
    List<user> userList = new ArrayList<>();
    for (Map<String, Object> map : getList){
        user user = new user();
        user.setUserId((String) map.get("user_id"));
        user.setPassword((String) map.get("password"));
        user.setUserName((String) map.get("user_name"));
        user.setBirthday((Date) map.get("birthday"));
        user.setAge((Integer) map.get("age"));
        user.setMarriage((boolean) map.get("marriage"));
        user.setRole((String) map.get("role"));
        userList.add(user);
    }
    return userList;
    }
    @Override
    public int updateOne(user user) throws DataAccessException{

        String password = passwordEncoder.encode(user.getPassword());

        int rowNumber = jdbc.update("UPDATE m_user"
                        + " SET"
                        + " password = ?,"
                        + " user_name = ?,"
                        + " birthday = ?,"
                        + " age = ?,"
                        + " marriage = ?"
                        + " WHERE user_id= ?",
                password,
                user.getUserName(),
                user.getBirthday(),
                user.getAge(),
                user.isMarriage(),
                user.getUserId());

//        if(rowNumber>0){
//            throw new DataAccessException("트랜젝션 테스트") {};
//        }

        return rowNumber;
    }
    @Override
    public int deleteOne(String userId) throws DataAccessException{

        int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id =?", userId);
    return rowNumber;
    }

    @Override
    public void userCsvout() throws DataAccessException{

        String sql = "SELECT * FROM m_user";
        userRowCallbackHandler handler = new userRowCallbackHandler();
        jdbc.query(sql, handler);
    }
}
