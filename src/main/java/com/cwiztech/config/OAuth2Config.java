//package com.cwiztech.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//	public static final String RESOURCE_ID1 = "restservice1";
//	public static final String RESOURCE_ID2 = "restservice2";
//	public static final String RESOURCE_ID3 = "restservice3";
//	public static final String RESOURCE_ID4 = "restservice4";
//
//	@Autowired
//	@Qualifier("userDetailsService")
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	@Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
//
//	@Value("${gigy.oauth.tokenTimeout:7200}")
//	private int expiration;
//
//	// password encryptor
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
//		configurer.authenticationManager(authenticationManager);
//		configurer.userDetailsService(userDetailsService);
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//		.withClient("cmis").secret("secret").accessTokenValiditySeconds(expiration)
//				.scopes("read", "write").authorizedGrantTypes("password", "authorization_code", "refresh_token")
//				.resourceIds(RESOURCE_ID1).resourceIds(RESOURCE_ID2).resourceIds(RESOURCE_ID3).resourceIds(RESOURCE_ID4).and()
//				
//				.withClient("signup").secret("secret").accessTokenValiditySeconds(7200).scopes("read", "write")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token").resourceIds(RESOURCE_ID3).and()
//				
//				.withClient("lms").secret("secret").accessTokenValiditySeconds(7200).scopes("read", "write")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token").resourceIds(RESOURCE_ID1).and()
//				
//				.withClient("vle").secret("ulearnvle").accessTokenValiditySeconds(7200).scopes("read", "write")
//				.authorizedGrantTypes("password", "authorization_code", "refresh_token").resourceIds(RESOURCE_ID4);
//
//	}
//
//}
