import { useEffect, useState } from "react";
import axios from 'axios';

export default function AdminEmployees() {


    const [empName, setEmpName] = useState("");
    const [empRole, setEmpRole] = useState("");
    const [empEmail, setEmpEmail] = useState("");
    const [empDepartment, setEmpDepartment] = useState("");
    const [empAuthenticationRole, setEmpAuthenticationRole] = useState("");
    const [empPassword, setEmpPassword] = useState("");

    const createEmployee = async () => {
        try {

            const token = localStorage.getItem("token");
            const loggedInEmployeeId = localStorage.getItem("employeeId");

            if(
                !empName.trim() ||
                !empEmail.trim() ||
                !empDepartment.trim() ||
                !empAuthenticationRole.trim() ||
                !empRole.trim() ||
                !empPassword.trim() 
            ){
                alert("All Fields Are Required.");
                return;
            }

            await axios.post(
                `http://localhost:8080/api/employee?loggedInEmployeeId=${loggedInEmployeeId}`,
                {
                    empName: empName,
                    empRole: empRole,
                    empEmail: empEmail,
                    empDepartment: empDepartment,
                    empAuthenticationRole: empAuthenticationRole,
                    empPassword: empPassword,
                    loggedInEmployeeId: 15
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type" : "application/json"
                    }
                }
            );
            alert("Employee Created Succefully.");
            console.log(loggedInEmployeeId);
            

            setEmpName("");
            setEmpAuthenticationRole("");
            setEmpEmail("");
            setEmpDepartment("");
            setEmpRole("");
            setEmpPassword("");

        

        } catch (error) {
            console.log(error);
            console.log(loggedInEmployeeId);
            
            alert("Employee Creation Failed.");


        }
    };

    return (
        <div style={{margin : "10px"}}>
            <h3>Create Employee</h3>

            <input
                type="text"
                placeholder="Employee Name"
                value={empName}
                onChange={(e) => setEmpName(e.target.value)}
            />

<br />
            <input
                type="email"
                placeholder="Employee Email"
                value={empEmail}
                onChange={(e) => setEmpEmail(e.target.value)}
            />

            <br />

            <input
                type="text"
                placeholder="Department"
                value={empDepartment}
                onChange={(e) => setEmpDepartment(e.target.value)}
            />

            <br />

            <input
                type="text"
                placeholder="Role"
                value={empRole}
                onChange={(e) => setEmpRole(e.target.value)}
            />

            <br />

            <input
                type="text"
                placeholder="Authentication Role"
                value={empAuthenticationRole}
                onChange={(e) => setEmpAuthenticationRole(e.target.value)}
            />

            <br />

            <input
                type="password"
                placeholder="Password"
                value={empPassword}
                onChange={(e) => setEmpPassword(e.target.value)}
            />

            <br /><br />

            <button onClick={createEmployee}>
                Create Employee
            </button>

            <br /><br />

        </div>
    )

}