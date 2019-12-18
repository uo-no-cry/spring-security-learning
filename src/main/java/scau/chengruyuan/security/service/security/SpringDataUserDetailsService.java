package scau.chengruyuan.security.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scau.chengruyuan.security.dao.UserDao;
import scau.chengruyuan.security.model.UserDTO;

import java.util.List;

/**
 * UserDetailsService 负责获取真实用户信息
 * @Author chengruyuan
 * @Date 2019/12/17 11:12
 * @Version 1.0.0
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 登录账号
        System.out.println("username: " + username);
        // 根据账号查询数据库
        UserDTO user = userDao.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 查询用户权限
        List<String> permissions = userDao.findPermissionsByUserId(user.getId());
        String[] perArray = new String[permissions.size()];
        permissions.toArray(perArray);

        UserDetails userDetails = User.withUsername(user.getUsername())
            .password(user.getPassword()).authorities(perArray).build();
        return userDetails;
    }
}
