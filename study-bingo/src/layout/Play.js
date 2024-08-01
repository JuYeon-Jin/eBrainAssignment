import React, {useState, useEffect} from 'react';
import Bingo from "./Bingo";
import Call from "./Call";

function Play() {

    const [count, setCount] = useState(0);
    const [players, setPlayers] = useState([]);
    const [gameStarted, setGameStarted] = useState(false);

    const [callNumbers, setCallNumbers] = useState([]);

    const addPlayer = () => {
        if (count < 5) {
            setCount(prevCount => {
                setPlayers([...players, 'player ' + (prevCount + 1)]);
                return prevCount + 1;
            });
        } else {
            alert('최대 플레이어 수(5인)에 도달했습니다.');
        }
    }

    const startGame = () => {
        if (count >= 2) {
            setGameStarted(true);
        } else {
            alert('게임을 시작하려면 최소한 두 명의 플레이어가 필요합니다.');
        }
    }

    const resetGame = () => {
        setPlayers([]);
        setCount(0);
        setGameStarted(false);
    }

    return (
        <div>
            {!gameStarted ? (
                <div>
                    <button onClick={addPlayer}>플레이어 추가</button>
                    <button onClick={startGame}>시작</button>
                </div>
            ) : (
                <Call resetGame={resetGame} callNumbers={callNumbers} setCallNumbers={setCallNumbers} />
                )}
            <div>
                {players.map((player, index) => (
                    <div key={index}>
                        {player}
                        <Bingo callNumbers={callNumbers} />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Play;