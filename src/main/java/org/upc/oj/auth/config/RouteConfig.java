package org.upc.oj.auth.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class RouteConfig {
    private Set<String> routes = new HashSet<>();
    public RouteConfig(){
        routes.add("/auth/login");
        routes.add("/auth/reg");
        routes.add("/bank/questions");
        routes.add("/index.html");
        routes.add("/bank/question/content");
        routes.add("/bank/question/tags");
        routes.add("/comments");
        routes.add("/record/all");
    }
    public Set<String> getRouters(){
        return routes;
    }
}
