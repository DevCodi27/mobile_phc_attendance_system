import React, { useState } from "react";
import { registerUser } from "../services/UserService"; // Import function
import axiosInstance from "../services/axiosInstance";
const roleMapping = {
  DOCTOR_ROLE: { roleId: 1, name: "Doctor" },
  DHO_ROLE: { roleId: 2, name: "DHO" },
  AO_ROLE: { roleId: 3, name: "AO" },
  BMO_ROLE: { roleId: 4, name: "BMO" },
};

const RegisterUser = () => {
  const [role, setRole] = useState("");
  const [formData, setFormData] = useState({
    userType: "",
    userId: "",
    fullName: "",
    email: "",
    phone: "",
    password: "",
    blockName: "",
    districtName: "",
    role: {},
  });

  const handleRoleChange = (e) => {
    const selectedRole = e.target.value;
    setRole(selectedRole);

    setFormData((prev) => ({
      ...prev,
      role: roleMapping[selectedRole] || {}, // Store role correctly
      blockName: selectedRole === "BMO_ROLE" ? prev.blockName : "", // Reset if not required
      districtName: selectedRole === "DHO_ROLE" ? prev.districtName : "", // Reset if not required
      userType: selectedRole ==="DHO_ROLE"?"DHO":"BMO"
    }));
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await registerUser(formData);
      alert("User registered successfully!");
    } catch (error) {
      alert("Registration failed. Please try again.");
    }
    console.log("Form Submitted", formData);
  };

  return (
    <div className="max-w-lg mx-auto mt-10 p-6 bg-white shadow-lg rounded-xl">
      <h2 className="text-2xl font-semibold mb-4 text-center">Register User</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-medium">User ID</label>
          <input
            type="number"
            name="userId"
            value={formData.userId}
            onChange={handleChange}
            className="w-full p-2 border rounded-lg mt-1"
            required
          />
        </div>

        <div>
          <label className="block font-medium">Select Role</label>
          <select
            name="role"
            value={role}
            onChange={handleRoleChange}
            className="w-full p-2 border rounded-lg mt-1"
            required
          >
            <option value="">-- Select Role --</option>
            <option value="BMO_ROLE">BMO</option>
            <option value="DHO_ROLE">DHO</option>
          </select>
        </div>

        <div>
          <label className="block font-medium">Full Name</label>
          <input
            type="text"
            name="fullName"
            value={formData.fullName}
            onChange={handleChange}
            className="w-full p-2 border rounded-lg mt-1"
            required
          />
        </div>

        <div>
          <label className="block font-medium">Email</label>
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            className="w-full p-2 border rounded-lg mt-1"
            required
          />
        </div>

        <div>
          <label className="block font-medium">Phone</label>
          <input
            type="tel"
            name="phone"
            value={formData.phone}
            onChange={handleChange}
            className="w-full p-2 border rounded-lg mt-1"
            required
          />
        </div>

        <div>
          <label className="block font-medium">Password</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            className="w-full p-2 border rounded-lg mt-1"
            required
          />
        </div>

        {role === "BMO_ROLE" && (
          <div>
            <label className="block font-medium">Block Name</label>
            <input
              type="text"
              name="blockName"
              value={formData.blockName}
              onChange={handleChange}
              className="w-full p-2 border rounded-lg mt-1"
              required
            />
          </div>
        )}

        {role === "DHO_ROLE" && (
          <div>
            <label className="block font-medium">District Name</label>
            <input
              type="text"
              name="districtName"
              value={formData.districtName}
              onChange={handleChange}
              className="w-full p-2 border rounded-lg mt-1"
              required
            />
          </div>
        )}

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

export default RegisterUser;
