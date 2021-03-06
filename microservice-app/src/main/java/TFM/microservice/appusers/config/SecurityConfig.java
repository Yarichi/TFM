package TFM.microservice.appusers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

import TFM.microservice.appusers.security.CorsFilter;
import TFM.microservice.appusers.security.JWTAuthenticationFilter;
import TFM.microservice.appusers.security.SecurityProperties;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SecurityProperties properties;
    
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
    
    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }
    
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
    		"/login",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
    
    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, SecurityProperties properties) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.properties = properties;
        

    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilterBefore(corsFilter(), SessionManagementFilter.class)
		.cors().disable()
		.csrf().disable().
		headers().frameOptions().disable().and().
		authorizeRequests().
		antMatchers(AUTH_WHITELIST).permitAll().
		antMatchers(HttpMethod.OPTIONS, "**").permitAll().
		antMatchers(HttpMethod.POST, "/appUser").permitAll().
		anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/swagger-ui.html");
		web.ignoring().antMatchers(AUTH_WHITELIST).and().
		ignoring().antMatchers(HttpMethod.OPTIONS, "**").and().
		ignoring().antMatchers(HttpMethod.POST, "/appUser");
// 
//        http.addFilterAfter(new CustomFilter(),
//          BasicAuthenticationFilter.class);
    }
	
	
	 @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
	 
	 
}
