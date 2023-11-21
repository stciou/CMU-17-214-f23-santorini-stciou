import React, { useState } from 'react';
import './App.css';
import GameBoard from './GameBoard';

function App() {
  const [gameState, setGameState] = useState({
    board: Array(5).fill().map(() => Array(5).fill({})),
    currentPlayer: 'Player1',
    phase: 'placement',
  });

  const handleCellClick = (x, y) => {
    const phase = gameState.phase;
    const currentPlayer = gameState.currentPlayer;
    const currentCell = gameState.board[x][y];

    if (phase === 'placement') {
      if (!currentCell.hasWorker) {
        const newBoard = [...gameState.board];
        newBoard[x][y] = { ...currentCell, hasWorker: true, worker: currentPlayer };
        setGameState({ ...gameState, board: newBoard });
        const nextPlayer = currentPlayer === 'Player1' ? 'Player2' : 'Player1';
        setGameState({ ...gameState, currentPlayer: nextPlayer });
      }

      const winner = checkWinner(gameState.board, currentPlayer);
      if (winner) {
        alert(`Player ${winner} wins!`);
        handleNewGame();
      }
    } else if (phase === 'move' || phase === 'build') {
      const isValidMove = checkValidMove(gameState.board, x, y);
      if (isValidMove) {
        const updatedBoard = performMove(gameState.board, x, y);
        const nextPlayer = currentPlayer === 'Player1' ? 'Player2' : 'Player1';
        setGameState({ ...gameState, board: updatedBoard, currentPlayer: nextPlayer });
        const winner = checkWinner(updatedBoard, nextPlayer);
        if (winner) {
          alert(`Player ${winner} wins!`);
          handleNewGame();
        } else {
          setGameState({ ...gameState, phase: 'build' });
        }
      }
    }
  };

  const handleNewGame = () => {
    setGameState({
      board: Array(5).fill().map(() => Array(5).fill({})),
      currentPlayer: 'Player1',
      phase: 'placement',
    });
  };

  const handleSkipAction = () => {
    const currentPlayer = gameState.currentPlayer;
    const nextPlayer = currentPlayer === 'Player1' ? 'Player2' : 'Player1';
    const nextPhase = gameState.phase === 'move' ? 'build' : 'move';
    setGameState({ ...gameState, currentPlayer: nextPlayer, phase: nextPhase });
  };

  const checkWinner = (board, currentPlayer) => {
    for (let x = 0; x < 5; x++) {
      for (let y = 0; y < 5; y++) {
        const cellState = board[x][y];
        if (cellState.hasWorker && cellState.level === 3 && cellState.worker === currentPlayer) {
          return currentPlayer;
        }
      }
    }
    return null;
  };

  const checkValidMove = (board, x, y) => {
    const currentCell = board[x][y];
    const adjacentCells = getAdjacentCells(board, x, y);

    if (currentCell.hasWorker) {
      return false;
    }

    for (const cell of adjacentCells) {
      if (!cell.hasWorker && cell.level - currentCell.level <= 1) {
        return true;
      }
    }
    return false;
  };

  const performMove = (board, x, y) => {
    const currentCell = board[x][y];
    const updatedBoard = [...board];

    updatedBoard[x][y] = { ...currentCell, hasWorker: true, worker: gameState.currentPlayer };

    return updatedBoard;
  };

  const getAdjacentCells = (board, x, y) => {
    const adjacentCells = [];
    for (let i = x - 1; i <= x + 1; i++) {
      for (let j = y - 1; j <= y + 1; j++) {
        if (i >= 0 && i < 5 && j >= 0 && j < 5) {
          adjacentCells.push(board[i][j]);
        }
      }
    }
    return adjacentCells;
  };

  return (
    <div className="App">
      <h1>Santorini Game</h1>
      <div className="game-status">
        <p>Current Player: {gameState.currentPlayer}</p>
        <p>Phase: {gameState.phase}</p>
      </div>
      <GameBoard gameState={gameState} onCellClick={handleCellClick} />
      <button onClick={handleNewGame}>New Game</button>
      <button onClick={handleSkipAction}>Skip Action</button>
    </div>
  );
}

export default App;
