import React, { useState, useEffect } from 'react';
import api from '../services/api';
import './LoginForm.css';
import { Link } from 'react-router-dom';

const LoginForm = () => {
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [message, setMessage] = useState('');
  const [messageColor, setMessageColor] = useState('red');
  const [lockSeconds, setLockSeconds] = useState(0);

  useEffect(() => {
    let timer;
    if (lockSeconds > 0) {
      timer = setInterval(() => setLockSeconds(prev => prev - 1), 1000);
    }
    return () => clearInterval(timer);
  }, [lockSeconds]);

  const handleChange = e => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const res = await api.post('/login', formData);
      const msg = res.data;
      setMessage(msg);
      if (msg.includes('successful')) {
        setMessageColor('green');
      } else if (msg.includes('locked')) {
        const secondsMatch = msg.match(/(\d+)/);
        if (secondsMatch) {
          setLockSeconds(parseInt(secondsMatch[0]));
        }
        setMessageColor('red');
      } else {
        setMessageColor('red');
      }
    } catch (err) {
      setMessage('❌ Login failed. Server error.');
      setMessageColor('red');
    }
  };

  return (
    <div className="login-container">
      <h2>User Login</h2>
      <form onSubmit={handleSubmit}>
        <input type="email" name="email" placeholder="Email" required onChange={handleChange} />
        <input type="password" name="password" placeholder="Password" required onChange={handleChange} />
        <button type="submit" disabled={lockSeconds > 0}>Login</button>
      </form>

      <p className="message" style={{ color: messageColor }}>
        {lockSeconds > 0 ? `🔒 Account locked. Try again in ${lockSeconds} seconds.` : message}
      </p>

      <div className="forgot-link">
        <Link to="/reset-password">Forgot Password?</Link>
      </div>

      <div className="register-link">
        <span>Don't have an account? </span>
        <Link to="/">Register here</Link>
      </div>
    </div>
  );
};

export default LoginForm;
