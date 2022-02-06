package com.example.springexam.domian.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class signupForm {

    @NotBlank(groups = ValiGroup1.class)
    @Email(groups = ValiGroup2.class)
    private String userId;

    @NotBlank(groups = ValiGroup1.class)
    @Length(min = 4, max = 100, groups = ValiGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValiGroup3.class)
    private String userName;

    @NotBlank(groups = ValiGroup1.class)
    private String password;

    @NotNull(groups = ValiGroup1.class)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @Max(value = 20 ,groups = ValiGroup2.class)
    @Min(value = 100, groups = ValiGroup2.class)
    private int age;

    @AssertFalse(groups = ValiGroup2.class)
    // argument로 특정 조건 및 boolean 값을 넘기고 assertTrue인 경우 false일때,
    // assertFalse인경우 true일때,
    // junit 에러를 발생시키며 message를 메시지로 리턴한다.
    private boolean marriage;
}
