package org.example.assignment1;

import java.util.List;
import java.util.Map;

public class QueryParser {

    /**
     * <p>이 메서드는 URL에서 쿼리 스트링 부분을 추출하여 key-value 쌍으로 분리한 후,
     * {@link MyRequest} 객체에 추가합니다. URL에 쿼리 스트링이 없는 경우 빈 {@link MyRequest} 객체(NULL)를 반환합니다.</p>
     *
     * @param url 파싱할 URL 문자열. URL의 형식은 "http://example.com/page?name=John&age=30&name=Jane"
     * @return 파싱된 쿼리 스트링이 저장된 {@link MyRequest} 객체
     */
    public MyRequest parse(String url) {
        MyRequest request = new MyRequest();

        int queryIndex = url.indexOf('?');
        if (queryIndex != -1) {
            String query = url.substring(queryIndex + 1);
            String[] eachQuery = query.split("&");

            for (String each : eachQuery) {
                int index = each.indexOf('=');
                // String key = URLDecoder.decode(each.substring(0, index),"UTF-8");
                String key = each.substring(0, index);
                String value = each.substring(index + 1);
                request.addParam(key, value);
            }
        }

        return request;
    }

    // 결과 확인을 위한 메소드
    public void printRequest(MyRequest request) {
        System.out.println("1번 --------------------------------------------------------------------------------------");
        for (Map.Entry<String, List<String>> entry : request.getParameters().entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            System.out.println("Key: " + key);
            for (String value : values) {
                System.out.println("  Value: " + value);
            }
        }
        System.out.println("------------------------------------------------------------------------------------------");
    }
}
