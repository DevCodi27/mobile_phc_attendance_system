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
import RegisterUser from "./pages/UserRegistrationForm";
import ProtectedRoute from "./components/ProtectedRoute";

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
    path: "/admin",
    element: <ProtectedRoute allowedRoles={["ADMIN"]} />, // ✅ Protect Admin Route
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
    path: "/dho",
    element: <ProtectedRoute allowedRoles={["DHO"]} />, // ✅ Protect DHO Route
    children: [{ path: "/dho", element: <Dho /> }],
  },
  {
    path: "*",
    element: <NotFound />,
  },
]);

createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
