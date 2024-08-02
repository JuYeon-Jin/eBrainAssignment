import React, {useCallback, useEffect, useState} from 'react';
import '../layout/Bingo.css';

const Bingo = ({ player, callNumbers }) => {

    // 랜덤 발생 수를 !numbers.includes() 중복없이 배열에 담는다.(do-while)
    const getBingoNumber = () => {
        const numbers = [];
        do {
            let randomNum = Math.floor(Math.random() * 15) + 1;
            if (!numbers.includes(randomNum)) {
                numbers.push(randomNum);
            }
        } while (numbers.length < 9)
        return numbers;
    };

    // 중복되지 않은 9개의 숫자를 담는 배열(하나의 빙고판에 사용될 예정)
    const [bingoBoardNumbers] = useState(getBingoNumber());

    // 3x3 빙고판 승리 조합
    const winningCombination = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];

    // Array.fill() : arr.fill(value, start, end)
    // value : 배열의 모든 요소를 채울 값
    // start : 시작 인덱스
    // end : 종료 인덱스(기본값은 배열의 길이)
     const [bingoClassForCss, setBingoClassForCss] = useState(
        Array(bingoBoardNumbers.length).fill('bingo-item'));
    // const bingoClassForCss = Array(bingoBoardNumbers.length).fill('bingo-item');

    // callNumbers 가 하나씩 추가될 때 마다 빙고 체크 실행.
    // useEffect(() => {  }, [])
    // 첫번째 인자 : () => { } 는 실행 할 함수
    // 두번째 인자 : [] 은 의존성 배열, 이 배열에 포함된 값이 변경될 때 마다, 첫번째 인자의 함수가 실행
    useEffect(() => {
        const lastCalledNumber = callNumbers[callNumbers.length - 1];
        const tempArr = [...bingoClassForCss];
        bingoBoardNumbers.forEach((number, index) => {
                if (number === lastCalledNumber) {
                    tempArr[index] = 'bingo-item active';
                }
            });
            setBingoClassForCss(tempArr);
            checkBingo();
        }, [callNumbers])

    // 플레이어의 빙고가 우승 조합에 해당되는지 반복문(for( A of B ) {  })을 통해 확인
    const checkBingo = () => {
        for (let line of winningCombination) {
            // every 메서드 : 배열의 모든 요소가 주어진 조건을 만족하면 true
            // bingoBoardNumbers[index] : bingoBoardNumbers[0] 에 위치한 숫자가
            // callNumbers.includes() : callNumbers 안에 포함되어있는가?
            // every : 모든 요소가 true 면 동작
            if (line.every(index => callNumbers.includes(bingoBoardNumbers[index]))) {
                const tempArr = [...bingoClassForCss];
                line.forEach(index => {
                    tempArr[index] = 'bingo-item bingo';
                });
                setBingoClassForCss(tempArr);

                // 템플릿 리터럴을 사용할 때는 작은따옴표(')나 큰따옴표(")가 아닌 백틱(`)을 사용해야 한다.
                // alert("${player} 빙고!"); →  alert('${player} 빙고!');
                document.getElementById(`${player}`).innerHTML = `${player} 빙고!`;
            }
        }
    };

    return (
        <div className="bingo-board">
            {player}
            <div className="bingo-item-container">
            {bingoBoardNumbers.map((number, index) => (
                <div key={index} className={bingoClassForCss[index]}>
                        {number}
                </div>
            ))}
            </div>
            <span id={player} className="alert"></span>
        </div>
    );
};

export default Bingo;