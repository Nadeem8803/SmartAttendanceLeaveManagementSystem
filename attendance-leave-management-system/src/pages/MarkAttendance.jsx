import { useState,useEffect } from "react";
import axios from 'axios';

export default function MarkAttendance(){

    const checkIn = async () => {

        try{

            const token = localStorage.getItem("token");
            const employeeId = localStorage.getItem("employeeId");

            await axios.post(
                `http://localhost:8080/api/attendence`,
                {
                    employeeId: employeeId
                },
                {
                    headers:{
                        Authorization : `Bearer ${token}`
                    }
                }
            );

            alert("Check In Successfully.");
        }catch(error){
            console.log(error);
            alert("CheckInFailed.");
            
        }

    };

    const checkOut = async () => {

        try{

            const token = localStorage.getItem("token");
            const employeeId = localStorage.getItem("employeeId");

            await axios.put(
                `http://localhost:8080/api/attendence/checkOut/${employeeId}`,
                {},
                {
                    headers:{
                        Authorization: `Bearer ${token}`
                    }
                }
            );
            alert("Check Out Successfully.");

        }catch(error){
            console.log(error);
            alert("Failed to Check Out.");
            
        }

    };

    return(
        <div>
            <h3>Mark Attendance</h3>

            
            <button onClick={checkIn}>
                Check-In
            </button>
            <hr />
            <button onClick={checkOut}>
                Check-Out
            </button>
            <hr />
        </div>
    );

}