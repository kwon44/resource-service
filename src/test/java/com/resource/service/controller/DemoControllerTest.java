package com.resource.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTest {
    @Autowired
    TokenStore tokenStore;
    @Autowired
    DefaultTokenServices defaultTokenServices;

    @Test
    public void getTokens(){
        List<String> tokenValues = new ArrayList<String>();
//        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("client_2");
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientIdAndUserName("client_2", "test2");
        if (tokens!=null){
            for (OAuth2AccessToken token:tokens){
//                tokenValues.add(token.getValue());
                log.info("token:{}",token);
                OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(token);
                UserDetails userDetails = (UserDetails) oAuth2Authentication.getPrincipal();
                log.info("username:{}",userDetails.getUsername());
            }
        }
    }

    @Test
    public void revokeToken() {
        String tokenId = "ec049ddc-4a0b-48f7-82ea-f19412f0412a";
        defaultTokenServices.revokeToken(tokenId);
    }
}