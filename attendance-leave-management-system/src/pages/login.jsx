import { useState } from "react";
import axios from "axios";


export default function Login(){

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  

  const handleLogin = async () => {
    try{
      const response = await axios.post(
        "http://localhost:8080/api/authentication/login",
        {
          email : email,
          password : password,
        }
      );

      localStorage.setItem("token", response.data.token);
      localStorage.setItem("employeeId", response.data.employeeId);
      localStorage.setItem("role", response.data.role.toUpperCase());

      console.log(response.data.role);
      
      console.log("token :", response.data);
      // window.location.href = "/dashboard";
      window.location.reload();
      
    }
    catch (error){
      alert("login failed")
      console.log("error");
      console.log("data :", error.response.data);
      console.log("status: ", error.response.status);
      
      
      
    }
  };

  return(
    <div style={{padding: "40px"}}>
      <h2>Login</h2>

      <input type="email" 
      placeholder="Email"
      value={email} 
      onChange={(e) => setEmail(e.target.value)} />
      <br /><br />

      <input type="password" 
      placeholder="Password" 
      value={password} 
      onChange={(e) => setPassword(e.target.value)}/>
      <br /><br />

      <button 
      onClick={handleLogin}>Login</button>
    
    </div>
  )
}