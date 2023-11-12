package com.ohgiraffers.section01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String firstName = (String) req.getAttribute("firstName");
//        String lastName = (String) req.getAttribute("lastName");
//
//        System.out.println("redirect servlet firstName = " + firstName);
//        System.out.println("redirect servlet lastName = " + lastName);
        
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies) {
            System.out.println("cookie = " + cookie);

            System.out.println("key = " + cookie.getName());
            System.out.println("value = " + cookie.getValue());
        }

        System.out.println("redirect servlet 요청 확인..");
    }
}
