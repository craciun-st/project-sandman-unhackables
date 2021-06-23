import './App.css';
import Navbar from './components/Navbar';
import AppPageBody from './components/AppPageBody';


function App() {
  return (
    <div className="App">
      <div className="container main">
    
        <Navbar/>

        <AppPageBody/>
      </div>

      <div className="container side-menu">
        This is the side menu
      </div>
    </div>
  );
}

export default App;
