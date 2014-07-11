package com.app.controller;

import com.app.config.DatabaseConfig;
import com.app.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cheebu on 7/10/2014.
 */
@RestController
public class SimpleController {

    @Autowired
    private DatabaseConfig databaseConfig;

    @Autowired
    private WebConfig webConfig;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public List<String> listAllConfig() {
        return Arrays.asList(new String[] {
                                            databaseConfig.getUsername(),
                                            databaseConfig.getPassword(),
                                            databaseConfig.getUrl(),
                                            webConfig.getAppname()
                                          }
                            );
    }

}
