import React, { useState } from 'react';
import axios from 'axios';
import { setAuthToken } from '../setAuthToken';

function Cars() {
  const [cars, setCars] = useState();

  const handleGetCars = () => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    setAuthToken(token);

    console.log(axios.defaults.headers.common);

    axios
      // .get(`http://localhost:8080/api/persons/${userId}/cars`)
      .get(
        `http://localhost:8080/api/persons/a1eb8cf5-0ff7-42d2-b912-6f0a31cecf2a/cars`
      )
      .then((response) => {
        console.log(response.data);
        setCars(response.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div>
      <h1>CARS PAGE</h1>
      <button onClick={handleGetCars}>CONSOLE LOG CARS</button>
      {cars ? (
        <div>
          {cars.map((car) => (
            <ul>
              <li>{car.registration}</li>
            </ul>
          ))}
        </div>
      ) : (
        <div></div>
      )}
    </div>
  );
}

export default Cars;
