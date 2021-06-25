import React, { Component } from 'react'

export default class LogoImage extends Component {
    render() {



        return (
            <div className="logo-image" onClick={this.props.onClickFunction}>
                <img src={this.props.logo} 
                width={this.props.imageWidth}
                    alt="Empty login face"/>
            </div>
        )
    }
}
