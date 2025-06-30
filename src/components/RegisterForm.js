import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../services/api';
import './RegisterForm.css';

const RegisterForm = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    confirmPassword: '',
    secretAnswer1: '',
    secretAnswer2: '',
    secretAnswer3: '',
    secretAnswer4: ''
  });

  const [message, setMessage] = useState('');
  const [messageColor, setMessageColor] = useState('red');

  const handleChange = (e) => {
    setFormData({...formData, [e.target.name]: e.target.value});
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post('/register', formData);
      setMessage(res.data);
      setMessageColor(res.data.includes('✅') ? 'green' : 'red');
    } catch (err) {
      setMessage('❌ Error occurred during registration');
      setMessageColor('red');
    }
  };

  return (
    <div className="register-container">
      <h2>User Registration</h2>
      <form onSubmit={handleSubmit}>
        <input type="email" name="email" placeholder="Email" required onChange={handleChange} />
        <input type="password" name="password" placeholder="Password" required onChange={handleChange} />
        <input type="password" name="confirmPassword" placeholder="Confirm Password" required onChange={handleChange} />
        <input type="text" name="secretAnswer1" placeholder="What is your favorite color?" required onChange={handleChange} />
        <input type="text" name="secretAnswer2" placeholder="Your childhood nickname?" required onChange={handleChange} />
        <input type="text" name="secretAnswer3" placeholder="Where were you born?" required onChange={handleChange} />
        <input type="text" name="secretAnswer4" placeholder="Your favorite pet's name?" required onChange={handleChange} />
        <button type="submit">Register</button>
      </form>
      
      {message && <p className="message" style={{ color: messageColor }}>{message}</p>}

      <p className="login-redirect">
        🔐 Already have an account? <Link to="/login">Login here</Link>
      </p>
    </div>
  );
};

export default RegisterForm;
