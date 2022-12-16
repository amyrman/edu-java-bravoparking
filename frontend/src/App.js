import React, { Component, useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './component/login';
import Home from './component/home';
import Cars from './component/cars';
import Events from './component/events';
import { checkJwtExpired } from './checkJwtExpired';
import { logoutUser } from './logoutUser';

function App() {
  const [loggedIn, setLoggedIn] = useState();

  const logout = () => {
    setLoggedIn(false);
    logoutUser();
  };

  useEffect(() => {
    checkJwtExpired();
    if (localStorage.getItem('token')) {
      setLoggedIn(true);
    } else {
      setLoggedIn(false);
    }
  }, []);

  const render = () => {
    return loggedIn ? (
      <Router>
        <div className='App'>
          <ul className='App-header'>
            <li>
              <Link to='/'>Home</Link>
            </li>
            <li>
              <Link to='/events'>Events</Link>
            </li>
            <li>
              <Link to='/cars'>Cars</Link>
            </li>
            <li>
              <button onClick={logout}>LogOut</button>
            </li>
          </ul>
          <Routes>
            <Route exact path='/' element={<Home />}></Route>
            <Route exact path='/events' element={<Events />}></Route>
            <Route exact path='/cars' element={<Cars />}></Route>
          </Routes>
        </div>
      </Router>
    ) : (
      <Login />
    );
  };

  return render();
}

export default App;
