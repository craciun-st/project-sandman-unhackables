import React, { Component } from 'react'
import './Navbar.css'
import logo from '../util/resources/image.png'

export default class Navbar extends Component {
    render() {
        function hideSideBar() {
            var sideBar = document.getElementById("side-menu");
            if (sideBar.style.display==="none") {
                sideBar.style.display = "block"
            } else {
                sideBar.style.display = "none"
            };
        }


        return (
            <div className="Navbar">
                    <div className="logo-image" onClick={hideSideBar}>
                        <img src={logo}
                            alt="Empty login face"
                            width="46"
                            height="46"/>
                    </div>
            </div>
        )
    }
}
