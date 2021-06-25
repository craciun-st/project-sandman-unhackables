import './AppPageBody.css'
import React, { Component, useState, useEffect } from 'react'
import TaskTable from './task/TaskTable'
import Box2 from './Box2'
import Box3 from './Box3'
import Box4 from './Box4'
import InputTaskBox from './task/InputTaskBox'
import { updateTaskName, updateTaskCategory, addTask } from '../util/taskStateFunctions'
import InspirationalQuote from "./InspirationalQuote";
import {quotesListJson} from "../util/resources/quotes";

const taskData = [];

const defaultCategories = [
    "General",
    "Study",
    "Work",
    "Exercise",
    "House chore"
]

let quoteIndex = Math.trunc(Math.random()*quotesListJson.length);

export default function AppPageBody()  {

        const [taskName, setTaskName] = useState("");
        const [taskList, setTaskList] = useState(taskData);
        const [canGetTaskList, setCanGetTaskList] = useState(false);
        const [taskCategory, setTaskCategory] = useState("");
        const [taskImportance, setTaskImportance] = useState("3");

        function mappedUpdateTaskName(event) {
            return updateTaskName(event, setTaskName);
        }

        function mappedUpdateTaskCategory(event) {
            return updateTaskCategory(event, setTaskCategory)
        }

        function mappedAddTask() {
            let taskMap = {
                name: taskName,
                category: taskCategory,
                importance: taskImportance,
            }
            let taskSetterMap = {
                'setTaskName': setTaskName,
                'setTaskCategory': setTaskCategory,
                'setTaskImportance': setTaskImportance,
                'setTaskList': setTaskList
            }
            return addTask(taskList, taskMap, taskSetterMap)
        }

        function handleCheckBoxClick(event) {
            let checkedState = event.target.checked;
        
            let idString = `${event.target.id}`.slice(6);
            let idAsInt = parseInt(idString);
            var newArray = [...taskList];
            newArray[idAsInt].done = checkedState;
            setTaskList(newArray);
            // console.log(newArray[idAsInt]);  // for debug purposes
          }

        function deleteTask(index) {
            if (taskList.length <= 1) {
                setTaskList([]);
            } else {
                var duplicateArray = [...taskList];
                duplicateArray.splice(index, 1);
                setTaskList(duplicateArray);
            }
        }
        
        function updateImportance(event) {
            setTaskImportance(parseInt(event.target.dataset.value))
        }

        // we ought to return an empty div as the first block (the NW corner) in the grid
        // as it is too small to contain anything useful right now
        return (
            <div className="AppPageBody">
                <div></div>
                <InspirationalQuote
                    quote={quotesListJson[quoteIndex].text}
                    author={quotesListJson[quoteIndex].author}
                />
                <Box2/>
                <InputTaskBox 
                    taskName={taskName} 
                    updateTaskName={mappedUpdateTaskName}
                    taskCategory={taskCategory}
                    updateTaskCategory={mappedUpdateTaskCategory} 
                    defaultCategories={defaultCategories}
                    onAddTaskClick={mappedAddTask} 
                    updateImportance={updateImportance}                   
                />
                <Box4/>
                <TaskTable
                    taskList={taskList}
                    handleCheckBoxClick = {handleCheckBoxClick}
                    deleteTask = {deleteTask}
                    />
            </div>
        )
    }