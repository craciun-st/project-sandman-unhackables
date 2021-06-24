import React, { Component } from 'react'
import './Navbar.css'
import LogoImage from '../util/resources/LogoImage';


export default class Navbar extends Component {
    render() {
        function hideSideBar() {
            var sideBar = document.getElementById("side-menu");
            if (sideBar.style.display==="none") {
                sideBar.style.display = "flex"
            } else {
                sideBar.style.display = "none"
            };
        }


        return (
            <div className="Navbar">

                <LogoImage
                    onClickFunction={hideSideBar}
                    logo={this.props.logo}
                    imageWidth="30vw"/>
            </div>
        )
    }
}
