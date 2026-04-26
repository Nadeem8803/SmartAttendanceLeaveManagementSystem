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

            {
                leaves.length === 0 ? (
                    <p>No Leaves Found</p>
                ) : (
                    <ul>
                        {
                            leaves.map((leave) => (
                                <li key={leave.leaveId}>
                                    {leave.leaveType} | {leave.startDate} to {leave.endDate} | Status: {leave.leaveStatus}
                                    <br />
                                    {leave.leaveStatus === "PENDING" && (
                                        <button onClick={() => handleDelete(leave.leaveId)}>
                                            Delete
                                        </button>
                                    )}
                                </li>
                            ))
                        }
                    </ul>
                )
            }

        </div>
    );

}