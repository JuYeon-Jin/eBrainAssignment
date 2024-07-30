package org.example.assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DTO 객체
public class MyRequest {

    private Map<String, List<String>> parameters;

    public MyRequest() {
        // parameters 초기화
        parameters = new HashMap<>();
    }

    public void addParam(String key, String value) {
        // Map<String, List<String>> parameters 에서 주어진 키에 대한 값을 찾아보기
        List<String> values = parameters.get(key);

        // 값이 없다면 새로운 리스트를 생성해서 parameters 에 추가
        if (values == null) {
            values = new ArrayList<>();
            parameters.put(key, values);
        }

        // 이미 존재하는 key 라면 value 를 추가
        values.add(value);
    }

    public String getParam(String key) {
        List<String> values = parameters.get(key);
        return values.get(0);
    }

    public List<String> getParams(String key) {
        return parameters.get(key);
    }

    public Map<String, List<String>> getParameters() {
        return parameters;
    }
}
