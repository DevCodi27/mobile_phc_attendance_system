import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import axiosInstance from "../services/axiosInstance";

const BlockList = () => {
  const [blocks, setBlocks] = useState([]);

  useEffect(() => {
    axiosInstance.get("http://localhost:8080/api/facilities/blocks")
      .then(response => setBlocks(response.data))
      .catch(error => console.error("Error fetching blocks:", error));
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-2">Available Blocks</h2>
      <ul className="bg-gray-100 p-4 rounded-lg shadow-md">
        {blocks.length > 0 ? (
          blocks.map((block, index) => (
            <li key={index} className="py-2 border-b last:border-none">
              <Link 
                to={`/${encodeURIComponent(block)}/facilities`} 
                className="text-blue-500 hover:underline"
              >
                {block}
              </Link>
            </li>
          ))
        ) : (
          <p className="text-gray-500">No blocks available</p>
        )}
      </ul>
    </div>
  );
};

export default BlockList;
