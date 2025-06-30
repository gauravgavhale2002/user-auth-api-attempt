import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../services/api';
import './ResetPasswordForm.css';


const ResetPasswordForm = () => {
  const [formData, setFormData] = useState({
    email: '',
    secretAnswer1: '',
    secretAnswer2: '',
    secretAnswer3: '',
    secretAnswer4: '',
    newPassword: '',
    confirmPassword: ''
  });

  const [message, setMessage] = useState('');
  const [messageColor, setMessageColor] = useState('red');

  const handleChange = (e) => {
    setFormData({...formData, [e.target.name]: e.target.value});
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post('/reset-password', formData);
      setMessage(res.data);
      setMessageColor(res.data.includes('✅') ? 'green' : 'red');
    } catch (err) {
      setMessage('❌ Failed to reset password');
      setMessageColor('red');
    }
  };

  return (
    <div className="reset-container">
      <h2>Reset Password</h2>
      <form onSubmit={handleSubmit}>
        <input type="email" name="email" placeholder="Email" required onChange={handleChange} />
        <input type="text" name="secretAnswer1" placeholder="Favorite color?" required onChange={handleChange} />
        <input type="text" name="secretAnswer2" placeholder="Nickname?" required onChange={handleChange} />
        <input type="text" name="secretAnswer3" placeholder="Birth place?" required onChange={handleChange} />
        <input type="text" name="secretAnswer4" placeholder="Pet name?" required onChange={handleChange} />
        <input type="password" name="newPassword" placeholder="New Password" required onChange={handleChange} />
        <input type="password" name="confirmPassword" placeholder="Confirm New Password" required onChange={handleChange} />
        <button type="submit">Reset Password</button>
      </form>
      
      <p className="message" style={{ color: messageColor }}>{message}</p>

      <div className="back-link">
        🔙 <Link to="/login">Back to Login</Link>
      </div>
    </div>
  );
};

export default ResetPasswordForm;
