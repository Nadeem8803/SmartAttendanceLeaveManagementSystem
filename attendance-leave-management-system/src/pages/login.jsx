import { useState } from "react";
import API from "../services/api";

export default function Login() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const response = await API.post("/login", {
        username,
        password,
      });

      console.log("LOGIN SUCCESS", response.data);

      // Example: store user
      localStorage.setItem("user", JSON.stringify(response.data));

      alert("Login Success");

    } catch (error) {
      console.error("LOGIN FAILED", error);
      alert("Invalid Credentials");
    }
  };

  return (
    <div style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      height: "100vh",
      backgroundColor: "#f5f5f5"
    }}>
      <div style={{
        width: "320px",
        padding: "30px",
        backgroundColor: "white",
        borderRadius: "10px",
        boxShadow: "0 0 10px rgba(0,0,0,0.1)"
      }}>
        <h2 style={{ textAlign: "center" }}>Login</h2>

        <input
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          style={{ width: "100%", padding: "10px", marginTop: "15px" }}
        />

        <input
          placeholder="Password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={{ width: "100%", padding: "10px", marginTop: "10px" }}
        />

        <button
          onClick={handleLogin}
          style={{
            width: "100%",
            padding: "10px",
            marginTop: "20px",
            backgroundColor: "#1976d2",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer"
          }}
        >
          Login
        </button>
      </div>
    </div>
  );
}