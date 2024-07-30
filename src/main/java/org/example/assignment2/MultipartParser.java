package org.example.assignment2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class MultipartParser {

    // ?? 왜 static?
    public static MyMultipartRequest parse(File file) throws IOException {

        MyMultipartRequest request = new MyMultipartRequest();

        // 파일의 모든 줄을 읽어 리스트에 저장
        // ? TO.PATH 는 뭐여
        List<String> lines = Files.readAllLines(file.toPath());

        // file 의 첫줄에서 HTTP Method, URI, HTTP 버전 저장
        String httpRequestLine = lines.get(0);
        request.setMethod(httpRequestLine.split(" ")[0]);
        request.setUri(httpRequestLine.split(" ")[1]);
        request.setHttpVersion(httpRequestLine.split(" ")[2]);

        // 헤더 분리해서(파싱) 헤더 Map 에 저장
        int lineNum = 1;  // lines.get(0) 은 이미 저장함
        while (!lines.get(lineNum).isEmpty()) { // ? blank 는 안되나? 그리고 for 문보다 이게 낫나?
            String headerLine = lines.get(lineNum);
            String[] header = headerLine.split(": ");
            request.setHeaders(header[0], header[1]);

            if (headerLine.startsWith("Content-Type")) {
                request.setBoundary(headerLine.split("boundary=")[1]);
            }

            lineNum++;
        }

        lineNum++;
        String boundary = "--" + request.getBoundary();

        while (lines.get(lineNum).startsWith(boundary)) {
            lineNum++;
            if (lineNum >= lines.size()) {
                break;
            }

            String[] parts = lines.get(lineNum).split("; ");
            String contentDisposition = parts[0].split(": ")[1];
            String name = parts[1].split("=")[1];
            String filename = parts[2].split("=")[1];

            lineNum++;
            String contentType = lines.get(lineNum).split(": ")[1];

            lineNum += 2;
            String content = lines.get(lineNum);

            request.getParts().add(new MyMultipartRequest.Part(contentDisposition, name, filename, contentType, content));

            lineNum++;
        }


        return request;
    }

    public static void printMultipartParserRequest(MyMultipartRequest request) {
        System.out.println("2번 --------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Method: " + request.getMethod());
        System.out.println("URI: " + request.getUri());
        System.out.println("HTTP Version: " + request.getHttpVersion());
        System.out.println("Boundary: " + request.getBoundary());
        System.out.println("Headers:");
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Parts:");
        for (MyMultipartRequest.Part part : request.getParts()) {
            System.out.println("  Content-Disposition: " + part.getContentDisposition());
            System.out.println("  Name: " + part.getName());
            System.out.println("  Filename: " + part.getFilename());
            System.out.println("  Content-Type: " + part.getContentType());
            System.out.println("  Content: " + part.getContent());
            System.out.println();
        }

        System.out.println("------------------------------------------------------------------------------------------");
    }

}
