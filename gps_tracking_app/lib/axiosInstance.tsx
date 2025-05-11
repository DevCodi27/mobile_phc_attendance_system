// lib/axiosInstance.ts
import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';

const axiosInstance = axios.create({
  baseURL: "http://192.168.209.174:8080/", // your backend URL
   headers: { 'Content-Type': 'application/json' },
});

// Automatically attach JWT token
axiosInstance.interceptors.request.use(
  async (config) => {
    const token = await AsyncStorage.getItem('token'); // stored after login
    console.log(token);
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosInstance;
