package com.ohgiraffers.section01.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8000);

        Socket client;
        while((client = serverSocket.accept()) != null) {

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });

            Socket finalClient = client;
            new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(finalClient.getInputStream()));
                    
//                    String line;
//                    while((line = reader.readLine()) != null) {
//                        System.out.println("line = " + line);
//                    }

                    String generalHeader = reader.readLine();
                    System.out.println("generalHeader = " + generalHeader);

                    String requestMethod = generalHeader.split(" ")[0];
                    String requestPath = generalHeader.split(" ")[1];

                    System.out.println("requestMethod = " + requestMethod);
                    System.out.println("requestPath = " + requestPath);

                    Map<String, String> requestHeader = new HashMap<>();
                    String line;
                    while((line = reader.readLine()) != null) {

                        if(line.isBlank()) {
                            break;
                        }

                        String key = line.split(": ")[0];
                        String value = line.split(": ")[1];

                        requestHeader.put(key, value);
                    }
                    System.out.println("requestHeader = " + requestHeader);

                    String body = null;
                    if("POST".equals(requestMethod)) {

                        int contentLength = Integer.parseInt(requestHeader.get("Content-Length"));
                        char[] temp = new char[contentLength];
                        reader.read(temp);
                        body = new String(temp).trim();
                    }
                    System.out.println("body = " + body);

                    StringBuilder responseText = new StringBuilder();
                    if("/".equals(requestPath) && "GET".equals(requestMethod)) {

                        String responseGeneralHeader = "HTTP/1.1 200 OK\r\n";
                        String contentType = "Content-Type: text/html; charset=UTF-8\r\n";
                        String whiteLine = "\r\n";

                        responseText.append(responseGeneralHeader);
                        responseText.append(contentType);
                        responseText.append(whiteLine);

                        BufferedReader br = new BufferedReader(
                                new FileReader("src/main/resources/index.html"));
                        String htmlLine = null;
                        while((htmlLine = br.readLine()) != null) {
                            responseText.append(htmlLine);
                        }

                        responseText.append(whiteLine);
                    }
                    System.out.println("responseText = " + responseText);

                    OutputStream out = finalClient.getOutputStream();
                    out.write(responseText.toString().getBytes());

                    out.flush();
                    out.close();

                    reader.close();

                    finalClient.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
