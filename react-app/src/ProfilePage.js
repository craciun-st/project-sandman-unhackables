import './App.css';
import './ProfilePage.css'
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import { React, useState } from "react";
import ChartistLineGraph from "./chart";

function ProfilePage() {
    const userName = 'Developer'    
    return (
        <div className="ProfilePage">
            <h2 className="nameContainer">Hello, {userName}</h2>
            <img src="https://picsum.photos/300/300" className="profilePicDim"></img>
            <div className="chart-container">
                <ChartistLineGraph></ChartistLineGraph>
            </div>
        </div>
    )
}

export default ProfilePage;