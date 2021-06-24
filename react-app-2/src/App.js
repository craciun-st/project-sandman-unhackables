import './App.css';
import Navbar from './components/Navbar';
import AppPageBody from './components/AppPageBody';
import LogoImage from './util/resources/LogoImage';
import LoginButton from './components/authentication/LoginButton';
import logo from './util/resources/image.png'


function App() {


  
  return (
    <div className="App">
      <div className="main-part">
    
        <Navbar
        logo={logo}/>

        <AppPageBody/>
      </div>

      <div className="side-menu" id="side-menu">
        This is the side menu
        <LogoImage
          logo={logo}
          imageWidth="80%"/>
        <LoginButton/>

      </div>
    </div>
  );
}

export default App;
