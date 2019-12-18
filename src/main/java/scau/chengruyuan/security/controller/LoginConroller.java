package scau.chengruyuan.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cry
 * @date 2019/12/8 17:50
 * @version 1.0.0
 **/
@RestController
public class LoginConroller {


    @RequestMapping(value = "/login‐success", produces = "text/plain;charset=UTF-8")
    public String loginSuccess(){
        return " 登录成功";
    }

    @RequestMapping(value = "/login-error", produces = "text/plain;charset=UTF-8")
    public String loginFailed(){
        return " 登录失败";
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = "text/plain; charset=UTF-8")
    public String r1(){
        return getUsername() + " 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = "text/plain; charset=UTF-8")
    public String r2(){
        return getUsername() + " 访问资源2";
    }

    /**
     * 测试资源3
     * @return
     */
    @GetMapping(value = "/r/r3",produces = "text/plain; charset=UTF-8")
    public String r3(){
        return getUsername() + " 访问资源3";
    }

    /**
     * 测试资源4
     * @return
     */
    @PreAuthorize("hasAuthority('p1') and hasAuthority('p2')")
    @GetMapping(value = "/r/r4",produces = "text/plain; charset=UTF-8")
    public String r4(){
        return getUsername() + " 访问资源4";
    }

    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
