package scau.chengruyuan.security.model;

import lombok.Data;

/**
 * @Author chengruyuan
 * @Date 2019/12/17 15:12
 * @Version 1.0.0
 **/
@Data
public class PermissionDTO {
    private String id;
    private String code;
    private String description;
    private String url;
}
