package `in`.kay.novland.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http
            ?.antMatcher("/api/v1/book")
            ?.httpBasic()
            ?.and()
            ?.csrf()
            ?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/api/v1/book")
            ?.authenticated()

    }
}