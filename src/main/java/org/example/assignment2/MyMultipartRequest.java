package org.example.assignment2;

import org.example.assignment3.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// HTTP multipart/form-data 요청을 나타내는 데이터 구조 정의
public class MyMultipartRequest {
    private String method;  // HTTP 메서드를 저장 (예: POST, GET)
    private String uri;
    private String httpVersion;
    private String boundary;
    private Map<String, String> headers = new HashMap<>();  // 헤더를 저장하는 맵 (키: 헤더 이름, 값: 헤더 값)
    private List<Part> parts = new ArrayList<>();  // 요청의 각 파트를 저장하는 리스트

    // // 요청의 multipart/form-data의 각 파트  ?? 왜 static?
    public static class Part {
        private String contentDisposition;  // Content-Disposition 값
        private String name;  // name 값
        private String filename;  // filename 값 (파일 파트인 경우)
        private String contentType;  // Content-Type 값
        private String content;  // 파트의 실제 콘텐츠 (문자열)

        // Part 클래스의 생성자
        public Part(String contentDisposition
                  , String name
                  , String filename
                  , String contentType
                  , String content) {
            this.contentDisposition = contentDisposition;
            this.name = name;
            this.filename = filename;
            this.contentType = contentType;
            this.content = content;
        }

        public String getContentDisposition() {
            return contentDisposition;
        }

        public String getName() {
            return name;
        }

        public String getFilename() {
            return filename;
        }

        public String getContentType() {
            return contentType;
        }

        public String getContent() {
            return content;
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(String name, String value) {
        this.headers.put(name, value);
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    // name 으로 file 데이터 get
    public MultipartFile getMultipartFile(String name) {

        for (Part part : parts) {
            if (part.getName().equals(name)) {
                return new MultipartFile(part.getFilename(), part.getContentType(), part.getContent().getBytes());
            }
        }
        return null;
    }
}
