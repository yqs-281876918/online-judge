package org.upc.oj.auth.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RouterConfig {
    private Map<String,String> routes = new HashMap<>();
    public RouterConfig(){
    }
}
