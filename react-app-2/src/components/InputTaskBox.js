import './InputTaskBox.css'
import React, { Component } from 'react'
import InputTaskName from './InputTaskName'
import AddTaskButton from './AddTaskButton'
import InputTaskCategory from './InputTaskCategory'


export class InputTaskBox extends Component {
    render() {
        return (
            <div className="input-task-box">
                <div className="itb-wide-container">
                    <InputTaskName taskName={this.props.taskName} updateTaskName={this.props.updateTaskName}/>
                    <InputTaskCategory 
                        taskCategory={this.props.taskCategory} 
                        updateTaskCategory={this.props.updateTaskCategory}
                        defaultCategories={this.props.defaultCategories}
                    />
                </div>
                <div className="itb-side-container">
                    <AddTaskButton addTask={this.props.onAddTaskClick}/>
                </div>
            </div>
        )
    }
}

export default InputTaskBox
