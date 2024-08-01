
//
class MyRequest {

    // parameters = new Map();

    // TODO 123455667
    // 생성자: MyRequest 클래스의 인스턴스를 초기화할 때 호출됩니다.
    // 여기서 바로 배열로 만들어서 초기화 할 수 있었겠따.
    // 오류에 대한 대비책을 고민 많이 해보기(경우의 수 생각해보기)
    // 생성자 없이 그냥 this.parameters = new Map(); 이렇게 해도 선언 초기화 가능한지 확인
    constructor() {
        // 인스턴스 변수 `parameters`를 Map 객체로 초기화합니다.
        this.parameters = new Map();
    }

    // TODO
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

function parse(url) {
    const request = new MyRequest();
    const queryStartIndex = url.indexOf('?');
    if (queryStartIndex === -1) {
        return '';
    }
    const query = url.substring(queryStartIndex + 1);
    const keysAndValues = query.split('&');

    for (const keyAndValue of keysAndValues) {
        /*const key = keyAndValue.split('=')[0];
        const value = keyAndValue.split('=')[1];*/
        const [key, value] = keyAndValue.split('=');
        request.addParam(key, value);
    }

    return request;
}

module.exports = { parse };