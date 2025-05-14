import React, { useEffect, useState } from 'react';
import axios from 'axios';

const PendingShifts = () => {
  const [shifts, setShifts] = useState([]);

  useEffect(() => {
    fetchPendingShifts();
  }, []);

  const fetchPendingShifts = async () => {
    try {
      const response = await axios.get('/api/shifts/pending');
      setShifts(response.data);
    } catch (error) {
      console.error('Error fetching pending shifts:', error);
    }
  };

  const updateShiftStatus = async (id, newStatus) => {
    try {
      await axios.put(`/api/shifts/${id}/status`, { status: newStatus });
      fetchPendingShifts(); // Refresh after update
    } catch (error) {
      console.error(`Failed to update status to ${newStatus}`, error);
    }
  };

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Pending Shifts</h1>
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white shadow rounded">
          <thead className="bg-gray-100">
            <tr>
              <th className="py-2 px-4 text-left">Date</th>
              <th className="py-2 px-4 text-left">Start Time</th>
              <th className="py-2 px-4 text-left">End Time</th>
              <th className="py-2 px-4 text-left">Assigned By</th>
              <th className="py-2 px-4 text-left">Actions</th>
            </tr>
          </thead>
<tbody>
  {Array.isArray(shifts) && shifts.length > 0 ? (
    shifts.map(shift => (
      <tr key={shift.id} className="border-t">
        <td className="py-2 px-4">{shift.date}</td>
        <td className="py-2 px-4">{shift.startTime}</td>
        <td className="py-2 px-4">{shift.endTime}</td>
        <td className="py-2 px-4">{shift.assignedBy?.username || 'N/A'}</td>
        <td className="py-2 px-4 flex gap-2">
          <button
            onClick={() => updateShiftStatus(shift.id, 'APPROVED')}
            className="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600"
          >
            Approve
          </button>
          <button
            onClick={() => updateShiftStatus(shift.id, 'REJECTED')}
            className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
          >
            Reject
          </button>
        </td>
      </tr>
    ))
  ) : (
    <tr>
      <td colSpan="5" className="text-center py-4 text-gray-500">
        No pending shifts found.
      </td>
    </tr>
  )}
</tbody>

        </table>
      </div>
    </div>
  );
};

export default PendingShifts;
