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

        String url = "https://www.ebrainsoft.com/?id=kmc774&favorite=001&favorite=002";
        File multipartData = new File("C:\\Users\\jinju\\OneDrive\\바탕 화면\\request-dummy.txt");

        QueryParser queryParser = new QueryParser();
        MyRequest myRequest = queryParser.parse(url);
        queryParser.printRequest(myRequest);

        MultipartParser multipartParser = new MultipartParser();
        MyMultipartRequest myMultipartRequest = multipartParser.parse(multipartData);
        multipartParser.printMultipartParserRequest(myMultipartRequest);


        MultipartFile firstFile = myMultipartRequest.getMultipartFile("text1");
        firstFile.store("c:/output/first.txt");

        MultipartFile secondFile = myMultipartRequest.getMultipartFile("text2");
        secondFile.store("c:/output/second.txt");


    }
}