import React from 'react';
import { useNavigate } from "react-router-dom";

export default function Admin() {
    const navigate = useNavigate();
console.log("Admin Loaded");
  return (
    <div>
       <div className="flex space-x-4 p-4">
      <button 
        className="bg-blue-500 text-white px-4 py-2 rounded-lg shadow-lg hover:bg-blue-600"
        onClick={() => navigate("/register")}
      >
        Register User
      </button>
      <button 
        className="bg-green-500 text-white px-4 py-2 rounded-lg shadow-lg hover:bg-green-600"
        onClick={() => navigate("/register")}
      >
        Register Doctor
      </button>
      <button 
        className="bg-green-500 text-white px-4 py-2 rounded-lg shadow-lg hover:bg-green-600"
        onClick={() => navigate("/register-phc")}
      >
        Register PHC
      </button>
    </div>
    </div>
  );
}




