import axios from "axios";

const API_URL = "http://localhost:8080/login";

export const login = async (email, password) => {
    try {
        const response = await axios.post(API_URL, { email, password });
        return response.data; // Includes token, username, role, and blockId
    } catch (error) {
        console.error("Login failed", error);
        throw error;
    }
};
