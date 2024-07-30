const MyRequest = require('./myRequest');

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

// 테스트 코드
const testUrl = 'https://www.example.com/?id=kmc774&favorite=001&favorite=002';
const request = parse(testUrl);

console.log(request.getParam("id")); // 'kmc774'
console.log(request.getParams("favorite")); // ['001', '002']