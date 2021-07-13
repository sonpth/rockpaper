package code.resmed.rockpaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import code.resmed.rockpaper.security.Http401UnauthorizedEntryPoint;
import code.resmed.rockpaper.security.RestAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestAuthenticationProvider restAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.authenticationProvider(restAuthenticationProvider)	
			.eraseCredentials(false)
		;
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	Http401UnauthorizedEntryPoint authenticationEntryPoint = new Http401UnauthorizedEntryPoint();
    	
    	http.antMatcher("/rest/**")
    			.authorizeRequests()
    			.anyRequest().authenticated()
    		.and()
    			.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
			.and()
          		.csrf().disable()
      			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)	
          	;
    }
    
    /**
     * <https://stackoverflow.com/questions/51496100/spring-boot-2-basic-http-auth-causes-unprotected-endpoints-to-respond-with-401>
     * <https://stackoverflow.com/questions/22767205/spring-security-exclude-url-patterns-in-security-annotation-configurartion>
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/rest/user/register");
    }
    
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
