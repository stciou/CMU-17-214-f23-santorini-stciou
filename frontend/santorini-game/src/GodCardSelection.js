import React from 'react';

const GodCardSelection = ({ onGodCardSelect }) => {
  const godCards = ['Demeter', 'Hephaestus', 'Minotaur', 'Pan']; // Add more as needed

  const handleSelect = (card) => {
    onGodCardSelect(card);
  };

  return (
    <div>
      <h3>Select your God Card:</h3>
      {godCards.map(card => (
        <button key={card} onClick={() => handleSelect(card)}>{card}</button>
      ))}
    </div>
  );
};

export default GodCardSelection;
