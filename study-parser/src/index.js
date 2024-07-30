const queryParser = require('./queryParser');

// 테스트 코드
const testUrl = 'https://www.example.com/?id=kmc774&favorite=001&favorite=002';

const request = queryParser.parse(testUrl);

console.log(request.getParam("id")); // 'kmc774'
console.log(request.getParams("favorite")); // ['001', '002']