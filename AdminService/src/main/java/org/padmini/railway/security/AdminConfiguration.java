package org.padmini.railway.security;
import org.springframework.context.annotation.Bean; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.password.NoOpPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder;
  
@SuppressWarnings("deprecation")
@EnableWebSecurity 
public class AdminConfiguration extends WebSecurityConfigurerAdapter 
{
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception  { 
	  auth.inMemoryAuthentication()
	  		.withUser("padminivetcha").password("padminiadmin").roles("ADMIN"); 
  }
  
  @Override 
  protected void configure(HttpSecurity http) throws Exception  {
	  
	  http.httpBasic()
	  .and().authorizeRequests()
	  	  .antMatchers("/admin/add","/admin/update/**","/admin/delete/**").hasRole("ADMIN")
	  	  .antMatchers("/admin/all","/admin/").permitAll()
	  	  .and().csrf().disable().headers().frameOptions().disable(); 
	 }
  
  @Bean
  public PasswordEncoder getPasswordEncoder() 
  { 
	  return NoOpPasswordEncoder.getInstance(); 
  } 
}