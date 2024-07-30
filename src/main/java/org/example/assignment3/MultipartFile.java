package org.example.assignment3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MultipartFile {

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

    /**
     * <p>FileOutputStream 을 사용하여 주어진 경로에 파일을 저장합니다.
     * 파일의 부모 디렉토리가 존재하지 않으면 해당 디렉토리를 생성한 후 파일을 저장합니다.</p>
     *
     * @param path 저장할 파일의 경로
     * @throws IOException 파일을 저장하는 동안 오류가 발생한 경우
     */
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
