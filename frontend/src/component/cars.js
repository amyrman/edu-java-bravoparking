import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { setAuthToken } from '../setAuthToken';
import { checkJwtExpired } from '../checkJwtExpired';

import '../css/cars.css';

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

  useEffect(() => {
    handleGetCars();
  }, []);

  return (
    <div>
      <h1>CARS PAGE</h1>
      {cars ? (
        <div>
          <ul>
            {cars.map((car) => (
              <li key={car.id} className='cars_list_item'>
                {car.registration}
              </li>
            ))}
          </ul>
        </div>
      ) : (
        <div></div>
      )}
    </div>
  );
}

export default Cars;
