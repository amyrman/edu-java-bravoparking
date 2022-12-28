import React from 'react';
import LoginForm from './loginform';
import Header from './header';

import '../css/main.css';

function Login() {
  return (
    <div className='page'>
      <Header />
      <LoginForm />
    </div>
  );
}

export default Login;
