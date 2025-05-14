import React from "react";
import { createRoot } from "react-dom/client";
import { createBrowserRouter, RouterProvider, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Admin from "./pages/Admin";
import Bmo from "./pages/Bmo";
import Dho from "./pages/Dho";
import Blocks from "./pages/Blocks";
import NotFound from "./pages/NotFound";
import FacilityList from "./pages/FacilityList";
import DoctorList from "./pages/DoctorList";
import DoctorRegistration from "./pages/DoctorRegistration";
import RegisterUser from "./pages/UserRegistrationForm";
import FacilityRegistrationForm from "./pages/FacilityRegistrationForm";
import ProtectedRoute from "./components/ProtectedRoute";
import 'leaflet/dist/leaflet.css';
import PendingShifts from "./pages/PendingShifts"
import DoctorsInBlock from "./pages/DoctorsInBlock"
import AllDoctorsList from "./pages/AllDoctorsList"
import AllFacilitiesList from "./pages/AllFacilitiesList"
import './index.css';


const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigate to="/login" replace />, // ✅ Default to login page
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/register",
    element: <RegisterUser />,
  },
  {
    path: "/blocks",
    element: <Blocks />,
  },
  {
    path: "/doctor-registration",
    element: <DoctorRegistration />,
  },
  {
     path: "/doctors",
    element: <AllDoctorsList />,
  },
  
  {
     path: "/facilities",
    element: <AllFacilitiesList />,
  },
  {
  path: "/admin",
  element: <ProtectedRoute allowedRoles={["ADMIN"]} />,
  children: [{ path: "/admin", element: <Admin /> }],
},

  {
    path: "/bmo",
    element: <ProtectedRoute allowedRoles={["BMO"]} />, // ✅ Protect BMO Route
    children: [{ path: "/bmo", element: <Bmo /> }],
  },
  {
    path:"/:blockName/facilities",
    element: <FacilityList />
  },
  {
    path:"/:facilityId/doctors",
    element:<DoctorList />
  },
  {
    path:"block/:blockName/doctors",
    element:<DoctorsInBlock />
  },
  {
    path: "/dho",
    element: <ProtectedRoute allowedRoles={["DHO"]} />, // ✅ Protect DHO Route
    children: [{ path: "/dho", element: <Dho /> }],
  },{
    path:"/register-phc",
    element:<FacilityRegistrationForm />
  },
  {
    path:"/pending-shifts",
    element:<PendingShifts />
  },
  {
    path: "*",
    element: <NotFound />,
  },
]);

createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
