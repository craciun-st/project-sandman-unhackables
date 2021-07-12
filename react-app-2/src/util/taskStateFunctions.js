export function updateTaskName(event, setterFunc) {
    setterFunc(event.target.value);            
}

export function updateTaskCategory(event, setterFunc) {
    setterFunc(event.target.value);
}

export function updateStateValue(event, setterFunc) {
    setterFunc(event.target.value);
}

export function createNewTask(taskName, taskCategory, importance = null) {
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

export function addTask(taskList, taskMap, taskSetterMap) {
    let newList = [...taskList];
    newList.push(createNewTask(taskMap.name, taskMap.category, taskMap.importance));
    taskSetterMap['setTaskList'](newList);
    taskSetterMap['setTaskName']("");
    taskSetterMap['setTaskCategory']("");
    taskSetterMap['setTaskImportance']("3");
    console.log(newList);
}