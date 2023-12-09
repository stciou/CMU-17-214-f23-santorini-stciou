import React from 'react';

const GameBoard = ({ gameState, onCellClick }) => {
  const renderCell = (x, y) => {
    const cellState = gameState.board[x][y];
    const isWorker = cellState.hasWorker;
    const level = cellState.level;
    const isDome = cellState.hasDome;
    const worker = isWorker ? cellState.worker : ' ';
    const cellContent = isDome ? `[[[O]]]` : `[${'['.repeat(level)}${worker}${']'.repeat(level)}]`;

    const isValidAction = gameState.validActions.some(action => action.x === x && action.y === y);
    const cellClass = `cell ${isValidAction ? 'valid-action' : ''}`;

    return (
      <button 
        className={cellClass} 
        onClick={() => isValidAction && onCellClick(x, y)}
      >
        {cellContent}
      </button>
    );
  };

  const renderRow = x => {
    return (
      <div className="row" key={`row-${x}`}>
        {Array.from({ length: 5 }, (_, y) => renderCell(x, y))}
      </div>
    );
  };

  return (
    <div className="board">
      {Array.from({ length: 5 }, (_, x) => renderRow(x))}
    </div>
  );
};

export default GameBoard;