package org.upc.oj.auth.po;

import lombok.Data;

@Data
public class OJUser {
    private String username;
    private String password;
    private String identity;
}
