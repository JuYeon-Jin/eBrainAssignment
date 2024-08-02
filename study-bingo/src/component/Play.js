import React, {useState, useEffect} from 'react';
import Bingo from "./Bingo";

/* childName = {parentName} */
const Play = () => {

    // count(number) : 플레이어의 수를 컨트롤 하기 위한 변수
    const [count, setCount] = useState(0);

    // players(배열) : 플레이어 명(보여줘야 됨)
    const [players, setPlayers] = useState([]);

    // isGameStarted(boolean) : 게임이 진행중인지, 아닌지를 컨트롤 하기 위한 변수
    const [isGameStarted, setIsGameStarted] = useState(false);

    // bingoCallList(배열) : 추첨된 빙고 번호 목록
    const [bingoCallList, setBingoCallList] = useState([]);

    // 플레이어 수 추가 시 최대 인원 5명을 제한하기 위한 함수
    const addPlayer = () => {
        if (count >= 5) {
            alert('최대 플레이어 수(5인)에 도달했습니다.');
            return;
        }
        setCount(prevCount => {
            setPlayers([...players, 'player ' + (prevCount + 1)]);
            return prevCount + 1;
        });
    }

    // 게임을 위한 플레이어 최소 인원(2명) 을 확인하며, 충족 시 분기처리를 위한 boolean 변수를 true 로 set 함.
    const startGame = () => {
        if (count >= 2) {
            setIsGameStarted(true);
        } else {
            alert('게임을 시작하려면 최소한 두 명의 플레이어가 필요합니다.');
        }
    }

    // Reset(게임 취소)시 게임을 초기화 하기 위한 함수
    const resetGame = () => {
        setPlayers([]);
        setCount(0);
        setIsGameStarted(false);
        setBingoCallList([]);
    }

    // Call(빙고 번호 호출)시 랜덤한 임의의 수를 bingoCallList 에 추가하기 위한 함수
    // 조건 1. 호출번호가 15개 이하인지 → 조건 2. 이미 존재하는 번호인지
    const calling = () => {
        let number = Math.floor(Math.random() * 15) + 1;
        if (bingoCallList.length < 15) {
            if (!bingoCallList.includes(number)) {
                setBingoCallList([...bingoCallList, number]);
            } else {
                calling();
            }
        }
    }

    return (
        <div className="game-container">

            {/* 삼항연산자 사용하여 영역 전환 */}
            {/* 게임 시작(false → true) ↔ 게임 취소(true → false) */}
            {/* state 패턴 공부해보기 */}
            <div className="controls-container">
            {!isGameStarted ? (
                <div className="btn-container">
                    <button onClick={addPlayer}>플레이어 추가</button>
                    <button onClick={startGame}>시작</button>
                </div>
            ) : (
                <div>
                    <div className="btn-container">
                        <button onClick={resetGame}>Reset</button>
                        <button onClick={calling}>Call</button>
                    </div>
                    <div className="bingo-call-container">
                        {bingoCallList.map((eachCallNumber, index) => (
                            <div className="bingo-call-item">{eachCallNumber}</div>
                        ))}
                    </div>
                </div>
            )}
            </div>

            <div className="bingo-board-container">
                {players.map((player) => (
                    <Bingo player={player} callNumbers={bingoCallList} />
                ))}
            </div>

        </div> /* game-container */
    );
};
// 세미콜론(;) 자동삽입 규칙

export default Play;