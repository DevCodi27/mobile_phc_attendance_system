import { useState } from "react";
import axiosInstance from "../services/axiosInstance";

const DoctorRegistration = () => {
  const [formData, setFormData] = useState({
    doctorId: "",
    name: "",
    password: "",
    email: "",
    specialization: "",
    facility: "",
    role: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Doctor Registered:", formData);
    // Here, you can integrate an API call to register the doctor
  };

  return (
    <div className="max-w-md mx-auto bg-white p-6 rounded-lg shadow-lg mt-10">
      <h2 className="text-xl font-semibold mb-4">Doctor Registration</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input 
          type="text" 
          name="doctorId" 
          placeholder="Doctor ID" 
          value={formData.doctorId} 
          onChange={handleChange} 
          className="w-full p-2 border rounded-lg" 
          required
        />
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
        <input 
          type="text" 
          name="facility" 
          placeholder="Facility" 
          value={formData.facility} 
          onChange={handleChange} 
          className="w-full p-2 border rounded-lg" 
          required
        />
        <input 
          type="text" 
          name="role" 
          placeholder="Role" 
          value={formData.role} 
          onChange={handleChange} 
          className="w-full p-2 border rounded-lg" 
          required
        />
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
