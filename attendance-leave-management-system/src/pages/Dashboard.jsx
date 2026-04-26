import ApplyLeave from "./ApplyLeave"
import MyLeaves from './MyLeaves';

export default function Dashboard(){

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
            <ApplyLeave/>
            <hr />
            <MyLeaves/>

        </div>
    )
}