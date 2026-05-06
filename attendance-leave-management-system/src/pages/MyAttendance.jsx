import { useEffect,useState } from "react";
import axios from 'axios';

export default function MyAttendance(){
    
    const [attendance,setAttendance] = useState([]);

    useEffect(() => {
        fetchMyAttendance();
    }, []);

    const fetchMyAttendance = async () => {

        try{

            const token = localStorage.getItem("token");
            const employeeId = localStorage.getItem("employeeId");

            const response = await axios.get(
                `http://localhost:8080/api/attendence/${employeeId}`,
                {
                    headers:{
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            setAttendance(response.data);

        }catch(error){
            console.log(error);
            alert("Not Able To Fetch Attendance.");            
        }

    };

    return (
        <div>
            <h3>My Attendance</h3>

            <table border="1" cellPadding="10">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Check In</th>
                        <th>Check Out</th>
                        <th>Status</th>
                    </tr>
                </thead>

                <tbody>
                    {attendance.map((att) => (
                        <tr key={att.attendenceId}>
                            <td>{att.attendenceId}</td>
                            <td>{att.attendenceDate}</td>
                            <td>{att.checkInTime}</td>
                            <td>{att.checkOutTime}</td>
                            <td>{att.attendenceStatus}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );

}