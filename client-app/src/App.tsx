import React from 'react';
import './App.css';
import AppRoutes from "./AppRoutes/AppRoutes";
import { BrowserRouter as Router } from 'react-router-dom';

function App() {
  return (
      <Router> {/* Wrap your Routes in BrowserRouter */}
        <AppRoutes />
      </Router>
  );
}

export default App;
