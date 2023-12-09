import { useState, useEffect } from 'react';
import GodCardSelection from './GodCardSelection';

const useGameLogic = () => {
  const [gameState, setGameState] = useState({
    board: initializeBoard(),
    currentPlayer: 'Player1',
    phase: 'placement',
    validActions: initializeValidActions(),
    workersPlaced: 0,
  });

  const [playerGodCards, setPlayerGodCards] = useState({ player1: null, player2: null });
  const [error, setError] = useState(null);

  function initializeBoard() {
    return Array(5).fill().map(() => Array(5).fill({ hasWorker: false, level: 0 }));
  }

  const determineNextPhase = () => {
    switch (gameState.phase) {
      case 'placement':
        return gameState.workersPlaced + 1 < 4 ? 'placement' : 'move';
      case 'move':
        return 'build';
      case 'build':
        return 'move';
      default:
        return gameState.phase;
    }
  };

  function initializeValidActions() {
    let actions = [];
    for (let x = 0; x < 5; x++) {
      for (let y = 0; y < 5; y++) {
        actions.push({ x, y });
      }
    }
    return actions;
  }

  useEffect(() => {
    setGameState(prevState => ({
      ...prevState,
      validActions: initializeValidActions()
    }));
  }, [gameState.phase]);

  const initializeBoardAndActions = () => {
    const newBoard = Array(5).fill().map(() => Array(5).fill({ hasWorker: false, level: 0 }));
    setGameState({
      board: newBoard,
      currentPlayer: 'Player1',
      phase: 'placement',
      validActions: initializeValidActions(),
      workersPlaced: 0
    });
  };

  const handleNewGame = () => {
    setGameState({
      board: initializeBoard(),
      currentPlayer: 'Player1',
      phase: 'placement',
      validActions: initializeValidActions(),
      workersPlaced: 0,
    });
    setPlayerGodCards({ player1: null, player2: null });
    setError(null);
  };

  const checkWinner = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/checkWinner', {
        method: 'GET'
      });
      if (response.ok) {
        const winner = await response.json();
        if (winner) {
          alert(`Player ${winner} wins!`);
          handleNewGame();
        }
      } else {
        console.error('Error checking winner');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const performMove = (x, y) => {
    if (!gameState.validActions.some(action => action.x === x && action.y === y)) {
      setError('Invalid move');
      return;
    }

    const updatedBoard = gameState.board.map(row => row.slice());
    updatedBoard[x][y] = { ...updatedBoard[x][y], hasWorker: true, worker: gameState.currentPlayer };
    let newWorkersPlaced = gameState.workersPlaced + 1;

    setGameState(prevState => ({
      ...prevState,
      board: updatedBoard,
      workersPlaced: newWorkersPlaced,
      currentPlayer: newWorkersPlaced % 2 === 0 ? 'Player2' : 'Player1',
      phase: newWorkersPlaced >= 4 ? 'move' : 'placement',
    }));
  };

  const performAction = async (x, y, actionType) => {
    try {
      const response = await fetch(`http://localhost:8080/api/${actionType}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ x, y, player: gameState.currentPlayer })
      });
      if (response.ok) {
        const updatedGameState = await response.json();
        setGameState(updatedGameState);
      } else {
        const errorText = await response.text();
        setError(`Failed to ${actionType}: ${errorText}`);
      }
    } catch (error) {
      setError(`Error: ${error.message}`);
    }
  };

  const handleCellClick = async (x, y) => {
    if (gameState.phase === 'placement') {
      performMove(x, y);
    } else if (gameState.phase === 'move' || gameState.phase === 'build') {
      await performAction(x, y, gameState.phase);
    }
  };

  const handleGodCardSelect = async (card) => {
    const currentPlayer = gameState.currentPlayer;
    try {
      const response = await fetch('http://localhost:8080/api/selectGodCard', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ playerName: currentPlayer, godCardName: card })
      });
      if (response.ok) {
        setPlayerGodCards({ ...playerGodCards, [currentPlayer]: card });
      } else {
        console.error('Failed to select god card');
      }
    } catch (error) {
      console.error('Error:', error);
    }
    setGameState(prevState => ({ ...prevState, phase: 'placement' }));
  };

  const handleSkipAction = () => {
    const currentPlayer = gameState.currentPlayer;
    const nextPlayer = currentPlayer === 'Player1' ? 'Player2' : 'Player1';
    const nextPhase = gameState.phase === 'move' ? 'build' : 'move';
    setGameState({ ...gameState, currentPlayer: nextPlayer, phase: nextPhase });
    checkWinner();
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

  const handleBuildAction = async (x, y) => {
    try {
      const response = await fetch('http://localhost:8080/api/build', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ x, y, player: gameState.currentPlayer })
      });
      if (response.ok) {
        const updatedGameState = await response.json();
        setGameState(updatedGameState);
      } else {
        console.error('Failed to build');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const GodCardPrompt = () => {
    if (!playerGodCards.player1 || !playerGodCards.player2) {
      return (
        <div className="god-card-prompt">
          <GodCardSelection onGodCardSelect={handleGodCardSelect} />
        </div>
      );
    }
    return null;
  };

  return { gameState, setGameState, playerGodCards, setPlayerGodCards, error, setError, handleCellClick, GodCardPrompt };
};

export default useGameLogic;
