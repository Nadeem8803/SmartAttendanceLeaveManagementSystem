import { useState } from "react";
import AdminLeaves from "./AdminLeaves";
import AdminEmployees from "./AdminEmployees";
import CreateEmployee from "./CreateEmployee";
import ActiveEmployees from "./ActiveEmployees";
import DeactiveEmployees from "./DeactiveEmployees";

export default function AdminDashboard(){

    const [view, setView] = useState("");
    const handleLogout = () =>{
        localStorage.clear();
        window.location.reload();
    };

    return(
        <div className="page-container">
            <h2>Admin Dashboard</h2>


                <button className="main-button" onClick={handleLogout}>
                    Logout
                </button>

                <br />
                <hr />

                <button className="common-button" onClick={() => {setView("leaves")}}>
                    Manage Leaves
                </button>

                <button className="common-button" onClick={() => setView("employees")}>
                    Employees 
                </button>

                <button className="common-button" onClick={() => setView("createEmployee")}>
                    Create Employee
                </button>

                <button className="common-button" onClick={() => setView("activeEmployees")}>
                    Active Employees
                </button>

                <button className="common-button" onClick={() => setView("deactiveEmployees")}>
                    Deactive Employees
                </button>

                <hr />

                {view === "leaves" && <AdminLeaves/>}
                {view === "employees" && <AdminEmployees/>}
                {view === "createEmployee" && <CreateEmployee/>}
                {view === "activeEmployees" && <ActiveEmployees/>}
                {view === "deactiveEmployees" && <DeactiveEmployees/>}

            
        </div>
    )
}