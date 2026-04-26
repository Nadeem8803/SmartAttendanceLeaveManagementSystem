import { useState } from "react";
import AdminLeaves from "./AdminLeaves";
import AdminEmployees from "./AdminEmployees";

export default function AdminDashboard(){

    const [view, setView] = useState("");
    const handleLogout = () =>{
        localStorage.clear();
        window.location.reload();
    };

    return(
        <div style={{padding : "20px"}}>
            <h2>Admin Dashboard</h2>


                <button onClick={handleLogout}>
                    Logout
                </button>

                <br />
                <hr />

                <button onClick={() => {setView("leaves")}}>
                    Manage Leaves
                </button>

                <button onClick={() => setView("employees")}>
                    Manage Leaves
                </button>

                <hr />

                {view === "leaves" && <AdminLeaves/>}
                {view === "employees" && <AdminEmployees/>}

            
        </div>
    )
}