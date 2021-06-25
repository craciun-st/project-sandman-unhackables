import React, {Component} from "react";

export default class SyncTasksWithServerButton extends Component {
    render() {
        return (
            <button
                className="btn btn-primary btn-info"
                onClick={this.props.syncWithServer}
            >
                Sync <span className="fas fa-sync-alt"></span>
            </button>
        )
    }
}