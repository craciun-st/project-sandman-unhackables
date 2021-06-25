import './InputTaskBox.css'
import React, { Component } from 'react'
import InputTaskName from './InputTaskName'
import AddTaskButton from './AddTaskButton'
import InputTaskCategory from './InputTaskCategory'
import TaskImportanceSelector from './TaskImportanceSelector'
import SyncTasksWithServerButton from "./SyncTasksWithServerButton";


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
                    <TaskImportanceSelector
                        updateImportance={this.props.updateImportance}/>
                </div>
                <div className="itb-side-container">
                    <AddTaskButton addTask={this.props.onAddTaskClick}/>
                    <SyncTasksWithServerButton />
                </div>
            </div>
        )
    }
}

export default InputTaskBox
