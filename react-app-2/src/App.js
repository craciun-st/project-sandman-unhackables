import './App.css';
import Navbar from './components/Navbar';
import TaskTable from './components/TaskTable';
import Box2 from './components/Box2';
import Box3 from './components/Box3';
import Box4 from './components/Box4';

function App() {
  return (
    <div className="App">
      <div className="container main">
        <Navbar/>
        <TaskTable/>
        <Box2/>
        <Box3/>
        <Box4/>
        
      </div>
      <div className="container side-menu">
        This is the side menu
      </div>
    </div>
  );
}

export default App;
