package org.upc.oj.auth.dto;

import lombok.Data;

@Data
public class LoginRequestParam {
    private String username;
    private String password;
}
