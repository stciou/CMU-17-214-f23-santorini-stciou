import React from 'react';

const Board = ({ gameState, onCellClick }) => {
  const renderCell = (x, y) => {
    const cellState = gameState.board[x][y];
    const cellContent = cellState.hasWorker ? 'W' : cellState.level;

    return (
      <button className="cell" onClick={() => onCellClick(x, y)}>
        {cellContent}
      </button>
    );
  };

  const renderRow = y => {
    return (
      <div className="row" key={`row-${y}`}>
        {Array.from({ length: 5 }, (_, x) => renderCell(x, y))}
      </div>
    );
  };

  return (
    <div className="board">
      {Array.from({ length: 5 }, (_, y) => renderRow(y))}
    </div>
  );
};

export default Board;
