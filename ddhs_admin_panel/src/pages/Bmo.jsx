import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axiosInstance from "../services/axiosInstance";

export default function Bmo() {
  const navigate = useNavigate();


  const [bmoDetails, setBmoDetails] = useState(null);
  const [error, setError] = useState(null);

useEffect(() => {
  const fetchBmoDetails = async () => {
    try {
      const id = localStorage.getItem('id');
      const res = await axiosInstance.get(`/api/bmo/${id}`);
      setBmoDetails(res.data);
    } catch (error) {
      console.error('Error fetching BMO details:', error);
    }
  };

  fetchBmoDetails();
}, []);




  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50">
      {/* Header */}
      <header className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8 flex justify-between items-center">
          <div>
            <h1 className="text-3xl font-bold text-gray-800">BMO Dashboard</h1>
            <p className="text-gray-600 mt-1">Block Medical Officer Portal</p>
          </div>
          <span className="inline-flex items-center px-3 py-1 rounded-full bg-indigo-100 text-indigo-800 text-sm font-medium">
            BMO Access
          </span>
        </div>
      </header>

      {/* Welcome Section */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="bg-white rounded-lg shadow-md p-6 mb-8">
          <h2 className="text-2xl font-semibold text-gray-800 mb-2">Welcome back!</h2>
          <p className="text-gray-600">
            Manage your block's healthcare facilities and medical professionals.
          </p>
        </div>

        {/* Action Buttons */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* PHCs Card */}
          <div 
            className="bg-white rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow cursor-pointer"
            onClick={() => navigate(`/${bmoDetails.blockName}/facilities`)}
          >
            <div className="p-6 flex items-start">
              <div className="flex-shrink-0 bg-blue-500 rounded-lg p-3">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                </svg>
              </div>
              <div className="ml-4">
                <h3 className="text-lg font-semibold text-gray-800">PHC Management</h3>
                <p className="mt-1 text-gray-500">View and manage Primary Health Centers in your block</p>
                <button className="mt-4 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                onClick={() => navigate(`/${bmoDetails.blockName}/facilities`)}

                >
                  Manage PHCs
                  <svg xmlns="http://www.w3.org/2000/svg" className="ml-2 -mr-1 h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M14 5l7 7m0 0l-7 7m7-7H3" />
                  </svg>
                </button>
              </div>
            </div>
          </div>

          {/* Doctors Card */}
          <div 
            className="bg-white rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow cursor-pointer"
            onClick={() => navigate(`/block/${bmoDetails.blockName}/doctors`)}
          >
            <div className="p-6 flex items-start">
              <div className="flex-shrink-0 bg-green-500 rounded-lg p-3">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                </svg>
              </div>
              <div className="ml-4">
                <h3 className="text-lg font-semibold text-gray-800">Doctors</h3>
                <p className="mt-1 text-gray-500">Manage medical professionals in your block</p>
                <button className="mt-4 inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500">
                  View Doctors
                  <svg xmlns="http://www.w3.org/2000/svg" className="ml-2 -mr-1 h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M14 5l7 7m0 0l-7 7m7-7H3" />
                  </svg>
                </button>
              </div>
            </div>
          </div>
        </div>

        {/* Quick Stats (placeholder) */}
        <div className="mt-8 grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="bg-white p-6 rounded-lg shadow">
            <h4 className="text-gray-500 text-sm font-medium">Total PHCs</h4>
            <p className="mt-1 text-3xl font-semibold text-indigo-600">24</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <h4 className="text-gray-500 text-sm font-medium">Active Doctors</h4>
            <p className="mt-1 text-3xl font-semibold text-green-600">48</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow">
            <h4 className="text-gray-500 text-sm font-medium">Recent Activity</h4>
            <p className="mt-1 text-3xl font-semibold text-blue-600">12</p>
          </div>
        </div>
      </div>
    </div>
  );
}