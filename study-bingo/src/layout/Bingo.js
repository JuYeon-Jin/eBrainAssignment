import React, {useEffect, useState} from 'react';
import './Bingo.css';

function Bingo({ callNumbers }) {

    const getRandomNumber = () => {

        const numbers = [];
        do {
            let randomNum = Math.floor(Math.random() * 15) + 1;
            if (!numbers.includes(randomNum)) {
                numbers.push(randomNum);
            }
        } while (numbers.length < 9)

        return numbers;
    }

    const bingo = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];

    // 컴포넌트가 렌더링될 때마다 빙고 체크를 실행합니다.
    useEffect(() => {checkBingo();}, [callNumbers])

    const checkBingo = () => {
        for (let line of bingo) {
            // bingoNum 의 특정 줄이 callNumbers에 모두 포함되어 있는지 확인
            if (line.every(index => callNumbers.includes(bingoNum[index]))) {
                alert("빙고!");
                break;
            }
        }
    }

    const [bingoNum] = useState(getRandomNumber());

    return (
        <div className="bingo-container">

            {bingoNum.map((number, index) => (
                <div key={index} className={`bingo-item ${callNumbers.includes(number) ? 'active' : ''}`}>
                        {number}
                </div>
            ))}

        </div>
    );
}

export default Bingo;