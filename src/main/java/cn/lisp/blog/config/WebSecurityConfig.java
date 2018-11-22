package cn.lisp.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName: WebSecurityConfig
 * @Description: TODO
 * @author: lisp
 * @date: 2018/11/20 下午 5:54
 * @version: v1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {

            "/user/login/**" };

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().antMatchers("/user/login/**")
                .authenticated().anyRequest().authenticated().and().formLogin();

        http.logout().logoutUrl("/user/logout").addLogoutHandler((request, response, authentication) -> {
        }).logoutSuccessHandler((request, response, authentication) -> responseText(response, "success"));

        http.exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> responseText(response,
                        accessDeniedException.getMessage()))
                .authenticationEntryPoint((request, response, authException) -> responseText(response,
                        authException.getMessage()));


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    private static void responseText(HttpServletResponse response, Object content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String strContent = mapper.writeValueAsString(content);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        byte[] bytes = strContent.getBytes(StandardCharsets.UTF_8);
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
        response.flushBuffer();
    }

}
