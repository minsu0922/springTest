package com.example.springexam.domian.repository.jdbc;

import com.example.springexam.domian.model.user;
import com.example.springexam.domian.repository.userDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("userDaoNamedJdbcImpl")
public class userDaoNamedJdbcImpl implements userDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @Override
    public int count(){

        String sql = "SELECT COUNT(*) FROM m_user";
        SqlParameterSource params = new MapSqlParameterSource();

        return jdbc.queryForObject(sql, params, Integer.class);
    }

    @Override
    public int insertOne(user user) {
        String sql = "INSERT INTO m_user(user_id,"
                        + " password,"
                        + " user_name,"
                        + " birthday,"
                        + " age,"
                        + " marriage,"
                        + " role)"
                        + " VALUES(:userId,"
                        + " :password,"
                        + " :userName,"
                        + " :birthday,"
                        + " :age,"
                        + " :marriage,"
                        + " :role)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", user.getUserId())
                .addValue("userName", user.getUserName())
                .addValue("birthday", user.getBirthday())
                .addValue("age", user.getAge())
                .addValue("password", user.getPassword())
                .addValue("marriage", user.isMarriage())
                .addValue("role", user.getRole());
        return jdbc.update(sql, params);
    }
    @Override
    public user selectOne(String userId){
        String sql = "SELECT * FROM m_user WHERE user_id = :userId";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);

        Map<String, Object> map = jdbc.queryForMap(sql, params);

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
    public List<user> selectMany() {

        String sql = "SELECT * FROM m_user";

        SqlParameterSource params = new MapSqlParameterSource();

        List<Map<String, Object>> getList = jdbc.queryForList(sql, params);
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
    public int updateOne(user user) {
        String sql = "UPDATE m_user"
                        + " SET"
                        + " password = :password,"
                        + " user_name = :userName,"
                        + " birthday = :birthday,"
                        + " age = :age,"
                        + " marriage = :marriage"
                        + " WHERE user_id= :userId";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", user.getUserId())
                .addValue("userName", user.getUserName())
                .addValue("birthday", user.getBirthday())
                .addValue("age", user.getAge())
                .addValue("password", user.getPassword())
                .addValue("marriage", user.isMarriage())
                .addValue("role", user.getRole());

        return jdbc.update(sql, params);
    }
    @Override
    public int deleteOne(String userId) {

        String sql = "DELETE FROM m_user WHERE user_id = :user_Id";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);
        int rowNumber = jdbc.update(sql, params);
        return rowNumber;
    }

    @Override
    public void userCsvout() {

        String sql = "SELECT * FROM m_user";
        userRowCallbackHandler handler = new userRowCallbackHandler();
        jdbc.query(sql, handler);
    }
}
