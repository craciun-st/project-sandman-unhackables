import './TaskTable.css'
import React, { Component } from 'react'
import { colorClassForCategory } from '../../util/cssHelperFunctions'
import bootstrap from "bootstrap/dist/css/bootstrap.min.css";

export default class TaskTable extends Component {
    render() {
        return (
            <table className="table table-success table-striped">
                <tbody>
                    {this.props.taskList.map((task, index) => (
                    <tr key={index}>
                        <td>
                        <input
                            type="checkbox"
                            id={`check-${index}`}
                            onChange={this.props.handleCheckBoxClick}
                            checked={task.done}
                        ></input>
                        </td>
                        <td>{index + 1}</td>
                        <td>
                        <div
                            className={`category-text ${colorClassForCategory(
                            task.category
                            )}`}
                        >
                            {task.category}
                        </div>
                        </td>
                        <td>{task.name}</td>
                        <td>{task.importance}</td>
                        <td>
                            <i
                                className="far fa-trash-alt red-color-text"
                                onClick={() => this.props.deleteTask(index)}
                            ></i>
                        </td>
                    </tr>
                    ))}
                </tbody>
            </table>
        )
    }
}
