import { useState } from "react";
import ApplyLeave from "./ApplyLeave"
import MyLeaves from './MyLeaves';
import MarkAttendance from './MarkAttendance';
import MyAttendance from './MyAttendance';

export default function Dashboard(){

    const [view,setView] = useState("");
    const handleLogout = () => {
        localStorage.removeItem("token");
        window.location.reload();
    };

    return(
        <div style={{padding: "40px"}}>
            <h2>Dashboard</h2>

            <button onClick={handleLogout}>
                Logout
            </button>
            <hr />
           
           <button onClick={() => setView("applyLeave")}>
            Apply Leave
           </button>

           <button onClick={() => setView("myLeaves")}>
            My Leaves
           </button>

           <button onClick={() => setView("markAttendance")}>
            Mark-Attendance
           </button>

           <button onClick={() => setView("myAttendance")}>
            My Attendance
           </button>

            <hr />
            {view === "applyLeave" && <ApplyLeave/>}
            {view === "myLeaves" && <MyLeaves/>}
            {view === "markAttendance" && <MarkAttendance/>}
            {view === "myAttendance" && <MyAttendance/>}


        </div>
    )
}