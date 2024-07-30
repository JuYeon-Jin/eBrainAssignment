package org.example.assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// DTO 객체
public class MyRequest {

    private Map<String, List<String>> parameters;

    public MyRequest() {
        parameters = new HashMap<>();
    }

    /**
     * <p>이 메서드는 주어진 키와 값 쌍을 {@link MyRequest} 객체의 내부 데이터(Map)에 추가합니다.
     * 만약 주어진 key 에 대한 value(List<String>) 이 이미 존재하면, value 를 추가합니다.
     * 처음 확인되는 key 라면, 새로운 value(List<String>) 를 생성하여 추가합니다.</p>
     *
     * @param key 파라미터 키
     * @param value 파라미터 값
     */
    public void addParam(String key, String value) {
        List<String> values = parameters.get(key);

        if (values == null) {
            values = new ArrayList<>();
            parameters.put(key, values);
        }

        values.add(value);
    }

    /**
     * <p>주어진 key 에 대한 value(List<String>) 에서 첫 번째 값을 반환합니다.
     * key 에 대한 value 가 존재하지 않으면 `null`을 반환합니다.</p>
     *
     * @param key 파라미터 키 (예: "name", "age")
     * @return 첫 번째 파라미터 값 (예: "John", "30")
     */
    public String getParam(String key) {
        List<String> values = parameters.get(key);
        if (values == null || values.isEmpty()) {
            return null;
        }
        return values.get(0);
    }

    /**
     * <p>어진 key 에 대한 모든 value(List<String>) 를 반환합니다.
     * key 에 대한 value 가 존재하지 않으면 `null`을 반환합니다.</p>
     *
     * @param key 파라미터 키 (예: "name", "age")
     * @return 모든 파라미터 값의 리스트 (예: ["John", "Jane"])
     */
    public List<String> getParams(String key) {
        return parameters.get(key);
    }

    // 결과 확인을 위한 메소드
    public Map<String, List<String>> getParameters() {
        return parameters;
    }
}
