import './AppPageBody.css'
import React, { Component, useState, useEffect } from 'react'
import TaskContainer from './TaskContainer'
import Box2 from './Box2'
import Box3 from './Box3'
import Box4 from './Box4'
import InputTaskBox from './InputTaskBox'
import { updateTaskName, updateTaskCategory, addTask } from '../util/taskStateFunctions'

export default function AppPageBody()  {

        const [taskName, setTaskName] = useState("");
        const [taskList, setTaskList] = useState(taskData);
        const [canGetTaskList, setCanGetTaskList] = useState(false);
        const [taskCategory, setTaskCategory] = useState("");
        const [taskImportance, setTaskImportance] = useState("3");

        return (
            <div className="AppPageBody">
                <Box2/>
                <InputTaskBox 
                    taskName={taskName} 
                    updateTaskName={mappedUpdateTaskName}
                    taskCategory={taskCategory}
                    updateTaskCategory={mappedUpdateTaskCategory} 
                    defaultCategories={defaultCategories}
                    onAddTaskClick={mappedAddTask}                    
                />
                <Box4/>
                <TaskContainer/>
            </div>
        )
    }
}
