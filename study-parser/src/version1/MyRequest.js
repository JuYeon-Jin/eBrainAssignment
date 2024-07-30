
class MyRequest {
    parameters = new Map();

    // 생성자: MyRequest 클래스의 인스턴스를 초기화할 때 호출됩니다.
    constructor() {
        // 인스턴스 변수 `parameters`를 Map 객체로 초기화합니다.
        this.parameters = new Map();
    }

    addParam(key, value) {
        if (!this.parameters.has(key)) {
            this.parameters.set(key, []);
        }
        this.parameters.get(key).push(value);
    }

    getParam(key) {
        const values = this.parameters.get(key);
        return values ? values[0] : null;
    }

    getParams(key) {
        return this.parameters.get(key) || null;
    }
}

module.exports = MyRequest;