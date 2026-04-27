import { useEffect, useState } from "react";
import axios from 'axios';

export default function AdminEmployees() {
    const [employees, setEmployees] = useState([]);
    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {

            const token = localStorage.getItem("token");

            const response = await axios.get(
                `http://localhost:8080/api/employee`,
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );
            setEmployees(response.data);

        } catch (error) {
            console.log(error);
            alert("Failed to Fetch Employees.")
        }
    };

    //staus cahnge employee
    
    const toggleActive = async (empId, currentStatus) => {
    try {
        const token = localStorage.getItem("token");

        await axios.put(
            `http://localhost:8080/api/employee/active/${empId}`,
            !currentStatus,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json"   // ✅ Add this line
                }
            }
        );
        alert("updated successfully.");
        fetchEmployees();
    } catch (error) {
        console.log(error);
        alert("updated failed.");
    }
};
    
    return (
        <div>
            <h3>All Employees</h3>

            <table border="1" cellPadding="10">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Active</th>
                        <th>Email</th>
                        <th>Joining Date</th>
                        <th>Department</th>
                        <th>Role</th>
                        <th>Action</th>
                        
                    </tr>
                </thead>

                <tbody>
                    {employees.map((emp) => (
                        <tr key={emp.empId}>
                            <td>{emp.empId}</td>
                            <td>{emp.empName}</td>
                            <td>{emp.active ? "Yes" : "No"}</td>
                            <td>{emp.empEmail}</td>
                            <td>{emp.empJoiningDate}</td>
                            <td>{emp.empDepartment}</td>
                            <td>{emp.empAuthenticationRole.toUpperCase()}</td>
                            <td>
                                <button onClick={()=> toggleActive(emp.empId, emp.active)}>{emp.active?"Activate":"Deactivate"}</button>
                            </td>
                            
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )

}
