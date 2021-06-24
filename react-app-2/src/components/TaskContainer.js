import './TaskContainer.css'
import React, { Component } from 'react'
import InputTaskBox from './InputTaskBox'
import TaskTable from './TaskTable'

export default class TaskContainer extends Component {
    render() {
        return (
            <div className="TaskContainer">                
                <TaskTable/>            
            </div>
        )
    }
}
