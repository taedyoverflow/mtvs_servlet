package com.ohgiraffers.section01.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/response")
public class ResponseTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 필기.
        *   서블릿이 수행하는 역할은 크게 3가지 이다.
        *   1. 요청 받기 - HttpMethod GET/POST 요청에 따라서 parameter로 전달받은 데이터를 꺼낸다.
        *   2. 비지니스 로직 처리 = DB 접속과 CRUD에 대한 로직 + 도메인 로직
        *   3. 응답하기 - 문자열로 동적인 웹(HTML 태그) 페이지를 만들고 스트림을 이용해 내보낸다.
        *  */

        String greeting = "안녕하세요";

        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<!doctype html>\n")
                .append("<html>\n")
                .append("<head></head>\n")
                .append("<body>\n")
                .append("<h1>" + greeting +"</h1>")
                .append("</body>\n")
                .append("</html>");

        // MIME 타입
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.write(responseBuilder.toString());

        out.flush();
        out.close();
    }
}
