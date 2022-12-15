import React, { useState } from 'react';
import axios from 'axios';
import { setAuthToken } from '../setAuthToken';

import '../css/loginform.css';

const LoginForm = () => {
  const initialValues = {
    username: '',
    password: '',
  };

  const [popupStyle, showPopup] = useState('hide');
  const [values, setValues] = useState(initialValues);

  const popup = () => {
    showPopup('login-popup');
    setTimeout(() => showPopup('hide'), 3000);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setValues({
      ...values,
      [name]: value,
    });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    //reqres registered sample user
    const loginPayload = {
      username: values.username,
      password: values.password,
    };

    try {
      const res = await axios
        .post('https://fungover.org/auth', loginPayload)
        .then((response) => {
          //get token from response
          const token = response.data.access_token;
          //get token duration
          const token_exp = response.data.expires_in;
          const expires = Date.now() + token_exp * 1000;

          //set JWT token to local
          localStorage.setItem('token', token);

          //set token duration to local
          localStorage.setItem('token_duration', expires);

          //set token to axios common header
          setAuthToken(token);

          //redirect user to home page
          // window.location.href = '/';

          return response;
        })
        .catch((err) => console.log(err));

      // if token get userid from backend by email
      if (res.data.access_token === localStorage.getItem('token')) {
        axios
          .get(`http://localhost:8080/api/login/${loginPayload.username}`)
          .then((response) => {
            // get userId from response
            const userId = response.data.userId;

            // set userId to local
            localStorage.setItem('userId', userId);
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        localStorage.setItem('userId', 'NaN');
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <form className='container' onSubmit={handleSubmit}>
      <h1 className='login-h1'>Log in to your account</h1>
      <input
        type='text'
        value={values.username}
        name='username'
        placeholder='username'
        onChange={handleInputChange}
      />
      <input
        type='password'
        value={values.password}
        name='password'
        placeholder='password'
        onChange={handleInputChange}
      />
      <input type='submit' className='login-btn' value='Login' />

      <div className='register-btn'>or register here!</div>

      <div className={popupStyle}>
        <h3>Login Failed</h3>
        <p>username or password incorrect</p>
      </div>
    </form>
  );
};

export default LoginForm;
