import React, { Component } from 'react'

export default class InputTaskName extends Component {
    render() {
        return (
            <div>
                <input
                            type="text"
                            placeholder="Enter task..."
                            className=""
                            value={this.props.taskName}
                            onChange={this.props.updateTaskName}
                />
            </div>
        )
    }
}
