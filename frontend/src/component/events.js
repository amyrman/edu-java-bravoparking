import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Events() {
  const [events, setEvents] = useState([]);
  const [newStopTime, setNewStopTime] = useState();
  const [currentEvent, setcurrentEvent] = useState();

  const handleAddMinutes = () => {
    setNewStopTime(newStopTime + 900000);
  };

  const handleSetStopTime = (event) => {
    let currentStopTime = event.stop.replace(/\s/g, 'T').concat('Z');
    let currentStopMilli = new Date(currentStopTime);
    setNewStopTime(Date.parse(currentStopMilli.toUTCString()));
    console.log(currentStopTime);
    console.log(Date.parse(currentStopMilli.toUTCString()));
  };

  const handleEnableUpdate = (event) => {
    return (
      <div>
        <button
          onClick={() => {
            setcurrentEvent(event);
            handleSetStopTime(event);
          }}
        >
          Uppdatera?
        </button>
      </div>
    );
  };

  const renderUpdateTime = (event) => {
    return (
      <div>
        <button onClick={handleAddMinutes}>+ 15min</button>
        <p>{new Date(newStopTime).toUTCString()}</p>
        <button>Uppdatera</button>
      </div>
    );
  };

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
              {event.active ? handleEnableUpdate(event) : ''}
              {currentEvent && event.active ? renderUpdateTime() : ''}
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
