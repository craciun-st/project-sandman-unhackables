/* eslint-disable no-unused-vars */
import "./App.css";
import bootstrap from "bootstrap/dist/css/bootstrap.min.css";

import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import ProfilePage from "./ProfilePage";

const MenuItems = [
  {
    title: "Home",
    url: "/",
    cName: "nav-link",
  },
  {
    title: "Events",
    url: "/events",
    cName: "nav-link",
  },
  {
    title: "Rewards",
    url: "/rewards",
    cName: "nav-link",
  },
];

var taskData = [
  {
    id: 6,
    name: "Yet Another Task (YAT)",
    importance: 3,
    category: "General",
    done: false,
  },
  {
    id: 7,
    name: "React scheme",
    importance: 3,
    category: "General",
    done: false,
  },
  {
    id: 8,
    name: "Get some sleep",
    importance: 3,
    category: "General",
    done: false,
  },
  {
    id: 1045836344,
    name: "Nou",
    importance: 2,
    category: "Testing",
    done: true,
  },
];
const userId = 1;
const userName = "Developer";

function App() {
  const [taskName, setTaskName] = useState("");
  const [taskList, setTaskList] = useState(taskData);
  const [canGetTaskList, setCanGetTaskList] = useState(true);
  const [taskCategory, setTaskCategory] = useState("");
  const [importance, setImportance] = useState("3");

  useEffect(() => {
    if (canGetTaskList) {
      fetch("http://localhost:8080/api/tasks?user=3", {
        method: "GET",
        mode: "cors",
        credentials: "omit",
        headers: {
          Accept: "*/*",
        },
      })
        .then((response) => response.json())
        .then((data) => setTaskList(data))
        .then((err) => (err ? console.error("Logging an error: " + err) : null))
        .then(setCanGetTaskList(false));
    }
    return () => {
      // setCanDoFetch(false);
    };
  });

  function createNewTask(taskName, taskCategory) {
    let nameString;
    let categoryString;
    let importanceInt;
    if (!(typeof taskName === "string")) {
      nameString = "";
    } else {
      nameString = taskName.slice();
    }

    if (!(typeof taskCategory === "string")) {
      categoryString = "";
    } else {
      categoryString = taskCategory.slice();
    }

    if (!(typeof importance === "string")) {
      if (!(typeof importance === "number")) {
        importanceInt = 3;
      } else {
        importanceInt = importance;
      }
    } else {
      importanceInt = parseInt(importance);
    }

    return {
      name: nameString,
      importance: importanceInt,
      category: categoryString,
      done: false,
    };
  }

  function addTask() {
    let newList = [...taskList];
    newList.push(createNewTask(taskName, taskCategory));
    setTaskList(newList);
    setTaskName("");
    setTaskCategory("");
    setImportance("3");
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

  function updateTaskName(event) {
    setTaskName(event.target.value);
  }

  function updateTaskCategory(event) {
    setTaskCategory(event.target.value);
  }

  function updateImportance(event) {
    setImportance(event.target.value);
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

  function colorClassForCategory(someString) {
    if (!(typeof someString === "string")) {
      return "color-label-general";
    } else {
      return `color-label-${someString
        .toLowerCase()
        .split(" ")
        .join("-")
        .slice(0, Math.min(someString.length, 10))}`;
    }
  }

  async function doPost(url = "", data) {
    const response = await fetch(url, {
      method: "POST",
      mode: "cors",
      credentials: "omit",
      headers: {
        Accept: "*/*",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    return response;
  }

  function persistToServer() {
    doPost("http://localhost:8080/api/task", taskList)
      .then((responseData) => console.log(responseData))
      .then((err) =>
        err ? console.error("Error while trying to save Tasks: " + err) : null
      )
      .then(setCanGetTaskList(true));
  }

  class LoginContainer extends React.Component {
    render() {
      return (
        <div className="login-container">
          You are logged in as{" "}
          <a className="user-name" href="/profile">
            {userName}
          </a>
        </div>
      );
    }
  }

  class TaskTable extends React.Component {
    render() {
      return (
        <table className="table table-success table-striped">
          <tbody>
            {taskList.map((task, index) => (
              <tr key={index}>
                <td>
                  <input
                    type="checkbox"
                    id={`check-${index}`}
                    onChange={handleCheckBoxClick}
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
                    className="far fa-trash-alt"
                    onClick={() => deleteTask(index)}
                  ></i>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }
  }

  class Navbar extends React.Component {
    render() {
      return (
        <div className="NavbarItems">
          <nav className="navbar navbar-light">
            {/* <h1 className="nav-logo">Menu </h1> */}
            <div className="navbar-nav">
              {MenuItems.map((menuItem, index) => (
                <div key={index}>
                  <a
                    className={`nav-item ${menuItem.cName}`}
                    href={menuItem.url}
                  >
                    {menuItem.title}
                  </a>
                </div>
              ))}
            </div>
          </nav>
        </div>
      );
    }
  }

  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/">
            {/* === INDEX PAGE === */}
            <div className="top-bar">
              <Navbar></Navbar>
              <LoginContainer></LoginContainer>
            </div>
            <div className="container">
              <div className="row justify-content-center">
                <div className="col">
                  <div className="inputs-container">
                    <div className="text-inputs-column">
                      <input
                        type="text"
                        placeholder="Enter task..."
                        className="form-control"
                        value={taskName}
                        onChange={updateTaskName}
                      />

                      {/* The input/list can't be extracted into a separate component-function, 
                      as it would lose either focus (on typing) or link with input */}
                      <input
                        list="default-categories"
                        id="category-choice"
                        name="category-choice"
                        className="form-control form-control-sm"
                        value={taskCategory}
                        onChange={updateTaskCategory}
                        placeholder="Enter task category..."
                      />
                      <datalist id="default-categories">
                        <option value="General" />
                        <option value="Study" />
                        <option value="Work" />
                        <option value="Exercise" />
                        <option value="House chore" />
                      </datalist>
                      {/* input-list ends here.. */}
                    </div>
                    <div className="slider-column">
                      <input
                        type="range"
                        list="tickmarks"
                        min="1"
                        max="5"
                        step="1"
                        value={importance}
                        onChange={updateImportance}
                      />
                      <datalist id="tickmarks">
                        <option value="1" label="Very minor (1)"></option>
                        <option value="3" label="Regular task (3)"></option>
                        <option value="5" label="Very important (5)"></option>
                      </datalist>
                    </div>
                  </div>

                  <div className="buttons-column">
                    <button className="btn btn-primary" onClick={addTask}>
                      ADD
                    </button>
                    <button
                      className="btn btn-primary btn-success"
                      onClick={persistToServer}
                    >
                      Sync <span className="fas fa-sync-alt"></span>
                    </button>
                  </div>

                  <TaskTable></TaskTable>
                </div>
              </div>
              {/* === END OF INDEX PAGE === */}
            </div>
          </Route>

          <Route path="/profile">
            <ProfilePage />
          </Route>
          <Route path="/events">This would be the events page...</Route>
          <Route path="/rewards">This would be the rewards page...</Route>
        </Switch>
      </Router>
    </div>
  );
}

export default App;
