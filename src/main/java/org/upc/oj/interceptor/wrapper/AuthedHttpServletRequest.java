package org.upc.oj.interceptor.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AuthedHttpServletRequest extends HttpServletRequestWrapper {
    private String username=null;
    private String identity=null;
    public AuthedHttpServletRequest(HttpServletRequest request) {
        super(request);
    }
    public void setUsername(String u){
        username=u;
    }
    public String getUsername(){
        return username;
    }
    public void setIdentity(String i){
        identity=i;
    }
    public String getIdentity(){
        return identity;
    }
}
