package cau.capstone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("**") // test2 /** -> ** 로 변경
      .allowCredentials(true)
      .allowedOriginPatterns("*")
//      .allowedOrigins("*")
      .allowedHeaders("*")
      .allowedMethods("*");
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/templates/", "classpath:/static")
            .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
  }
}
