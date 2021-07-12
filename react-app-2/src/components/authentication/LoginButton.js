import React, { Component } from 'react'
import bootstrap from "bootstrap/dist/css/bootstrap.min.css";

export default class LoginButton extends Component {
    render() {
        return (
            <button 
                className="btn btn-success"
                onClick={this.props.onClick} 
                style={{display: (this.props.loginStatus) ? 'none':'flex'}}
            >
                Log In
            </button>
        )
    }
}

