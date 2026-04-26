import { useEffect, useState } from "react";
import axios from "axios";

export default function AdminLeaves(){

    const [leaves, setLeaves] = useState([]);

    useEffect(()=>{
        fetchLeaves();
    },[]);

    const fetchLeaves = async () => {
        try{
            const token = localStorage.getItem("token");

            const response = await axios.get(
                `http://localhost:8080/api/leave`,
                {
                    headers:{
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            setLeaves(response.data);

        } catch(error){
            console.log(error);
            alert("Failed to fetch leaves");
        }
    };

    const updateStatus = async (leaveId, status) => {
        try{
            const token = localStorage.getItem("token");
            const employeeId = localStorage.getItem("employeeId");

            await axios.put(
                `http://localhost:8080/api/leave/status/${leaveId}?loggedInEmployeeId=${employeeId}`,
                status,
                {
                    headers:{
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "text/plain"
                    }
                }
            );

            alert("Status Updated");
            fetchLeaves();

        } catch(error){
            console.log(error);
            alert("Update Failed");
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
            <h2>Admin - Manage Leaves</h2>

            {
                leaves.length === 0 ? (
                    <p>No Leaves Found</p>
                ) : (
                    <ul>
                        {
                            leaves.map((leave) => (
                                <li key={leave.leaveId}>
                                    {leave.employee?.empName} | {leave.leaveType} | {leave.startDate} to {leave.endDate} | {leave.leaveStatus}
                                    
                                    <br />

                                    {leave.leaveStatus.toUpperCase() === "PENDING" && (
                                        <>
                                            <button onClick={() => updateStatus(leave.leaveId, "APPROVED")}>
                                                Approve
                                            </button>

                                            <button onClick={() => updateStatus(leave.leaveId, "REJECTED")}>
                                                Reject
                                            </button>

                                            <button onClick={()=> handleDelete(leave.leaveId)}>
                                                Delete
                                            </button>
                                        </>
                                    )}
                                    <hr style={{padding:"5px"}}/>
                                </li>
                                
                            ))
                        }
                        
                    </ul>
                )
            }

        </div>
    );
}