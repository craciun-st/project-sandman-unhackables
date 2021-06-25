import React from "react";

export default class LogoutButton extends React.Component {
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
