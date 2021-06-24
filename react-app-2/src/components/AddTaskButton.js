import React, { Component } from 'react'
import bootstrap from "bootstrap/dist/css/bootstrap.min.css";

export default class AddTaskButton extends Component {
    render() {
        return (
            <button className="btn btn-success" onClick={this.props.addTask}>
                      ADD
            </button>
        )
    }
}
