package com.cathive.fx.pastebin.server;

import com.cathive.fx.pastebin.common.JsonConverter;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * @author Alexander Erben (a_erben@outlook.com)
 */
@Singleton
public class Configuration {

    @Produces
    @Named("converter")
    public JsonConverter getJsonConverter(){
        return new JsonConverter();
    }
}
