package uutiset.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Security joka estää näkemättä kaikkia sivuja kirjautumatta.
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Määritellään, mitkä sivut voi nähdä ja mitkä ei.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/home").permitAll()
                .antMatchers(HttpMethod.GET, "/home/*").permitAll()
                .antMatchers(HttpMethod.GET, "/articles/{articleId}/picture").permitAll()
                .antMatchers(HttpMethod.GET, "/articles/{articleId}").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers("/images/monkeynews.jpg").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().permitAll();
        http.formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll();
    }

    //Luodaan yksi USER tyyppinen käyttäjä, jolla on tunnus ja salasana.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("author").password("author123").roles("USER");
    }
}
