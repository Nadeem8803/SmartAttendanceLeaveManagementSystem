import { useState } from "react";
import axios from "axios";

export default function ApplyLeave(){
    const [leaveType, setLeaveType] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [leaveReason, setLeaveReason] = useState("");
    const today = new Date().toLocaleDateString("en-CA");

    const handleApplyLeave = async () =>{

        if(!leaveType || !startDate || !endDate || !leaveReason){
            alert("Please Fill All The Fields.");
            return;
        }

        if(endDate < startDate){
            alert("End Date Cannot be Before The Start Date.");
            return;
        }

        try{
            const token = localStorage.getItem("token");
            const employeeId = localStorage.getItem("employeeId")
            const response = await axios.post(
                "http://localhost:8080/api/leave",
                {
                    employeeId: Number(employeeId),
                    leaveType: leaveType,
                    startDate: startDate,
                    endDate: endDate,
                    leaveReason: leaveReason
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            alert("Leaved Applied Successfully.");
            console.log(response.data);
            
        } catch(error){
            alert("Leave Apply Failed.");
            console.log(error);
            console.log("status : ", error.response?.data);
            console.log("data :", error.response?.data);
            
            
            
        }
    };

    return(
        <div style={{padding: "20px"}}>
            <h2>Apply Leave</h2>

            <input type="text"
            placeholder="Leave Type"
            value={leaveType}
            onChange={(e) => {
                setLeaveType(e.target.value)
            }} />
            <br /><br />

            <input type="date"
            value={startDate}
            min={today}
            onChange={(e) => {
                setStartDate(e.target.value)
            }}
             />
             <br /><br />

             <input type="date"
             value={endDate}
             min={startDate | today}
             onChange={(e)=>{
                setEndDate(e.target.value)
             }} />
             <br /><br />

             <input type="text"
             placeholder="Leave Reason"
             value={leaveReason}
             onChange={(e)=>{
                setLeaveReason(e.target.value)
             }} />
             <br /><br />

             <button 
             onClick={handleApplyLeave}>Apply Leave</button>

        </div>
    );

}