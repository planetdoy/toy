package run.freshr.common.config;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web mvc config.
 *
 * @author FreshR
 * @apiNote Cross-Origin Resource Sharing 설정
 * @since 2022. 12. 23. 오후 3:42:48
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  /**
   * Add cors mappings.
   *
   * @param registry registry
   * @apiNote 모든 요청에 대해 CORS 허용
   * @author FreshR
   * @since 2022. 12. 23. 오후 3:42:48
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods(GET.name(), POST.name(), PUT.name(), DELETE.name(), OPTIONS.name());
  }

}