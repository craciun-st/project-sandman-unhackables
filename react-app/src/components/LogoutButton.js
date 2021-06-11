import React from 'react'
import PropTypes from 'prop-types'


class LogoutButton extends React.Component {
    render() {
      return (
        <button className="btn btn-warning"
          onClick={this.props.onClick} 
          style={{display: (this.props.loginStatus) ? 'block':'none'}}
        >
          Log Out
        </button>
      )      
    }
  }
  LogoutButton.propTypes = {
    onClick: PropTypes.func.isRequired,
    loginStatus: PropTypes.bool,
    jwt: PropTypes.string    
  }

  export default LogoutButton;