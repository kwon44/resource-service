package com.resource.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/infomessage")
public class ClientController {

    @PreAuthorize("#oauth2.hasScope('infomessage.2c.marquee.read')")
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public Map infomessageRead(){
        Map<String,String> map = new HashMap<>();
        map.put("result", "infomessageRead");
        return map;
    }

    @PreAuthorize("#oauth2.hasScope('infomessage.2c.marquee.create')")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public Map infomessageCreate(){
        Map<String,String> map = new HashMap<>();
        map.put("result", "infomessageCreate");
        return map;
    }

    @PreAuthorize("#oauth2.hasScope('infomessage.2c.marquee.update')")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Map infomessageUpdate(){
        Map<String,String> map = new HashMap<>();
        map.put("result", "infomessageUpdate");
        return map;
    }

}
