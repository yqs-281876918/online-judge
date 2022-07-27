package org.upc.oj.auth.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class RouteConfig {
    private Set<String> releaseUrls=new HashSet<>();
    public RouteConfig(){
        releaseUrls.addAll(Arrays.asList("/auth/login","/auth/reg"));
        releaseUrls.addAll(Arrays.asList("/bank/tags","/bank/question/tags","/bank/questions","/bank/question/content"));
        releaseUrls.addAll(Arrays.asList("/comments"));
        releaseUrls.addAll(Arrays.asList("/record/all"));
    }
}
