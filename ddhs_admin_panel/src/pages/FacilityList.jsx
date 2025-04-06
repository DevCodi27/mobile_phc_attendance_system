import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import axiosInstance from "../services/axiosInstance"; // ✅ Axios instance with JWT token

const FacilityList = () => {
  const { blockName } = useParams();
  const [facilities, setFacilities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchFacilities = async () => {
      try {
        const response = await axiosInstance.get(`/api/facilities/${blockName}/facilities`);
        setFacilities(response.data);
        console.log(response.data);
      } catch (err) {
        console.error("Error fetching facilities:", err);
        setError("Failed to fetch facilities");
      } finally {
        setLoading(false);
      }
    };

    fetchFacilities();
  }, [blockName]);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-2">Facilities in {blockName}</h2>

      {loading ? (
        <p className="text-gray-500">Loading facilities...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : facilities.length > 0 ? (
        <ul className="bg-gray-100 p-4 rounded-lg shadow-md">
         {facilities.map((facility) => (
            <li key={facility.id} className="py-2 border-b last:border-none">
             
              <Link
                to={`/${facility.id}/doctors`}
                className="text-blue-600 hover:underline"
              >
                {facility.name}
              </Link>
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-gray-500">No facilities found</p>
      )}
    </div>
  );
};

export default FacilityList;
