package com.ues.crm_backend.Models;

import java.util.ArrayList;
import java.util.Date;
import com.ues.crm_backend.security.JWTTokenProvider;

public class Token {
    private String token;
    private Date lastDate = new Date();
    private final String username;
    private final String role;

    private JWTTokenProvider jwtTokenProvider;
    public static ArrayList<Token> Alltokens = new ArrayList<Token>();

    public Token(String username, String role, JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.token = jwtTokenProvider.createToken(username, role);
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public boolean checkSession() {
        Date presentDate = new Date();
        if (presentDate.getTime() - lastDate.getTime() > 2*60000) {
            Alltokens.remove(findTokenByRequest(this.token));
            return false;
        }
        if (presentDate.getTime() - lastDate.getTime() > 1*60000) {
            resetToken();
        }
        this.lastDate = presentDate;
        return true;
    }

    public void resetToken() {
        this.token = jwtTokenProvider.createToken(this.username, this.role);
    }

    public static Token findTokenByRequest(String request) {
        for (Token token:Alltokens) {
            if (token.getToken().equals(request)) {
                return token;
            }
        }
        return null;
    }
}
