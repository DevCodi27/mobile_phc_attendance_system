import React, { useState } from "react";

const DoctorsInBlock = () => {
  // const [blockName, setBlockName] = useState("");
  const [doctorsByBlock, setDoctorsByBlock] = useState({});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const fetchDoctors = async () => {
    setLoading(true);
    setError("");
    setDoctorsByBlock({});
    const { blockName } = useParams();

    try {
      const response = await axiosInstance.get(`/api/doctors/block/${blockName}/doctors`);

      if (!response.status === 200) {
        throw new Error("Failed to fetch doctors for the block");
      }

      const data = await response.json();
      setDoctorsByBlock(data);
    } catch (err) {
      setError(err.message || "Something went wrong");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="p-4 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Doctors in Block</h1>

      <div className="mb-4">
        <input
          type="text"
          value={blockName}
          onChange={(e) => setBlockName(e.target.value)}
          placeholder="Enter Block Name"
          className="border p-2 mr-2 rounded"
        />
        <button
          onClick={fetchDoctors}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Fetch Doctors
        </button>
      </div>

      {loading && <p>Loading...</p>}

      {error && <p className="text-red-600">{error}</p>}

      {Object.keys(doctorsByBlock).length > 0 && (
        <div>
          {Object.entries(doctorsByBlock).map(([block, doctors]) => (
            <div key={block} className="mb-6">
              <h2 className="text-xl font-semibold mb-2">Block: {block}</h2>
              {doctors.length === 0 ? (
                <p>No doctors found.</p>
              ) : (
                <ul className="list-disc pl-5 space-y-1">
                  {doctors.map((doctor) => (
                    <li key={doctor.id}>
                      <strong>{doctor.name}</strong> - {doctor.specialization}
                    </li>
                  ))}
                </ul>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default DoctorsInBlock;
