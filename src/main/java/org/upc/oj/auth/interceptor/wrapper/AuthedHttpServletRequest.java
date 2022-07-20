package org.upc.oj.auth.interceptor.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AuthedHttpServletRequest extends HttpServletRequestWrapper {
    private String username;
    private String identity;
    public AuthedHttpServletRequest(HttpServletRequest request) {
        super(request);
    }
    public void setUsername(String u){
        username=u;
    }
    public void setIdentity(String i){
        identity=i;
    }
}
