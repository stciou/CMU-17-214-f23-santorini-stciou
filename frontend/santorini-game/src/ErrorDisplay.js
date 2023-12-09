import React from 'react';

const ErrorDisplay = ({ errorMessage, clearError }) => {
  if (!errorMessage) return null;

  return (
    <div className="error-message">
      {errorMessage}
      <button onClick={clearError}>Close</button>
    </div>
  );
};

export default ErrorDisplay;