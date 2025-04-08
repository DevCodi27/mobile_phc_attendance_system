import { useState } from "react";
import { MapContainer, TileLayer, Polygon, useMapEvents } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import axios from "axios";
import axiosInstance from "../services/axiosInstance";

// Fix for missing default marker icons
import L from "leaflet";
import markerIcon2x from "leaflet/dist/images/marker-icon-2x.png";
import markerIcon from "leaflet/dist/images/marker-icon.png";
import markerShadow from "leaflet/dist/images/marker-shadow.png";

delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: markerIcon2x,
  iconUrl: markerIcon,
  shadowUrl: markerShadow,
});

const FacilityRegistrationForm = () => {
  const [formData, setFormData] = useState({
    name: "",
    block: "",
    district: "",
    facilityType: "",
    region: [],
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (formData.region.length < 3) {
      alert("At least 3 points are required to form a region.");
      return;
    }

    const payload = {
      name: formData.name,
      block: formData.block,
      district: formData.district,
      facilityType: formData.facilityType,
      region: formData.region.map((point) => ({
        latitude: point.lat,
        longitude: point.lng,
      })),
    };

    try {
      const response = await axiosInstance.post("http://localhost:8080/api/facilities/register", payload);
      if (response.status === 200 || response.status === 201) {
        alert("Facility registered successfully!");
        setFormData({
          name: "",
          block: "",
          district: "",
          facilityType: "",
          region: [],
        });
      } else {
        alert("Something went wrong. Try again.");
      }
    } catch (error) {
      console.error("Submission error:", error);
      alert("Failed to register. See console for details.");
    }
  };

  const LocationPolygon = () => {
    useMapEvents({
      click(e) {
        setFormData((prev) => {
          return { ...prev, region: [...prev.region, e.latlng] };
        });
      },
    });

    return formData.region.length > 0 ? (
      <Polygon positions={formData.region} pathOptions={{ color: "blue" }} />
    ) : null;
  };

  return (
    <div className="max-w-2xl mx-auto mt-10 p-6 bg-white rounded-lg shadow-md">
      <h2 className="text-2xl font-bold mb-6">Register Facility</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="name"
          placeholder="Facility Name"
          value={formData.name}
          onChange={handleChange}
          className="w-full p-2 border border-gray-300 rounded"
          required
        />
        <input
          type="text"
          name="block"
          placeholder="Block"
          value={formData.block}
          onChange={handleChange}
          className="w-full p-2 border border-gray-300 rounded"
          required
        />
        <input
          type="text"
          name="district"
          placeholder="District"
          value={formData.district}
          onChange={handleChange}
          className="w-full p-2 border border-gray-300 rounded"
          required
        />
        <input
          type="text"
          name="facilityType"
          placeholder="Facility Type"
          value={formData.facilityType}
          onChange={handleChange}
          className="w-full p-2 border border-gray-300 rounded"
          required
        />

        <div style={{ height: "300px", width: "100%", zIndex: 0 }}>
          <MapContainer
            center={[20, 78]}
            zoom={5}
            style={{ height: "100%", width: "100%" }}
          >
            <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
            <LocationPolygon />
          </MapContainer>
        </div>

        {formData.region.length > 0 && (
          <div className="text-sm text-gray-600">
            <p className="font-semibold mt-2">Selected Region Points:</p>
            <ul className="list-disc pl-5">
              {formData.region.map((pt, idx) => (
                <li key={idx}>
                  ({pt.lat.toFixed(4)}, {pt.lng.toFixed(4)})
                </li>
              ))}
            </ul>
          </div>
        )}

        <button
          type="button"
          onClick={() => setFormData({ ...formData, region: [] })}
          className="w-full bg-red-500 text-white p-2 rounded hover:bg-red-600"
        >
          Clear Region
        </button>

        <button
          type="submit"
          className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600"
        >
          Register
        </button>
      </form>
    </div>
  );
};

export default FacilityRegistrationForm;
