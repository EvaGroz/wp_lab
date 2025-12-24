package mk.ukim.finki.wp.jan2025g2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class is used to configure user login on path '/login' and logout on path '/logout'.
 * After login, the user should be redirected to the '/national-parks' page. After logout,
 * the user should be redirected to the '/' page.
 * The public pages in the application should be '/' and '/national-parks'.
 * All other pages should be visible only for a user with admin role.
 * Furthermore, in the "list.html" template, the 'Edit', 'Delete', 'Add' and 'Close' buttons should only be
 * visible for a user with admin role.
 * <p>
 * For login inMemory users should be used. Their credentials are given below:
 * [{
 * username: "user",
 * password: "user",
 * role: "USER"
 * },
 * <p>
 * {
 * username: "admin",
 * password: "admin",
 * role: "ADMIN"
 * }]
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Remove the implementation of the following method and replace it with your own code to implement the security requests.
     * If you do not wish to implement the security requests, leave this code as it is.
     (20 поени) Потребно е да конфигурирате најава на /login и одјава на /logout. Притоа, јавни се патеките / и /national-parks, додека сите останати страници треба да се видливи само за корисник со улога admin.
     Дополнително, кај list.html копчињата Edit, Delete, Add new national park и Close треба да се видливи само за корисник кој има улога admin. Копчето Close треба да е видливо само дококу паркот е отворен.

     Имплементацијата на оваа функционалност може да ја проверите со тестовите test_security_urls_10pt и test_security_buttons_10pt.*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(httpSecurityHeadersConfigurer ->
                        httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/national-parks").permitAll()
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(form -> form
//                        .loginPage("/login")    // za login prenasoci kon login strantata od nasiot html (view)
                                .permitAll()    // dozvoli na site
//                        .failureUrl("/login?error=BadCredentials")  // ako ne se logira uspesno prenasoci tuka
                                .defaultSuccessUrl("/national-parks", true)   // po uspesno logiranje redirektiraj tuka
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // tuka se izvrsuva odlogiranje
                        .clearAuthentication(true)  // se brise avtentikacija
                        .invalidateHttpSession(true)    // se brise sesija
                        .deleteCookies("JSESSIONID")    // se brisat kolacinja
                        .logoutSuccessUrl("/national-parks")     // prenasocuvanje po uspesno odlogiranje
                );
        ;
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .headers(httpSecurityHeadersConfigurer ->
//                        httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
//                )
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/**")
//                        .permitAll()
//                );
//        return http.build();
//    }
}
