import './TaskTable.css'
import React, { Component } from 'react'
import { colorClassForCategory } from '../../util/cssHelperFunctions'
import bootstrap from "bootstrap/dist/css/bootstrap.min.css";

export default class TaskTable extends Component {



    getUniqueCategoryList(props) {
        return [...new Set(props.taskList.map(task => task.category))];

    }

    // filterByCategory(event) {
        
    // }

    compareAlphabetically(firstWord, secondWord) {
        
    }

    sortByCategory(props) {
        props.taskList.sort((a,b) => (a.category.localeCompare(b.category)))
    }
    
    render() {


        return (
            <div>
            <select>
                <option defaultChecked value = "all">All</option>
                {this.getUniqueCategoryList(this.props).map((category) => (
                    <option value ={category}></option>
                
                    ))}
            </select>
            <button onClick={this.sortByCategory(this.props)}>Sort by category</button>

            <table className="table table-success table-striped TaskTable">
                {this.props.taskList.length > 0 ?
                    (<thead>
                        <tr>
                            <th>Done?</th>
                            <th>Index</th>
                            <th>Category</th>
                            <th>Task To Be Done</th>
                            <th>Importance</th>
                            <th>Remove Task?</th>
                        </tr>
                    </thead>)
                    : null}
                <tbody>
                    {this.props.taskList.map((task, index) => (
                    <tr key={index} className={task.done ? "text-task-done" : ""}>
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
            </div>
        )
    }
}
