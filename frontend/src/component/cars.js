import React, { useState } from 'react';
import axios from 'axios';
import { setAuthToken } from '../setAuthToken';
import { checkJwtExpired } from '../checkJwtExpired';

function Cars() {
  const [cars, setCars] = useState();

  const handleGetCars = () => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    checkJwtExpired();
    setAuthToken(token);

    axios
      .get(`http://localhost:8080/api/persons/${userId}/cars`)
      .then((response) => {
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
