import { useEffect,useState } from "react";
import axios from 'axios';

export default function DeactiveEmployees() {

    const [employees, setEmployees] = useState([]);
    useEffect(() => {
        fetchDeactiveEmployees();
    },[]);    

    const fetchDeactiveEmployees = async () => {

        try {

            const token = localStorage.getItem("token");

            const response = await axios.get(
                `http://localhost:8080/api/employee/status/activeFalse`,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    }
                }
            );

            
            
            setEmployees(response.data);

        } catch (error) {
            console.log(error);
            alert("Not Able To Fetch.");

        }

    };

    return (
        <div>
            <h3>All Deactive Employees</h3>

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

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );

}