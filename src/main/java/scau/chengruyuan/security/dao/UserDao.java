package scau.chengruyuan.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import scau.chengruyuan.security.model.PermissionDTO;
import scau.chengruyuan.security.model.UserDTO;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author chengruyuan
 * @Date 2019/12/17 13:36
 * @Version 1.0.0
 **/
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public UserDTO getUserByUsername(String username) {
        String sql = "SELECT * FROM t_user WHERE username = ?";

        List<UserDTO> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDTO.class));
        if (null == list || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据用户id 查询用户权限
     * @param userId
     * @return
     */
    public List<String> findPermissionsByUserId(String userId) {

        String sql = "SELECT * FROM t_permission WHERE id IN ( \n" +
                "\tSELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
                "\t\tSELECT role_id FROM t_user_role WHERE user_id = ? \n" +
                "\t)\n" +
                ")";

        List<PermissionDTO> list = jdbcTemplate.query(sql, new Object[]{userId},
                new BeanPropertyRowMapper<>(PermissionDTO.class));
        List<String> permissions = new ArrayList();
        list.forEach(c->permissions.add(c.getCode()));
        return permissions;
    }


}
