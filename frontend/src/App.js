import logo from './logo.svg';
import axios from 'axios';
import { setAuthToken } from './setAuthToken';
import { checkJwtExpired } from './checkJwtExpired';
import './App.css';

function App() {
  //check jwt token
  const token = localStorage.getItem('token');
  if (token) {
    setAuthToken(token);
  }

  const handleSubmit = async (email, pass) => {
    //reqres registered sample user
    const loginPayload = {
      username: 'test@testmail.se',
      password: 'password',
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

  return (
    <div className='App'>
      <header className='App-header'>
        <img src={logo} className='App-logo' alt='logo' />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <button onClick={handleSubmit}>LOGIN</button>
        <button onClick={handleGetAllPersons}>GET PERSONS</button>
        <button onClick={checkJwtExpired}>VALID?!</button>
      </header>
    </div>
  );
}

export default App;
