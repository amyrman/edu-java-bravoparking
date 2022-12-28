import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { setAuthToken } from '../setAuthToken';
import { checkJwtExpired } from '../checkJwtExpired';

import '../css/cars.css';

function Cars() {
  const [cars, setCars] = useState();
  const [registration, setRegistration] = useState('');
  const userId = localStorage.getItem('userId');

  const handleAddCar = (event) => {
    event.preventDefault();
    setCars([...cars, { registration: registration }]);

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
    setRegistration('');
  };

  const handleInputChange = (e) => {
    setRegistration(e.target.value);
  };

  const handleGetCars = () => {
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

  const renderCarsList = () => {
    return cars ? (
      <ul>
        {cars.map((car) => (
          <li key={car.registration} className='cars_list_item'>
            {car.registration}
          </li>
        ))}
      </ul>
    ) : (
      <div></div>
    );
  };

  useEffect(() => {
    handleGetCars();
  }, []);

  return (
    <div>
      <h1>CARS PAGE</h1>
      {renderCarsList()}

      <form onSubmit={handleAddCar}>
        <input
          type='text'
          name='registration'
          placeholder='Car registration'
          onChange={handleInputChange}
          value={registration}
        />
        <input type='submit' value='ADD CAR' />
      </form>
    </div>
  );
}

export default Cars;
