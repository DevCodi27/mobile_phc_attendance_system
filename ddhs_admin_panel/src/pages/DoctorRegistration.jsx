import { useState, useEffect } from "react";
import axiosInstance from "../services/axiosInstance";

const DoctorRegistration = () => {
  const [facilities, setFacilities] = useState([]);
  const [formData, setFormData] = useState({
    doctorId: "",
    name: "",
    password: "",
    email: "",
    specialization: "",
    facility: "",
    role: { roleId: 1, name: "Doctor" },
  });

  // Fetch facilities on mount
  useEffect(() => {
    axiosInstance.get("http://localhost:8080/api/facilities/names")
      .then(response => {
        setFacilities(response.data);
      })
      .catch(error => {
        console.error("Failed to fetch facilities:", error);
      });
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axiosInstance.post("http://localhost:8080/api/doctors/register",formData)
    .then(response => console.log("Doctor Registered:", response))
    .catch(error => {
        console.error("Failed to register:", error);
      });
    };

  return (
    <div className="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg mt-10">
      <h2 className="text-xl font-semibold mb-4">Doctor Registration</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
      
        <input 
          type="text" 
          name="name" 
          placeholder="Full Name" 
          value={formData.name} 
          onChange={handleChange} 
          className="w-full p-2 border rounded-lg" 
          required
        />
        <input 
          type="password" 
          name="password" 
          placeholder="Password" 
          value={formData.password} 
          onChange={handleChange} 
          className="w-full p-2 border rounded-lg" 
          required
        />
        <input 
          type="email" 
          name="email" 
          placeholder="Email" 
          value={formData.email} 
          onChange={handleChange} 
          className="w-full p-2 border rounded-lg" 
          required
        />
        <input 
          type="text" 
          name="specialization" 
          placeholder="Specialization" 
          value={formData.specialization} 
          onChange={handleChange} 
          className="w-full p-2 border rounded-lg" 
          required
        />
        
        {/* Dropdown for Facilities */}
        <select
          name="facility"
          value={formData.facility}
          onChange={handleChange}
          className="w-full p-2 border rounded-lg"
          required
        >
          <option value="">Select a Facility</option>
          {facilities.map((facility) => (
            <option key={facility.id} value={facility.id}>
              {facility.name}
            </option>
          ))}
        </select>

        <button 
          type="submit" 
          className="w-full bg-blue-500 text-white p-2 rounded-lg hover:bg-blue-600"
        >
          Register
        </button>
      </form>
    </div>
  );
};

export default DoctorRegistration;
