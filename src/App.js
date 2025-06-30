// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RegisterForm from './components/RegisterForm';
import LoginForm from './components/LoginForm';
import ResetPasswordForm from './components/ResetPasswordForm';
import SocialIcons from './components/SocialIcons'; // ✅

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<RegisterForm />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/reset-password" element={<ResetPasswordForm />} />
        </Routes>
        <SocialIcons /> {/* ✅ This will be shown on every page */}
      </div>
    </Router>
  );
}

export default App;
// Note: The SocialIcons component is imported and used in the App component to ensure it appears on every page.