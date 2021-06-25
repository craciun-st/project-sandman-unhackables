import './App.css';
import Navbar from './components/Navbar';
import AppPageBody from './components/AppPageBody';
import LogoImage from './components/LogoImage';
import LoginButton from './components/authentication/LoginButton';
import logo from './util/resources/image.png'


const loginStatus = true;

function App() {


  
  return (
    <div className="App">
      <div className="main-part">
    
        <Navbar
        logo={logo} loginStatus={loginStatus}/>

        <AppPageBody loginStatus={loginStatus}/>
      </div>

      <div className="side-menu" id="side-menu">
        This is the side menu
        <LogoImage
          logo={logo}
          imageWidth="80%"/>
        <LoginButton loginStatus={loginStatus}/>

      </div>
    </div>
  );
}

export default App;
