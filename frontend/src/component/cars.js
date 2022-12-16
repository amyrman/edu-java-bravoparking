import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { setAuthToken } from '../setAuthToken';
import { checkJwtExpired } from '../checkJwtExpired';

import '../css/cars.css';

function Cars() {
  const initialValues = {
    registration: '',
  };
  const [cars, setCars] = useState();
  const [registration, setRegistration] = useState('');
  const userId = localStorage.getItem('userId');

  const handleAddCar = (event) => {
    event.preventDefault();

    axios({
      method: 'post',
      url: `http://localhost:8080/api/cars`,
      data: {
        registration: registration,
        person: {
          userId: userId,
        },
      },
    });
  };

  const handleInputChange = (e) => {
    setRegistration(e.target.value);
  };

  const handleGetCars = () => {
    const token = localStorage.getItem('token');

    checkJwtExpired();
    setAuthToken(token);

    axios
      .get(
        `http://localhost:8080/api/persons/b1fcb6f5-a2ec-4c16-9c09-697233bacbab/cars`
      )
      // .get(`http://localhost:8080/api/persons/${userId}/cars`)
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
        <ul>
          {cars.map((car) => (
            <li key={car.id} className='cars_list_item'>
              {car.registration}
            </li>
          ))}
        </ul>
      ) : (
        <div></div>
      )}

      <form onSubmit={handleAddCar}>
        <input
          type='text'
          name='registration'
          placeholder='Car registration'
          onChange={handleInputChange}
        />
        <input type='submit' value='ADD CAR' />
      </form>
    </div>
  );
}

export default Cars;
