import React from 'react';
import PropTypes from 'prop-types';



class NameAndLogStatus extends React.Component {
    
    render() {
      
      if (this.props.isLoggedIn) {
        return (        
          <div>              
              You are logged in as{" "}
              <a className="user-name" href="/profile">
                {this.props.userName}
              </a>
          </div>
        )
      } 
      else {
        return (
          <div>
            Welcome!
          </div>
        )
      }
      
    }
  }

  NameAndLogStatus.propTypes = {
    isLoggedIn: PropTypes.bool.isRequired,
    userName: PropTypes.string
  }

  export default NameAndLogStatus;