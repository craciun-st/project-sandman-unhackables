import './App.css';
import bootstrap from '../node_modules/bootstrap/dist/css/bootstrap.css'

const taskData = [
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
  return (
    <div className="App">
      <div className="login-container">
        You are logged in as <span className="user-name">
          {userName}
          </span>
      </div>
      <div>
        <table className="table table-success table-striped">
          <tbody>
            {taskData.map(
              (task, index) => (
                <tr key={index}>
                  <td>{index+1}</td>
                  <td>{task.name}</td>
                </tr>
              )
            )}
            </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;





