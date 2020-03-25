package com.company.helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

public class CookieHelper {
    public static String getCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        String cookieName = name;
        Cookie cookie = null;
        String value = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    cookie = c;
                    value = cookie.getValue();
                    break;
                }
            }
        }
        return value;
    }

    public static void setCookieByName(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletResponse response, String name){
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}

