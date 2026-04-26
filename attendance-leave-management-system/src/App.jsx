import { useState } from "react";

import Login from "./pages/login";
import Dashboard from "./pages/Dashboard";
import AdminDashboard from "./pages/AdminDashboard";


export default function App(){

  const token = localStorage.getItem("token");
  const role = localStorage.getItem("role");

  if(!token){
    return <Login/>
  }
  if(role === "ADMIN"){
    return <AdminDashboard/>
  }

  return <Dashboard/>

}