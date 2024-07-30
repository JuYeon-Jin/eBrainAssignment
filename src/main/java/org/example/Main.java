package org.example;

import org.example.assignment1.MyRequest;
import org.example.assignment1.QueryParser;
import org.example.assignment2.MultipartParser;
import org.example.assignment2.MyMultipartRequest;
import org.example.assignment3.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        javaAssignment();
    }

    public static void javaAssignment() throws IOException {

        String url = "https://www.ebrainsoft.com/?id=kmc774&favorite=001&favorite=002";
        File multipartData = new File("src/main/java/org/example/ref/request-dummy.txt");

        QueryParser queryParser = new QueryParser();
        MyRequest myRequest = queryParser.parse(url);
        queryParser.printRequest(myRequest);

        MultipartParser multipartParser = new MultipartParser();
        MyMultipartRequest myMultipartRequest = multipartParser.parse(multipartData);
        multipartParser.printMultipartParserRequest(myMultipartRequest);


        MultipartFile firstFile = myMultipartRequest.getMultipartFile("text1");
        firstFile.store("src/main/java/org/example/output/first.txt");

        MultipartFile secondFile = myMultipartRequest.getMultipartFile("text2");
        secondFile.store("src/main/java/org/example/output/second.txt");
    }
}