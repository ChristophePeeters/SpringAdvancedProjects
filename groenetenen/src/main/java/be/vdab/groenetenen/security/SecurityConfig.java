package be.vdab.groenetenen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String MANAGER = "manager";
    private static final String HELPDESKMEDEWERKER = "helpdeskmedewerker";
    private static final String MAGAZIJNIER = "magazijnier";
    private static final String USERS_BY_USERNAME =
            "select naam as username, paswoord as password, actief as enabled" +
                    " from gebruikers where naam = ?";
    private static final String AUTHORITIES_BY_USERNAME =
            "select gebruikers.naam as username, rollen.naam as authorities" +
                    " from gebruikers inner join gebruikersrollen" +
                    " on gebruikers.id = gebruikersrollen.gebruikerid" +
                    " inner join rollen" +
                    " on rollen.id = gebruikersrollen.rolid" +
                    " where gebruikers.naam= ?";

    @Bean
    JdbcDaoImpl jdbcDaoImpl(DataSource dataSource) {
        JdbcDaoImpl impl = new JdbcDaoImpl();
        impl.setDataSource(dataSource);
        impl.setUsersByUsernameQuery(USERS_BY_USERNAME);
        impl.setAuthoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME);
        return impl;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").and().logout().logoutSuccessUrl("/")
                .and().authorizeRequests()
                .mvcMatchers("/offertes/toevoegen").hasAuthority(MANAGER)
                .mvcMatchers("/werknemers").hasAnyAuthority(MAGAZIJNIER, HELPDESKMEDEWERKER)
                .mvcMatchers("/", "/login").permitAll()
                .mvcMatchers("/**").authenticated();
    }
}