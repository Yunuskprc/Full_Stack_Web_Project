import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';


function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = {
        userName:username,
        password:password
    };

    axios.post("http://localhost:8080/Login", data, {withCredentials: true})
    .then(response => {
        navigate("/Anasayfa");
    })
    .catch(error => {
        window.alert("Kullanıcı adı veya şifre hatalı");
        console.error('Veri gönderilirken bir hata oluştu:', error); 
    });
  };



  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              Login
            </div>
            <div className="card-body">
              <form onSubmit={handleSubmit}>
                <div className="form-group">
                  <label htmlFor="username">Username</label>
                  <input 
                    type="text" 
                    className="form-control" 
                    id="username" 
                    placeholder="Enter username"
                    value={username}
                    onChange={handleUsernameChange}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="password">Password</label>
                  <input 
                    type="password" 
                    className="form-control" 
                    id="password" 
                    placeholder="Enter password"
                    value={password}
                    onChange={handlePasswordChange}
                  />
                </div>
                <button type="submit" className="btn btn-primary btn-block">Login</button>
              </form>
              <Link to={"/Register"} className="btn btn-secondary btn-block mt-3">Register</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
