import './App.css';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import {useState} from "react";
import Navbar from './components/Navbar';
import AppPageBody from './components/AppPageBody';
import LogoImage from './components/LogoImage';
import LoginButton from './components/authentication/LoginButton';
import logo from './util/resources/image.png'
import LogoutButton from "./components/authentication/LogoutButton";
import LoginPage from "./components/authentication/LoginPage";




function App() {

  const [loginStatus, setLoginStatus] = useState(true);

  function handleLoginButtonClick(event) {
      window.location.href = "/login"
  }
  
  return (
    <Router>
      <Switch>

          <Route exact path="/">
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
                      <LoginButton loginStatus={loginStatus} onClick={handleLoginButtonClick}/>
                      <LogoutButton loginStatus={loginStatus}/>

                  </div>
              </div>
          </Route>

          <Route path="/login">
              <LoginPage setLoginStatus={setLoginStatus}/>
          </Route>

      </Switch>
    </Router>


  );
}

export default App;
