import React, { Component } from 'react'
import './Navbar.css'
import LogoImage from '../util/resources/LogoImage';


class PrivateNavbar extends Component {
    render() {
        return (
            <>
                <a href="/events">Events</a>
                <a href="/rewards">Rewards</a>
            </>
        )
    }
}

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
                <div className="left-align-links">
                    <a href="/">Home</a>

                    { (this.props.loginStatus) ? (
                            <PrivateNavbar/>
                        ) : null
                    }
                </div>

                <div className="right-align-image">
                    <LogoImage
                        onClickFunction={hideSideBar}
                        logo={this.props.logo}
                        imageWidth="30vw"
                    />
                </div>

            </div>
        )
    }
}
