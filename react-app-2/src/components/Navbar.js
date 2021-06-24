import React, { Component } from 'react'
import './Navbar.css'
import LogoImage from '../util/resources/LogoImage';
import logo from '../util/resources/image.png'

export default class Navbar extends Component {
    render() {


        return (
            <div className="Navbar">

                <LogoImage
                    logo={logo}/>
            </div>
        )
    }
}
