package web.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
manager.createUser(
		User.withDefaultPasswordEncoder()
		.username("admin")
		.password("admin").roles("ADMIN").build());
		return manager;
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
        http
                .authorizeRequests()
                    .antMatchers("/login", "/css/**", "**/js/**").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                    .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
                    .and()
                   
                .formLogin() // Cho phép người dùng xác thực bằng form login
                    .defaultSuccessUrl("/")
                    .permitAll() // Tất cả đều được truy cập vào địa chỉ này
                    .and()
                    
                .logout() // Cho phép logout
                    .permitAll()
                    .and().exceptionHandling().accessDeniedPage("/403"); 
        
        http.csrf().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	 web.ignoring().antMatchers("/css/**");
         web.ignoring().antMatchers("/js/**");
         web.ignoring().antMatchers("/images/**");
    }
	
	
}
