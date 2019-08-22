package com.resource.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/beaccount")
public class ResourceOwnerController {
    @Autowired
    private TokenStore tokenStore;

    @PreAuthorize("#oauth2.hasScope('beaccount.read')")
    @RequestMapping(value = "/getbeaccount/{accountid}", method = RequestMethod.GET)
    public Map getBeAccountById(@PathVariable(value = "accountid", required = true) Integer accountId){
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
        //取得oauth2 request info
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
        //取得產生後accessToken info
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());

        List<String> tokenValues = new ArrayList<String>();
        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("beclient");
        if (tokens!=null){
            for (OAuth2AccessToken token:tokens){
                log.info("token:{}",token);
            }
        }

        Map<String,String> map = new HashMap<>();
        map.put("result", "getBeAccountById");
        return map;
    }

    @PreAuthorize("#oauth2.hasScope('beaccount.write')")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Map update() {
        Map<String,String> map = new HashMap<>();
        map.put("result", "berole.edit");
        return map;
    }

    @PreAuthorize("#oauth2.hasScope('beaccount.delete')")
    @RequestMapping(value = "/delete/{accountid}", method = RequestMethod.GET)
    public Map deleteTest(@PathVariable("accountid") Integer accountId) {
        Map<String,String> map = new HashMap<>();
        map.put("result", "beaccount.delete");
        return map;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/deletebeaccount/{accountid}", method = RequestMethod.GET)
    public Map deleteBeAccountById(@PathVariable("accountid") Integer accountId) {
        Map<String,String> map = new HashMap<>();
        map.put("result", "hasRole_ADMIN");
        return map;
    }

}
