package development.bulletinboard.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Атентификация будет через jdbc и базу данных, а базу данных возьмем из dataSource,
     * прописанного в application.properties
     * @param auth аутентификация
     * @throws Exception если что не так
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    /**
     * Настройки доступа по HTTP для всех типов пользователей.
     * @param config - конфигурация HTTP доступа
     * @throws Exception - если что не так
     */
    @Override
    public void configure(HttpSecurity config) throws Exception {
        config
                .authorizeRequests()
                .antMatchers("/details/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/addnew/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/authenticateTheUser").defaultSuccessUrl("/")
                .and().logout().logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
