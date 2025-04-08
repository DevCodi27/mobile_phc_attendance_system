import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from "../services/AuthService";
import axios from 'axios'
import axiosInstance from "../services/axiosInstance";
export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
        // ✅ API Call to Spring Boot Backend
        const response = await login(email, password);

        console.log(response);
        // Store the Token and Role in LocalStorage
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);
        localStorage.setItem('id', response.id);
        console.log(response);
        // Redirect Based on Role
        if (response.role === 'ADMIN') {
          navigate('/admin');
        } else if (response.role === 'DHO') {
          navigate('/blocks');
        }  else if (response.role === 'BMO') {
          const bmo = await fetchBmoDetails(response.id);
          // console.log(bmo);
          navigate(`/${bmo.blockName}/facilities`);
        } else {
          navigate('/');
        }
    } catch (error) {
      // ✅ Handle Login Failure
      if (error.response && error.response.status === 401) {
        alert('Invalid Credentials');
      } else {
        alert('Something went wrong. Please try again.' + error);
      }
    }
  };

// ✅ Function to Fetch BMO Details
  const fetchBmoDetails = async (bmoId) => {
    try {
      const token = localStorage.getItem('token'); // Retrieve token for authentication
      const res = await axiosInstance.get(`/users/${bmoId}`);
      console.log('BMO Details:', res.data);
      // navigate(`/${res.data.blockName}/facilities`)
      // console.log(res);
      // localStorage.setItem('bmoDetails', JSON.stringify(res.data));
      return res.data;
    } catch (error) {
      console.error('Error fetching BMO details:', error);
    }
  };  

  return (
    <div>
      <h1>Login</h1>
      <form onSubmit={handleLogin}>
        <div>
          <label>Email:</label>
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            required 
          />
        </div>
        <div>
          <label>Password:</label>
          <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)} 
            required 
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}
