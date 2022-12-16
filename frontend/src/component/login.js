import React from 'react';
import LoginForm from './loginform';
import Header from './header';
import { checkJwtExpired } from '../checkJwtExpired';
import axios from 'axios';

import '../css/main.css';

const handleGetAllPersons = () => {
  axios
    .get('http://localhost:8080/api/cars')
    .then((response) => {
      console.log(response);
    })
    .catch((err) => {
      console.log(err);
    });
};

function Login() {
  return (
    <div className='page'>
      <Header />
      <LoginForm />

      <button onClick={handleGetAllPersons}>GET PERSONS</button>
      <button onClick={checkJwtExpired}>VALID?!</button>
    </div>
  );
}

export default Login;
