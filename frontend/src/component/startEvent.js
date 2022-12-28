import axios from "axios";
import { useState, useEffect } from "react";
import "../css/startEvent.css";

function StartEvent() {
	const [zones, setZones] = useState([]);
	const [cars, setCars] = useState([]);
	const [startZone, setStartZone] = useState();
	const [startCar, setStartCar] = useState();

	const handleCarChange = (event) => {
		setStartCar(event.target.value);
	};
	const handleZoneChange = (event) => {
		setStartZone(event.target.value);
	};

	const renderStartNewPark = (zones, cars) => {
		return (
			<div className="startEvent">
				<h3>Start new parking</h3>
				<form onSubmit={handleStartParking}>
					<label htmlfor="zones" id="zones"></label>
					<select id="zones" name="zones" onChange={handleZoneChange}>
						{zones.map((zone) => (
							<option key={zone.id}>{zone.name}</option>
						))}
					</select>
					<label htmlfor="cars" id="cars"></label>
					<select id="cars" name="cars" onChange={handleCarChange}>
						{cars.map((car) => (
							<option
								key={car.registration}
								value={car.registration}
							>
								{car.registration}
							</option>
						))}
					</select>
					<input
						type="submit"
						className="startpark"
						value="StartPark"
					/>
				</form>
			</div>
		);
	};

	const handleStartParking = async (event) => {
		event.preventDefault();
		//console.log(startCar);
		//console.log(startZone);
		const eventPayload = {
			zone: startZone,
			car: startCar,
		};
		const res = await axios
    // THIS IS WORK IN PROGRESS
/* 			.post("http://localhost:8080/api/events", eventPayload)
			.then((response) => {
				console.log(response);
				return response; */
			});
	};

	async function handleGetZones() {
		const res = await axios
			.get(`http://localhost:8080/api/zones`)
			.then((response) => {
				return response;
			})
			.catch((error) => console.log(error));
		setZones(res.data);
		setStartZone(res.data[0].name);
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
		setStartCar(res.data[0].registration);
	}
	useEffect(() => {
		handleGetZones();
		handleGetCars();
	}, []);

	return <div>{renderStartNewPark(zones, cars)}</div>;
}

export default StartEvent;
