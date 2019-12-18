package scau.chengruyuan.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 配置SpringSecurity
 * @Description
 * @Author cry
 * @Date 2019/12/8 17:34
 * @Version 1.0.0
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    //配置用户信息服务
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());   // zhansan 密码 权限
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
                .authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1") // /r/r1 需要有 p1 权限标记
                .antMatchers("/r/r2").hasAuthority("p2")
                .antMatchers("/r/r3").access("hasAuthority('p1') and hasAuthority('p2')")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()
            .and()
                .formLogin()
//                .loginPage("login-view")//指定我们自己的登录页URL,spring security以重定向方式跳转到/login-view
                .loginProcessingUrl("/login")//指定登录处理的URL，也就是用户名、密码表单提交的目的路径
                .successForwardUrl("/login‐success")  // 指定登录成功后的跳转URL
                .failureForwardUrl("/login-error")
                .permitAll()//允许所有用户访问我们的登录页
            .and()
                .logout()
                .logoutUrl("logout")
                .logoutSuccessUrl("/login-view?logout")
        ;
    }
}

