import React from 'react';
import './App.css';
import GameBoard from './GameBoard';
import useGameLogic from './useGameLogic';

function App() {
  const { 
    gameState, 
    error, 
    setError, 
    handleNewGame, 
    handleSkipAction, 
    GodCardPrompt,
    handleCellClick 
  } = useGameLogic();

  return (
    <div className="App">
      {error && (
        <div className="error-message">
          {error} 
          <button onClick={() => setError(null)}>Close</button>
        </div>
      )}
      <h1>Santorini Game</h1>
      <div className="game-status">
        <p>Current Player: {gameState.currentPlayer}</p>
        <p>Phase: {gameState.phase}</p>
      </div>
      <GameBoard gameState={gameState} onCellClick={handleCellClick} />
      <button onClick={handleNewGame}>New Game</button>
      <button onClick={handleSkipAction}>Skip Action</button>
      <GodCardPrompt />
    </div>
  );
}

export default App;
