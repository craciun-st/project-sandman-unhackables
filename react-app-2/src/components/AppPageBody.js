import './AppPageBody.css'
import React, { Component } from 'react'
import TaskContainer from './TaskContainer'
import Box2 from './Box2'
import Box3 from './Box3'
import Box4 from './Box4'

export default class AppPageBody extends Component {
    render() {
        return (
            <div className="AppPageBody">
                <Box2/>
                <InputTaskBox 
                />
                <Box4/>
                <TaskContainer/>
            </div>
        )
    }
}
