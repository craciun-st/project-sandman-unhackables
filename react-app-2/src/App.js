import './App.css';
import Navbar from './components/Navbar';
import AppPageBody from './components/AppPageBody';


function App() {

  
  return (
    <div className="App">
      <div className="main-part">
    
        <Navbar/>

        <AppPageBody/>
      </div>

      <div className="side-menu" id="side-menu">
        This is the side menu
      </div>
    </div>
  );
}

export default App;
