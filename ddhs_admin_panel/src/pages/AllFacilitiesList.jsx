import React, { useEffect, useState } from 'react';
import axiosInstance from '../services/axiosInstance';

const AllFacilitiesList = () => {
  const [facilities, setFacilities] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchFacilities = async () => {
      try {
        const response = await axiosInstance.get('/api/facilities/names');
        setFacilities(response.data);
      } catch (error) {
        console.error('Failed to fetch facilities', error);
      } finally {
        setLoading(false);
      }
    };
    fetchFacilities();
  }, []);

  return (
    <div className="p-4 m-4">
      <h2 className="text-2xl font-semibold mb-4">Facility List</h2>
      {loading ? (
        <div className="flex justify-center items-center">
          <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-gray-900"></div>
        </div>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full border border-gray-300">
            <thead>
              <tr className="bg-gray-200">
                <th className="p-2 border">Facility ID</th>
                <th className="p-2 border">Facility Name</th>
                <th className="p-2 border">Block</th>
                <th className="p-2 border">District</th>
                <th className="p-2 border">Facility Type</th>
              </tr>
            </thead>
            <tbody>
              {facilities.map((facility) => (
                <tr key={facility.id} className="hover:bg-gray-100">
                  <td className="p-2 border text-center">{facility.id}</td>
                  <td className="p-2 border">{facility.name}</td>
                  <td className="p-2 border">{facility.block}</td>
                  <td className="p-2 border">{facility.district}</td>
                  <td className="p-2 border">{facility.facilityType}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default AllFacilitiesList;
