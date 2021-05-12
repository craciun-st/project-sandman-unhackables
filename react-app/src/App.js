import './App.css';
import bootstrap from '../node_modules/bootstrap/dist/css/bootstrap.css'
import {useState} from 'react';

var taskData = [
  {
      "id": 1411077732,
      "name": "Assignments"
  },
  {
      "id": 507317033,
      "name": "Sports"
  },
  {
      "id": 1219644257,
      "name": "Assignments"
  },
  {
      "id": 1045836344,
      "name": "General"
  }
];
const userId = 1;
const userName = "Developer";



function App() {

  const[taskName, setTaskName] = useState('');
  const[taskList, setTaskList] = useState(taskData);


  function addTask() {
    let newList = [...taskList];
    newList.push({"name": taskName});
    setTaskList(newList);
  }

  function deleteTask(index) {
    var duplicateArray = [...taskList];
    duplicateArray.splice(index,1);
    setTaskList(duplicateArray);
  }
  


  return (
    <div className="App">
      <div className="login-container">
        You are logged in as <span className="user-name">
          {userName}
          </span>
      </div>
      <div className="row justify-content-center">
        <div className="col-md-5">
          
          <input type="text" placeholder="Enter task..." className="form-control" 
          value={taskName} onChange={(e) => {setTaskName(e.target.value)}}
          />
          <button className="btn btn-primary" onClick={addTask}>ADD</button>

          <table className="table table-success table-striped">
            <tbody>
              {taskList.map(
                (task, index) => (
                  <tr key={index}>
                    <td>{index+1}</td>
                    <td>{task.name}</td>
                    <td><i class="far fa-trash-alt" onClick={() => deleteTask(index)}></i></td>
                  </tr>
                )
              )}
              </tbody>
          </table>
        </div>
      </div>
    </div>
  );


}

export default App;





