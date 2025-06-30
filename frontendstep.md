
# 🔐 User Authentication Frontend (React.js)

This is a complete frontend React application for user authentication (Register, Login, Reset Password), connected to a Spring Boot + MySQL backend.

---

## 🏁 Step-by-Step Setup

### 1. 📦 Create React Project

```bash
npx create-react-app user-auth-frontend
cd user-auth-frontend
```

### 2. 📁 Create Folder Structure

```bash
mkdir src/components
mkdir src/services
```

### 3. 📄 Project Structure

```
user-auth-frontend/
├── public/
├── src/
│   ├── components/
│   │   ├── RegisterForm.js
│   │   ├── LoginForm.js
│   │   ├── ResetPasswordForm.js
│   │   ├── SocialIcons.js
│   │   ├── RegisterForm.css
│   │   ├── LoginForm.css
│   │   ├── ResetPasswordForm.css
│   │   └── SocialIcons.css
│   ├── services/
│   │   └── api.js
│   ├── App.js
│   └── index.js
├── package.json
└── README.md
```

---

## 🔌 Install Dependencies

```bash
npm install react-router-dom axios
```

---

## ⚙️ API Configuration

### ➤ `src/services/api.js`

```js
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080/api/auth", // Backend URL
});

export default api;
```

---

## 🧠 Components

### 🔹 RegisterForm

Handles registration with email, password and 4 secret questions.

**File:** `src/components/RegisterForm.js`

🔹 **CSS:** `RegisterForm.css`  

---

### 🔹 LoginForm

Handles login with email/password and lockout timer.

**File:** `src/components/LoginForm.js`

🔹 **CSS:** `LoginForm.css`  

---

### 🔹 ResetPasswordForm

Verifies secret answers and resets password.

**File:** `src/components/ResetPasswordForm.js`

🔹 **CSS:** `ResetPasswordForm.css`  

---

### 🔹 SocialIcons (Footer)

**File:** `src/components/SocialIcons.js`

🔹 **CSS:** `SocialIcons.css`

```css
.social-icons {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
}

.social-btn {
  text-decoration: none;
  padding: 8px 14px;
  border-radius: 8px;
  font-size: 14px;
  font-weight: 500;
  color: white;
  transition: all 0.3s ease-in-out;
}

.social-btn:hover {
  opacity: 0.85;
  transform: translateY(-2px);
}

.fb { background-color: #3b5998; }
.yt { background-color: #FF0000; }
.google { background-color: #DB4437; }
.tiktok { background-color: #000000; }
.twitter { background-color: #1DA1F2; }
```

---

## 🔀 Routing Setup

### ➤ `src/App.js`

```js
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RegisterForm from './components/RegisterForm';
import LoginForm from './components/LoginForm';
import ResetPasswordForm from './components/ResetPasswordForm';
import SocialIcons from './components/SocialIcons';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<RegisterForm />} />
          <Route path="/login" element={<LoginForm />} />
          <Route path="/reset-password" element={<ResetPasswordForm />} />
        </Routes>
        <SocialIcons />
      </div>
    </Router>
  );
}

export default App;
```

---

## ▶️ Run the Project

```bash
npm start
```

Then open:  
🌐 [http://localhost:3000](http://localhost:3000)

---

## 🔗 Backend API Connection

Ensure the backend Spring Boot server is running on:  
`http://localhost:8080/api/auth`

Endpoints used:
- `POST /register`
- `POST /login`
- `POST /reset-password`

---

## 👨‍💻 Author

**Gaurav Gavhale**  
React + Spring Boot Full Stack Auth Project 🔐
