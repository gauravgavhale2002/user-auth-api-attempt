// src/components/SocialIcons.js
import React from 'react';
import './SocialIcons.css';

const SocialIcons = () => {
  return (
    <div className="social-icons">
      <a href="https://facebook.com" target="_blank" rel="noopener noreferrer" className="social-btn fb">Facebook</a>
      <a href="https://youtube.com" target="_blank" rel="noopener noreferrer" className="social-btn yt">YouTube</a>
      <a href="https://google.com" target="_blank" rel="noopener noreferrer" className="social-btn google">Google</a>
      <a href="https://www.tiktok.com" target="_blank" rel="noopener noreferrer" className="social-btn tiktok">TikTok</a>
      <a href="https://twitter.com" target="_blank" rel="noopener noreferrer" className="social-btn twitter">Twitter</a>
    </div>
  );
};

export default SocialIcons;
