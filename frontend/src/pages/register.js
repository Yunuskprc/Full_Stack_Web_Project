import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function Register() {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    userName: "",
    password: "",
    name:"",
    last_name:""
  });

  const { userName, password, name, last_name } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
   
    axios.post("http://localhost:8080/Register", user)
    .then(response => {
        navigate("/");
  
    })
    .catch(error => {
        window.alert("Kullanıcı adı aynı.");
      console.error('Veri gönderilirken bir hata oluştu:', error); 
    });
  };

  return (
    <div className='container'>
      <div className='row'>
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
          <h2 className='text-center m-4'>Register User</h2>
          <form onSubmit={onSubmit}>
            <div className='mb-3'>
              <label className='form-label' htmlFor='userName'>Username:</label>
              <input type="text" className='form-control' placeholder='Enter userName:' name='userName' value={userName} onChange={onInputChange} />
            </div>

            <div className='mb-3'>
              <label className='form-label' htmlFor='password'>Password:</label>
              <input type="password" className='form-control' placeholder='Enter password:' name='password' value={password} onChange={onInputChange} />
            </div>

            <div className='mb-3'>
              <label className='form-label' htmlFor='name'>Name:</label>
              <input type="text" className='form-control' placeholder='Enter name:' name='name' value={name} onChange={onInputChange} />
            </div>

            <div className='mb-3'>
              <label className='form-label' htmlFor='lastName'>LastName:</label>
              <input type="text" className='form-control' placeholder='Enter surname:' name='last_name' value={last_name} onChange={onInputChange} />
            </div>



            <button type='submit' className='btn btn-outline-primary'>Submit</button>
            <Link to={"/"} className='btn btn-outline-danger mx-2'>Cancel</Link>
          </form>
        </div>
      </div>
    </div>
  )
}
