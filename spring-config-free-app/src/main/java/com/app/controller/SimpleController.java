package com.app.controller;

import com.app.config.DatabaseConfig;
import com.app.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
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

    @RequestMapping(value = "/config",
                    method = RequestMethod.GET,
                    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    InternalUseOnly listAllConfig() {
        return new InternalUseOnly(Arrays.asList(new String[]
                        {
                                databaseConfig.getUsername(),
                                databaseConfig.getPassword(),
                                databaseConfig.getUrl(),
                                webConfig.getAppname()
                        }
        )
        );
    }

    @XmlRootElement
    public static class InternalUseOnly {
        private List<String> internal;

        public InternalUseOnly() {}

        public InternalUseOnly(List<String> internal) {
            this.internal = internal;
        }

        @XmlAttribute
        public List<String> getInternal() {
            return internal;
        }
    }

}
