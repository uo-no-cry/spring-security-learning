package scau.chengruyuan.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestConroller
 * @Description
 * @Author cry
 * @Date 2019/12/8 17:50
 * @Version 1.0.0
 **/
@RestController
public class LoginConroller {


    @RequestMapping(value = "/login‐success", produces = "text/plain;charset=UTF-8")
    public String loginSuccess(){
        return " 登录成功";
    }

    /**
     * 测试资源1
     * @return
     */

    @GetMapping(value = "/r/r1",produces = "text/plain; charset=UTF-8")
    public String r1(){
        return " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = "text/plain; charset=UTF-8")
    public String r2(){
        return " 访问资源2";
    }
}
