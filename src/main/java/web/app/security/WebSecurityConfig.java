package web.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import web.app.service.LoginService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    MySimpleUrlAuthenticationSuccessHandler simpleAuthen(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
    @Autowired
    LoginService service;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();
        http.headers()
                .frameOptions().sameOrigin().and()
                .authorizeRequests()
                .antMatchers("/login", "/css/**", "**/js/**", "/4**").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này

                .antMatchers("/users").hasRole("ADMIN")
                .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                    .and()

                .formLogin() // Cho phép người dùng xác thực bằng form login
                    .loginPage("/login")
                    .successHandler(simpleAuthen())
//                    .successForwardUrl("/")
//                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error")
                    .permitAll() // Tất cả đều được truy cập vào địa chỉ này
                    .and()

                .logout() // Cho phép logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/403");

//        http
//                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    public void configure(WebSecurity web) {
    	 web.ignoring().antMatchers("/css/**");
         web.ignoring().antMatchers("/js/**");
         web.ignoring().antMatchers("/images/**");
    }
	
	
}
