package com.app.ws;

import javax.jws.WebMethod;

/**
 * Created by cheebu on 7/14/2014.
 */
public interface GenericServiceInterface {
    @WebMethod
    String getServiceName();
}
