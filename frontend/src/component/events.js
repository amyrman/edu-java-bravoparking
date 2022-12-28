import React from 'react';
import axios from 'axios';

const handleGetEvents = () => {
  const userId = localStorage.getItem('userId');
  axios
    .get(`http://localhost:8080/api/events/person?id=${userId}&active=true`)
    .then((response) => {
      console.log(response);
    })
    .catch((err) => {
      console.log(err);
    });
};

function Events() {
  return (
    <div>
      <h1>EVENTS PAGE</h1>
      <button>CONSOLE LOG EVENTS</button>
    </div>
  );
}

export default Events;
