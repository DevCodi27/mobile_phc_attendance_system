import React, { useEffect, useState } from 'react';
import axiosInstance from '../services/axiosInstance';

const AllDoctorsList = () => {
  const [doctors, setDoctors] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchDoctors = async () => {
      try {
        const response = await axiosInstance.get('/api/doctors/');
        setDoctors(response.data);
      } catch (error) {
        console.error('Failed to fetch doctors', error);
      } finally {
        setLoading(false);
      }
    };
    fetchDoctors();
  }, []);

  return (
    <div className="p-4 m-4">
      <h2 className="text-2xl font-semibold mb-4">Doctor List</h2>
      {loading ? (
        <div className="flex justify-center items-center">
          <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-gray-900"></div>
        </div>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full border border-gray-300">
            <thead>
              <tr className="bg-gray-200">
                <th className="p-2 border">Doctor ID</th>
                <th className="p-2 border">Full Name</th>
                <th className="p-2 border">Email</th>
                <th className="p-2 border">Specialization</th>
              </tr>
            </thead>
            <tbody>
              {doctors.map((doctor) => (
                <tr key={doctor.doctorId} className="hover:bg-gray-100">
                  <td className="p-2 border text-center">{doctor.doctorId}</td>
                  <td className="p-2 border">{doctor.fullName}</td>
                  <td className="p-2 border">{doctor.email}</td>
                  <td className="p-2 border">{doctor.specialization}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default AllDoctorsList;
