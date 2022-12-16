import React from 'react';
import axios from 'axios';

const handleGetCars = () => {
  const userId = localStorage.getItem('userId');
  axios
    .get(`http://localhost:8080/api/persons//${userId}/cars`)
    .then((response) => {
      console.log(response);
    })
    .catch((err) => {
      console.log(err);
    });
};

function Cars() {
  return (
    <div>
      <h1>CARS PAGE</h1>
      <button onClick={handleGetCars}>CONSOLE LOG CARS</button>
    </div>
  );
}

export default Cars;
