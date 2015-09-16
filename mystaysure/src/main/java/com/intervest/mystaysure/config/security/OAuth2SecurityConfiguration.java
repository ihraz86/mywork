package com.intervest.mystaysure.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

@Configuration
@PropertySource("classpath:system.properties")
public class OAuth2SecurityConfiguration {
	
	@Value("${staysure.api.url}")
	private String staysureApiUrl;
	@Value("${staysure.client.id}")
	private String staysureClientId;
	@Value("${staysure.client.secret}")
	private String staysureClientSecret;
	@Value("${staysure.client.grant.type}")
	private String staysureGrantType;
	@Value("${staysure.user.name}")
	private String staysureUserName;
	@Value("${staysure.user.passowrd}")
	private String staysurePassword;

	@Bean
	public OAuth2RestOperations oauth2RestTemplate() {
	    AccessTokenRequest atr = new DefaultAccessTokenRequest();
	    return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
	}

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List<String> scopes = new ArrayList<String>(2);
        scopes.add("write");
        scopes.add("read");
        resource.setScope(scopes);
        resource.setAccessTokenUri(staysureApiUrl + "/oauth/token");
        resource.setClientId(staysureClientId);
        resource.setClientSecret(staysureClientSecret);
        resource.setGrantType(staysureGrantType);

        resource.setUsername(staysureUserName);
        resource.setPassword(staysurePassword);

        return resource;
    }

}
