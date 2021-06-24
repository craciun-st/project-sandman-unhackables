import React, { Component } from 'react'

export default class LogoImage extends Component {
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
            <div className="logo-image" onClick={hideSideBar}>
                <img src={this.props.logo}
                    alt="Empty login face"
                    width="46"
                    height="46"/>
            </div>
        )
    }
}
