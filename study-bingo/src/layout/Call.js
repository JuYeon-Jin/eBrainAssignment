import React, {useState} from 'react';

function Call({ resetGame, callNumbers, setCallNumbers }) {

    const calling = () => {
        let number = Math.floor(Math.random() * 15) + 1;
        if (callNumbers.length < 15) {
            if (!callNumbers.includes(number)) {
                setCallNumbers([...callNumbers, number]);
            } else {
                calling();
            }
        } else {
            alert("우승자가 존재하지 않습니다.")
            resetGame();
        }
    }

    return (
        <div>
            <div>
                <button onClick={resetGame}>Reset</button>
                <button onClick={calling}>Call</button>
            </div>
            <div>
                {callNumbers.map((callNumber, index) => (
                    <span key={index}>{callNumber}  , </span>
                ))}
            </div>
        </div>
    );
}

export default Call;