package org.example.assignment3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultipartFile {

    /*
멀티파트 파일 읽어서 파일로 저장
<예시>
    MultipartFile firstFile = myMultipartRequest.getMultipartFile("text1");
    firstFile.store("c:/output/first.txt);

    MultipartFile secondFile = myMultipartRequest.getMultipartFile("text2");
    secondFile.store("c:/output/second.txt);
        * */
    private String fileName;
    private String contentType;
    private byte[] content;

    public MultipartFile(String fileName, String contentType, byte[] content) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void store(String path) throws IOException {
        File file = new File(path);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content);
        }
    }

}
