import { useState } from "react";

import Login from "./pages/login";
import Dashboard from "./pages/Dashboard";

export default function App(){

  const [isLoggedIn, setLoggedIn] = useState(localStorage.getItem("token")  ? true : false );

  return(
    <>
    {isLoggedIn ? <Dashboard/> : <Login/>}    
    </>
  )
}