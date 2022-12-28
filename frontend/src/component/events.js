import React, { useState, useEffect } from "react";
import axios from "axios";

import "../css/events.css";

function Events() {
	const [events, setEvents] = useState([]);
	const [zones, setZones] = useState([]);
	const [cars, setCars] = useState([]);

	const renderStartNewPark = (zones, cars) => {
		return (
			<div className="startEvent">
				<h3>Start new parking</h3>
				<form>
					<label for="zones" id="zones"></label>
					<select id="zones" name="zones">
						{zones.map((zone) => (
							<option key={zone.id}>{zone.name}</option>
						))}
					</select>
				</form>
				<form>
					<label for="cars" id="cars"></label>
					<select id="cars" name="cars">
						{cars.map((car) => (
							<option
								key={car.registration}
								value={car.registration}
							>
								{car.registration}
							</option>
						))}
					</select>
				</form>
			</div>
		);
	};

	const renderEventsList = (events) => {
		return (
			<div>
				<ul>
					{events.map((event) => (
						<li
							key={event.id}
							className={event.active ? "active" : "finished"}
						>
							<p>ParkingZone: {event.zone.name}</p>
							<p>{event.active ? event.start : ""}</p>
							<p>{event.active ? event.stop : ""}</p>
							<p>
								Status: {event.active ? "active" : "finished"}
							</p>
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

	async function handleGetZones() {
		const res = await axios
			.get(`http://localhost:8080/api/zones`)
			.then((response) => {
				return response;
			})
			.catch((error) => console.log(error));
		setZones(res.data);
	}

	async function handleGetCars() {
		const userId = localStorage.getItem("userId");
		const res = await axios
			.get(`http://localhost:8080/api/persons/${userId}/cars`)
			.then((response) => {
				return response;
			})
			.catch((err) => console.log(err));

		setCars(res.data);
		//console.log(res.data);
	}

	useEffect(() => {
		handleGetEvents();
		handleGetZones();
		handleGetCars();
	}, []);

	return (
		<div>
			<h1>EVENTS PAGE</h1>
			{renderStartNewPark(zones, cars)}
			<h3>History</h3>
			{renderEventsList(events)}
		</div>
	);
}

export default Events;
