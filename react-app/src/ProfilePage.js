import './App.css';
import './ProfilePage.css'
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'
import { React, useState } from "react";
import ChartistLineGraph from "./chart";

function ProfilePage() {
    const userName = 'Developer'    
    return (
        <div className="ProfilePage profileSuperContainer">
            <h2 class="nameContainer">Hello, {userName}</h2>
            <div className="userDataContainer">
                
                <div className="userNameAndPic">                    
                    <img src="https://picsum.photos/300/300" className="profilePicDim"></img>                    
                </div>    
                <div className="chart-container">
                    <h2 className="dummySpearator"></h2>
                    <ChartistLineGraph></ChartistLineGraph>
                </div>
            
            </div>
            <br></br>
            <br></br>
            <br></br>
            <a href="/">Back to main page</a>
        </div>
    )
}

export default ProfilePage;