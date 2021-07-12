import './LoginPage.css'
import React, {useState} from "react";
import {doFetchWithPost} from "../../connection/fetches";

const serverAddress = "http://localhost:8080"

export default function LoginPage(props) {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    function handleFormSubmit(event) {
        let jsonToSend = {
            username: username,
            password: password
        }
        console.log("Send:"+jsonToSend)

        doFetchWithPost(serverAddress+"/auth", jsonToSend)
            .then(response => response.json())
            .then(body => (console.log("response: "+body)))
            .catch(error => console.error(error))
        // alert('JWT would be: '+ jwtToken)
        props.setLoginStatus(true);
        window.location.href("/")
    }

    function updateUsername(event) {
        setUsername(event.target.value)
    }

    function updatePassword(event) {
        setPassword(event.target.value)
    }


    return (
            <div>
                <h1>
                    <label id="loginFormLabel" htmlFor="loginForm">
                        Please login
                    </label>
                </h1>
                <div className="loginForm">
                    <label htmlFor="emailInputElement">Username (Email Address)</label>
                    <input
                        type="text"
                        id="emailInputElement"
                        name="email"
                        value={username}
                        onChange={updateUsername}
                    ></input>

                    <label htmlFor="passwordInputElement">Password</label>
                    <input
                        type="password"
                        id="passwordInputElement"
                        name="password"
                        value={password}
                        onChange={updatePassword}
                    ></input>

                    <button onClick={handleFormSubmit}>Submit</button>
                </div>
            </div>
    );

}