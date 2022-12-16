import React, { Component } from 'react';
import { setAuthToken } from './setAuthToken';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Home from './component/home';
import Cars from './component/cars';
import Events from './component/events';

//check jwt token
const token = localStorage.getItem('token');
if (token) {
  setAuthToken(token);
}

class App extends Component {
  render() {
    return (
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
          </ul>
          <Routes>
            <Route exact path='/' element={<Home />}></Route>
            <Route exact path='/events' element={<Events />}></Route>
            <Route exact path='/cars' element={<Cars />}></Route>
          </Routes>
        </div>
      </Router>
    );
  }
}

export default App;
