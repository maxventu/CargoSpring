package config.security;


import dao.CustomDaoAuthenticationProvider;
import dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import service.UserService;
import service.security.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userService;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                //.antMatchers("/users/**").hasAnyRole("SYS_ADMIN","ADMIN")
                //.antMatchers("/index/**").hasAnyRole("DRIVER")

                //.antMatchers("/users**").hasAnyRole("SYS_ADMIN","ADMIN")
                //.antMatchers("/users").hasAnyRole("SYS_ADMIN","ADMIN")
                //.antMatchers("/index").permitAll()
                .antMatchers("/**").permitAll()
                //.antMatchers("/resources/app/lib/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/login")
                    .failureUrl("/#?error")
                    .defaultSuccessUrl("/#/default?success")
                    .permitAll()
                /*.and()
                    .logout()
                    .logoutUrl("/#?logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()*/;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder(4));
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        return new CustomDaoAuthenticationProvider();
    }
}
