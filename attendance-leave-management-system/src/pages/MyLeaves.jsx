import { useEffect, useState } from "react";
import axios from "axios";

export default function MyLeaves(){

    const [leaves, setLeaves] = useState([]);

    useEffect(()=>{
        fetchLeaves();
    },[]);

    const fetchLeaves = async () => {

        try{
            const token = localStorage.getItem("token");
            const employeeId = localStorage.getItem("employeeId");

            const response = await axios.get(
                `http://localhost:8080/api/leave/employee/${employeeId}`,
                {
                    headers:{
                        Authorization : `Bearer ${token}`
                    }
                }
            );
            
            setLeaves(response.data);

        } catch(error){
            console.log(error);
            alert("Failed To Fetch Leaves.");
            
        }

    };

    const handleDelete = async (leaveId) => {

        try{
        
        const token = localStorage.getItem("token");
        const employeeId = localStorage.getItem("employeeId");

        const response = await axios.delete(
                `http://localhost:8080/api/leave/${leaveId}?loggedInEmployeeId=${employeeId}`,
                {
                    headers:{
                        Authorization : `Bearer ${token}`
                    }
                }
            );
            alert("Leave Deleted Successfully.");
            fetchLeaves();
        } catch(error){

            console.log(error);
            alert("Delete Fail.")            

        }

    };

    return (
        <div style={{padding: "20px"}}>
            <h2>My Leaves</h2>

        {leaves.length === 0 ? (
            <p>No Leaves Found</p>
        ) : (
            <table className="main-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Leave Type</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                    </tr>
                </thead>

                <tbody>
                    {leaves.map((leave) => (
                        <tr key={leave.leaveId}>
                            <td>{leave.leaveId}</td>
                            <td>{leave.leaveType}</td>
                            <td>{leave.startDate}</td>
                            <td>{leave.endDate}</td>
                            <td>{leave.leaveStatus}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        )}

        </div>
    );

}