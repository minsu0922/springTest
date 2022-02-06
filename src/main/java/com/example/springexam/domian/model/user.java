package com.example.springexam.domian.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class user {

    private String userId;
    private String userName;
    private String password;
    private Date birthday;
    private int age;
    private boolean marriage;
    private String role;
}
