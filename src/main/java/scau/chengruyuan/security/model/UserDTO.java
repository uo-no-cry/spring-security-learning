package scau.chengruyuan.security.model;

import lombok.Data;

/**
 * @Author chengruyuan
 * @Date 2019/12/17 13:35
 * @Version 1.0.0
 **/
@Data
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
