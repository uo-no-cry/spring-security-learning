package scau.chengruyuan.security.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import scau.chengruyuan.security.config.ApplicationConfig;
import scau.chengruyuan.security.config.WebConfig;
import scau.chengruyuan.security.config.WebSecurityConfig;

public class SpringApplicationInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {ApplicationConfig.class, WebSecurityConfig.class}; // 指定rootContext的配置类
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {WebConfig.class}; // 指定servletContext的配置类
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/"};
  }
}
