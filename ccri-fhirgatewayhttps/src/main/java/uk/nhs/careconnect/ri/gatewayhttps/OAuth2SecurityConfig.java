package uk.nhs.careconnect.ri.gatewayhttps;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
@PropertySource("classpath:oauth2.properties")
public class OAuth2SecurityConfig {

    @Value("${accessTokenUri}")
    private String accessTokenUri;

    @Value("${userAuthorizationUri}")
    private String userAuthorizationUri;

    @Value("${clientID}")
    private String clientID;

    @Value("${clientSecret}")
    private String clientSecret;

    @Bean
    public OAuth2ProtectedResourceDetails oauthDetails(){
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("ccri");
        details.setClientId(clientID);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setTokenName("oauth_token");
        details.setScope(Arrays.asList("launch"));
//        details.setPreEstablishedRedirectUri("http://localhost/login");
//        details.setUseCurrentUri(false);
        return details;
    }

    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oAuth2ClientContext) {
        return new OAuth2RestTemplate(oauthDetails(), oAuth2ClientContext);
    }

}
