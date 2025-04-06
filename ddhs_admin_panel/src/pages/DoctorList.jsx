import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axiosInstance from "../services/axiosInstance";

const DoctorList = () => {
  const { facilityId } = useParams();
  const [doctors, setDoctors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [shiftUpdates, setShiftUpdates] = useState({});
  const [editing, setEditing] = useState({});
  const [userRole, setUserRole] = useState("BMO"); // Mock role, replace with actual role fetching logic

  useEffect(() => {
    const fetchDoctors = async () => {
      try {
        const response = await axiosInstance.get(`/api/doctors/${facilityId}/doctors`);
        setDoctors(response.data);
      } catch (err) {
        console.error("Error fetching doctors:", err);
        setError("Failed to fetch doctors");
      } finally {
        setLoading(false);
      }
    };

    fetchDoctors();
  }, [facilityId]);

  const updateShift = async (doctorId) => {
    try {
      const response = await axiosInstance.put(`/api/doctors/${doctorId}/update-shift`, {
        startTime: shiftUpdates[doctorId]?.startTime || "",
        endTime: shiftUpdates[doctorId]?.endTime || ""
      });
      alert("Shift updated successfully");
      setEditing((prev) => ({ ...prev, [doctorId]: false }));
      console.log(response.data);
    } catch (err) {
      console.error("Error updating shift:", err);
      alert("Failed to update shift");
    }
  };

  const verifyShift = async (doctorId) => {
    try {
      await axiosInstance.put(`/api/doctors/${doctorId}/verify-shift`);
      alert("Shift verified successfully");
    } catch (err) {
      console.error("Error verifying shift:", err);
      alert("Failed to verify shift");
    }
  };

  const handleInputChange = (doctorId, field, value) => {
    setShiftUpdates((prev) => ({
      ...prev,
      [doctorId]: { ...prev[doctorId], [field]: value }
    }));
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-2">Doctors in Facility</h2>

      {loading ? (
        <p className="text-gray-500">Loading doctors...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : doctors.length > 0 ? (
        <ul className="bg-gray-100 p-4 rounded-lg shadow-md">
          {doctors.map((doctor) => (
            <li key={doctor.doctorId} className="py-2 border-b last:border-none flex flex-col gap-2">
              <div>
                <span className="block font-semibold">{doctor.name} - {doctor.specialization}</span>
                <span className="text-sm text-gray-600">Shift: {doctor.startTime} - {doctor.endTime}</span>
              </div>
              {editing[doctor.doctorId] ? (
                <div className="flex gap-2 items-center">
                  <input
                    type="time"
                    value={shiftUpdates[doctor.doctorId]?.startTime || ""}
                    onChange={(e) => handleInputChange(doctor.doctorId, "startTime", e.target.value)}
                    className="border p-1 rounded"
                  />
                  <input
                    type="time"
                    value={shiftUpdates[doctor.doctorId]?.endTime || ""}
                    onChange={(e) => handleInputChange(doctor.doctorId, "endTime", e.target.value)}
                    className="border p-1 rounded"
                  />
                  <button
                    onClick={() => updateShift(doctor.doctorId)}
                    className="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600"
                  >
                    Update
                  </button>
                  <button
                    onClick={() => setEditing((prev) => ({ ...prev, [doctor.doctorId]: false }))}
                    className="bg-gray-500 text-white px-3 py-1 rounded hover:bg-gray-600"
                  >
                    Cancel
                  </button>
                </div>
              ) : (
                userRole === "BMO" && (
                  <button
                    onClick={() => setEditing((prev) => ({ ...prev, [doctor.doctorId]: true }))}
                    className="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600"
                  >
                    Edit Shift
                  </button>
                )
              )}
              {userRole === "DHO" && (
                <button
                  onClick={() => verifyShift(doctor.doctorId)}
                  className="bg-purple-500 text-white px-3 py-1 rounded hover:bg-purple-600"
                >
                  Verify Shift
                </button>
              )}
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-gray-500">No doctors found</p>
      )}
    </div>
  );
};

export default DoctorList;
