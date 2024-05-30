package ca.sheridancollege.vakild.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private AccessDeniedHandler accessDenied;
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = 
				 new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public AuthenticationManager authManager (HttpSecurity http,
			PasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder authBuilder = 
			http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
		
		return authBuilder.build();
	}
	



	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		////////////////////////////////////////
		//Delete when switching to production code.
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http
	.authorizeHttpRequests((authz) -> authz
	.requestMatchers(antMatcher("/add")).hasRole("VENDOR")
	.requestMatchers(antMatcher("/edit")).hasRole("VENDOR")
	.requestMatchers(antMatcher("/delete")).hasRole("VENDOR")
	//permitAll->Can access without logging in
	//hasRole("ROLE") for a specific role
	//hasAnyRole("ROLE1", "ROLE2") for more roles
	.requestMatchers(antMatcher("/")).permitAll()
	.requestMatchers(antMatcher("/h2-console/**")).permitAll()
	.requestMatchers(antMatcher("/register")).permitAll()
	.requestMatchers(antMatcher("/css/**")).permitAll()
	.anyRequest().authenticated()
	)
	.formLogin((formLogin) -> formLogin
			.loginPage("/login")
			.failureUrl("/login?failed")
			.permitAll()
			)
			
	.logout((logout) ->
			logout.deleteCookies("remove") 
					.invalidateHttpSession(true)
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout")
					.permitAll() 
				)	
	.exceptionHandling((ex) -> ex.accessDeniedHandler(new LoginAccessDeniedHandler())

			);

	    return http.build();
	}


}
