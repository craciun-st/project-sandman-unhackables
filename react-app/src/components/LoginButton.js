import React, { Component } from 'react'
import PropTypes from 'prop-types'

class LoginButton extends Component {


    render() {
        return (
            <button 
                className="btn btn-success"
                onClick={this.props.onClick} 
                style={{display: (this.props.loginStatus) ? 'none':'block'}}
            >
                Log In
            </button>
        )
    }
}

LoginButton.propTypes = {
    onClick: PropTypes.func.isRequired,
    loginStatus: PropTypes.bool.isRequired,
    jwt: PropTypes.string
}

export default LoginButton;


