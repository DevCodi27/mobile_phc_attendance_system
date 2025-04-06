import axios from "axios";
import axiosInstance from "./axiosInstance";
const API_URL = "http://localhost:8080/users";

export const registerUser = async (userData) => {
  try {
    const response = await axiosInstance.post(API_URL +"/register", userData, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    
    console.log("User registered successfully:", response.data);
    return response.data; // Return response for further use (if needed)
  } catch (error) {
    console.error("Error registering user:", error.response?.data || error.message);
    throw error; // Re-throw error for handling in the UI
  }
};
