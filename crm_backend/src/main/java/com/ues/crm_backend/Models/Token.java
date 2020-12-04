package com.ues.crm_backend.Models;

import java.util.ArrayList;
import java.util.Date;
import com.ues.crm_backend.security.JWTTokenProvider;

import javax.servlet.http.HttpSession;
/** Модель токена пользователя*/
public class Token {
    private String token;
    private Date lastDate = new Date();
    private final String username;
    private final String role;
    private JWTTokenProvider jwtTokenProvider;
    /**Список всех токенов */
    public static ArrayList<Token> Alltokens = new ArrayList<Token>();

    /** Конструктор класса*/
    public Token(String username, String role, JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.token = jwtTokenProvider.createToken(username, role);
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    /**Функция проверки валидности токена в зависимости от времени бездействия:
     * Более 10 мин - обновление;
     * Более 15 мин - удаление;
     * @return статус токена : валидный/невалидный
     */
    public boolean checkSession() {
        Date presentDate = new Date();
        if (presentDate.getTime() - lastDate.getTime() > 15*60000) {
            Alltokens.remove(findTokenByRequest(this.token));
            return false;
        }
        if (presentDate.getTime() - lastDate.getTime() > 10*60000) {
            resetToken();
        }
        this.lastDate = presentDate;
        return true;
    }

    /** Обновление токена*/
    public void resetToken() {
        this.token = jwtTokenProvider.createToken(this.username, this.role);
    }

    /** Поиск текущего токена в списке всех имеющихся токенов*/
    public static Token findTokenByRequest(String request) {
        for (Token token:Alltokens) {
            if (token.getToken().equals(request)) {
                return token;
            }
        }
        return null;
    }
}
