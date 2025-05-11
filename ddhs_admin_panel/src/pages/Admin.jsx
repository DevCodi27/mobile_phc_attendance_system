import React from 'react';
import { useNavigate } from "react-router-dom";

export default function Admin() {
    const navigate = useNavigate();
    
    return (
        <div className="min-h-screen bg-gray-50 p-8">
            <h1 className="text-3xl font-bold text-gray-800 mb-8">Admin Dashboard</h1>
            
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {/* Register User Card */}
                <div 
                    className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow cursor-pointer border-l-4 border-blue-500"
                    onClick={() => navigate("/register")}
                >
                    <div className="flex items-center mb-4">
                        <div className="p-3 rounded-full bg-blue-100 mr-4">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-blue-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
                            </svg>
                        </div>
                        <h2 className="text-xl font-semibold text-gray-700">Register User</h2>
                    </div>
                    <p className="text-gray-500">Create new user accounts with specific roles and permissions</p>
                </div>

                {/* Register Doctor Card */}
                <div 
                    className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow cursor-pointer border-l-4 border-green-500"
                    onClick={() => navigate("/doctor-registration")}
                >
                    <div className="flex items-center mb-4">
                        <div className="p-3 rounded-full bg-green-100 mr-4">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-green-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10" />
                            </svg>
                        </div>
                        <h2 className="text-xl font-semibold text-gray-700">Register Doctor</h2>
                    </div>
                    <p className="text-gray-500">Add new doctors to the system with their specialization details</p>
                </div>

                {/* Register PHC Card */}
                <div 
                    className="bg-white p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow cursor-pointer border-l-4 border-purple-500"
                    onClick={() => navigate("/register-phc")}
                >
                    <div className="flex items-center mb-4">
                        <div className="p-3 rounded-full bg-purple-100 mr-4">
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-purple-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                            </svg>
                        </div>
                        <h2 className="text-xl font-semibold text-gray-700">Register PHC</h2>
                    </div>
                    <p className="text-gray-500">Register new Primary Health Centers with location and facility details</p>
                </div>
            </div>
        </div>
    );
}