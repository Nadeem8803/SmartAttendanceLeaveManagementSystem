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
        <div className="page-container">
            <h2>Dashboard</h2>

            <button className="main-button" onClick={handleLogout}>
                Logout
            </button>
            <hr />
           
           <button className="common-button" onClick={() => setView("applyLeave")}>
            Apply Leave
           </button>

           <button className="common-button" onClick={() => setView("myLeaves")}>
            My Leaves
           </button>

           <button className="common-button" onClick={() => setView("markAttendance")}>
            Mark-Attendance
           </button>

           <button className="common-button" onClick={() => setView("myAttendance")}>
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