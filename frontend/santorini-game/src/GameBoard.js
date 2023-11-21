import React from 'react';

const GameBoard = ({ gameState, onCellClick }) => {
  const renderCell = (x, y) => {
    const renderCellContent = (cellState) => {
      let content = '';
      for (let i = 0; i < cellState.level; i++) {
        content += '[[';
      }
      if (cellState.hasWorker) {
        content += ` ${cellState.worker} `;
      }
      for (let i = 0; i < cellState.level; i++) {
        content += ']]';
      }
      return content;
    };

    const cellState = gameState.board[x][y];
    const cellContent = renderCellContent(cellState);

    return (
      <button className="cell" onClick={() => onCellClick(x, y)} disabled={cellState.isOccupied}>
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
