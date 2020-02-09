//package com.haapp.formicary.security;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    private UserDetailsServiceImpl userDetailsService;
//
//    private static String REALM = "logistic-offer";
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().antMatchers()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic()
//                .realmName(REALM)
//                .authenticationEntryPoint(getEntryPoint())
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .csrf()
//                .disable();
//    }
//
//    @Bean
//    public BasicAuthenticationEntryPoint getEntryPoint() {
//        BasicAuthenticationEntryPoint result = new BasicAuthenticationEntryPoint();
//        result.setRealmName(REALM);
//        return result;
//    }
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
