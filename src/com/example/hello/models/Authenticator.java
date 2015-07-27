package com.example.hello.models;

/**
 * Created by user on 27.07.2015.
 */
public class Authenticator
{

    public String authenticate(String username, String password)
    {
        if ("lcserny".equalsIgnoreCase(username) && "password".equals(password)) {
            return "success";
        } else {
            return "failure";
        }
    }

}
