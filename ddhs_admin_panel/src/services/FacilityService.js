import axios from "axios";
import axiosInstance from "./axiosInstance";
const API_BASE_URL = "http://localhost:8080/api/facilities";

export const FacilityService = {
    getFacilitiesByBlock: async (blockName) => {
        try {
            const response = await axiosInstance.get(`${API_BASE_URL}/${blockName}`);
            return response.data;
        } catch (error) {
            console.error("Error fetching facilities", error);
            return [];
        }
    }
};
