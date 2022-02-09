package com.example.springexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {

    // 데이터소스
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 사용자ID와 비밀번호를 얻는 SQL문
    private static final String USER_SQL = "SELECT"
            + "    user_id,"
            + "    password,"
            + "    true"
            + " FROM"
            + "    m_user"
            + " WHERE"
            + "    user_id = ?";

    // 사용자 역할을 얻는 SQL문
    private static final String ROLE_SQL = "SELECT"
            + "    user_id,"
            + "    role"
            + " FROM"
            + "    m_user"
            + " WHERE"
            + "    user_id = ?";

    @Override
    public void configure(WebSecurity web) throws Exception {

        // 정적인 리소스에 액세스하는데 보안을 적용하지 않음
        web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 로그인 불필요한 페이지 설정
        http
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll() // webjars에 대한 액세스 권한
                .antMatchers("/css/**").permitAll() // css에 대한 액세스 권한
                .antMatchers("/login").permitAll() // 로그인페이지는 직접링크 가능
                .antMatchers("/signup").permitAll() // 사용자등록 화면은 직접링크 가능
                .antMatchers("/rest/**").permitAll() // REST 직접 링크 가능
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") // Admin 사용자 허용
                .anyRequest().authenticated(); // 그외 직접링크 금지!

        // 로그인 처리
        http
                .formLogin()
                .loginProcessingUrl("/login") // 로그인처리경로
                .loginPage("/login") // 로그인페이지 설정
                .failureUrl("/login") // 로그인 실패시 경로
                .usernameParameter("userId") //로그인 페이지의 사용자 ID
                .passwordParameter("password") // 로그인페이지 비밀번호
                .defaultSuccessUrl("/home", true); //로그인 성공후 전환대상

        // 로그아웃
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        // CSRF를 사용하지 않도록 설정하는 URL설정
        RequestMatcher csrfMatcher = new restMatcher("/rest/**");
        // REST 전용 CSRF 대책을 비활성화로 설정
        http.csrf().requireCsrfProtectionMatcher(csrfMatcher);
        //http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 로그인 처리시 사용자정보를 데이터베이스로부터 얻기
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_SQL)
                .authoritiesByUsernameQuery(ROLE_SQL)
                .passwordEncoder(passwordEncoder());
    }
}
