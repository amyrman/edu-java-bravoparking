import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Events() {
  const [events, setEvents] = useState([]);

  const renderEventsList = (events) => {
    return (
      <div>
        <ul>
          {events.map((event) => (
            <li key={event.id}>
              <p>ParkingZone: {event.zone.name}</p>
              <p>{event.active ? event.start : ''}</p>
              <p>{event.active ? event.stop : ''}</p>
              <p>Status: {event.active ? 'active' : 'finished'}</p>
              <p>Registration: {event.car.registration}</p>
              <p>---</p>
            </li>
          ))}
        </ul>
      </div>
    );
  };

  async function handleGetEvents() {
    const res = await axios
      .get(`http://localhost:8080/api/events`)
      .then((response) => {
        return response;
      })
      .catch((err) => {
        console.log(err);
      });
    setEvents(res.data);
  }

  useEffect(() => {
    handleGetEvents();
  }, []);

  return (
    <div>
      <h1>EVENTS PAGE</h1>
      {renderEventsList(events)}
    </div>
  );
}

export default Events;
