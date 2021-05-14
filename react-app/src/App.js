import './App.css';
import bootstrap from 'bootstrap/dist/css/bootstrap.min.css'

import React, {useState} from 'react';
import {  
  BrowserRouter as Router,
  Route,
  Switch  
} from "react-router-dom";

import ProfilePage from "./ProfilePage";


var taskData = [
  {
      "id": 1411077732,
      "name": "Assignments",
      "done": true
  },
  {
      "id": 507317033,
      "name": "Sports",
      "done": false
  },
  {
      "id": 1219644257,
      "name": "Assignments",
      "done": true
  },
  {
      "id": 1045836344,
      "name": "General",
      "done": false
  }
];
const userId = 1;
const userName = "Developer";



function App() {

  const[taskName, setTaskName] = useState('');
  const[taskList, setTaskList] = useState(taskData);


  function addTask() {
    let newList = [...taskList];
    newList.push({"name": taskName, "done": false});
    setTaskList(newList);
    setTaskName('');
  }

  function deleteTask(index) {
    var duplicateArray = [...taskList];
    duplicateArray.splice(index,1);
    setTaskList(duplicateArray);
  }

  function updateTaskName(event) {
    setTaskName(event.target.value)
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
  

  class LoginContainer extends React.Component {
    render() {
      return(
        <div className="login-container">
        You are logged in as <a className="user-name" href="/profile">
          {userName}
          </a>
      </div>
      )
    }
  }

  class TaskTable extends React.Component {
    render() {
      return(
        <table className="table table-success table-striped">
          <tbody>
            {taskList.map(
              (task, index) => (
                <tr key={index}>
                  <td>                      
                    <input type="checkbox" id={`check-${index}`} onChange={handleCheckBoxClick} checked={task.done}></input>
                  </td>
                  <td>{index+1}</td>
                  <td>{task.name}</td>
                  <td><i class="far fa-trash-alt" onClick={() => deleteTask(index)}></i></td>
                </tr>
              )
            )}
          </tbody>
        </table>
      )
    }
  }


  return (    
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/" >

            {/* === INDEX PAGE === */}
            <LoginContainer></LoginContainer>
            <div className="row justify-content-center">
              <div className="col-md-5">
                
                <input type="text" placeholder="Enter task..." className="form-control" 
                value={taskName} onChange={updateTaskName}
                />
                <button className="btn btn-primary" onClick={addTask}>ADD</button>

                <TaskTable></TaskTable>
                
              </div>
            </div>
            {/* === END OF INDEX PAGE === */}

            </Route>

          <Route path="/profile"><ProfilePage /></Route>
        </Switch>
      </Router>
    </div>
  );


}

export default App;





