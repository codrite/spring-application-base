package com.app.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by cheebu on 7/14/2014.
 */
@WebService(serviceName = "GenericService")
public class GenericService implements GenericServiceInterface {

    private String serviceName = "generic";

    @Override
    @WebMethod
    public String getServiceName() {
        return serviceName;
    }

}
