package org.example.assignment2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class MultipartParser {

    /**
     * <p>주어진 파일의 내용을 한 줄씩 읽어 HTTP 요청의 메서드, URI, HTTP 버전, 헤더 및 바디를 파싱합니다.
     * 헤더 정보는 {@link MyMultipartRequest} 객체의 헤더 맵에 저장되고,
     * boundary 를 기준으로 분리된 각 파트는 {@link MyMultipartRequest.Part} 객체로 저장됩니다.</p>
     *
     * @param file 파싱할 파일 객체
     * @return 파싱된 HTTP 요청 정보를 담은 {@link MyMultipartRequest} 객체
     * @throws IOException 파일을 읽는 동안 오류가 발생한 경우
     */
    public MyMultipartRequest parse(File file) throws IOException {

        MyMultipartRequest request = new MyMultipartRequest();

        List<String> lines = Files.readAllLines(file.toPath());

        // file 의 첫줄(lines[0])에서 HTTP Method, URI, HTTP 버전 저장
        String httpRequestLine = lines.get(0);
        request.setMethod(httpRequestLine.split(" ")[0]);
        request.setUri(httpRequestLine.split(" ")[1]);
        request.setHttpVersion(httpRequestLine.split(" ")[2]);

        // lines[1] 부터 헤더 데이터. 각각 분리해서(파싱) 헤더 Map 에 저장
        int lineNum = 1;
        while (!lines.get(lineNum).isEmpty()) {
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

        // boundary 를 기준으로 각 파일데이터 저장
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

    // 결과 확인을 위한 메소드
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
