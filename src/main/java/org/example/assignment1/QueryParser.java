package org.example.assignment1;

import java.util.List;
import java.util.Map;

// 쿼리 스트링 파싱
// 파싱 : URL 의 쿼리스트링 부분을 분석(parsing)하여 해당 데이터를 객체의 속성이나 필드에 저장
public class QueryParser {

    // URL 을 입력받는다.
    public MyRequest parse(String url) {
        // 초기화
        MyRequest request = new MyRequest();

        // url 에서 "?" 의 위치 찾기
        int queryIndex = url.indexOf('?');

        if (queryIndex != -1) {
            // "?" 위치를 기준으로 쿼리 스트링 추출
            String query = url.substring(queryIndex + 1);

            // key-value 묶음으로 분리
            String[] eachQuery = query.split("&");

            // MyRequest 에 key, value 추가
            for (String each : eachQuery) {
                int index = each.indexOf('=');
                // String key = URLDecoder.decode(each.substring(0, index),"UTF-8");
                String key = each.substring(0, index);
                String value = each.substring(index + 1);
                request.addParam(key, value);
            }
        }

        // "?" 없으면 빈 객체 반환
        return request;
    }

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
