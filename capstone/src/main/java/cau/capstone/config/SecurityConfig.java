package cau.capstone.config;

//import cau.capstone.config.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Transactional(readOnly = true)
@Configuration // IoC에 등록, 메모리에 떠야한다.
@EnableWebSecurity // 해당 파일로 시큐리티를 활성화한다.
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//  @Autowired
//  private CustomOAuth2UserService customOAuth2UserService;

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }



  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeRequests()
//      .antMatchers("/api/ar").authenticated() // AR page 는 로그인한 사용자만 접속 가능 *** ar url 확인하기
//      .antMatchers("/", "/api/user/signup").permitAll()
        .anyRequest().permitAll()
        .and()
      .formLogin()
        .usernameParameter("email")
        .passwordParameter("password")
        .successHandler(new AuthenticationSuccessHandler() {
          @Override
          public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            log.info("[login success]");
//            response.sendRedirect("/api/test");
          }
        })
        .failureHandler(new AuthenticationFailureHandler() {
          @Override
          public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            log.error("로그인 실패");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
          }
        })
        .and()
      .logout()
      .logoutUrl("/logout")
      .permitAll()
      .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
      .and()
      .cors()
        .configurationSource(corsConfigurationSource());
//      .and()
//      .logout()
//     .invalidateHttpSession(true); // HTTP Session 초기화
//     .deleteCookies("JSESSIONID"); // 특정 쿠키 제거


//    http
//      .oauth2Login()
//      .userInfoEndpoint()
//      .userService(customOAuth2UserService);

  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOriginPattern("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    corsConfiguration.addExposedHeader("JSESSIONID");
    corsConfiguration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

    return urlBasedCorsConfigurationSource;
  }
}
