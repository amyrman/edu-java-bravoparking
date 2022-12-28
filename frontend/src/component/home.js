import axios from "axios";
import React, { useEffect, useState } from "react";
import { checkJwtExpired } from "../checkJwtExpired";
import { setAuthToken } from "../setAuthToken";

import "../css/home.css";

function Home() {
	const [userName, setUserName] = useState();
	const [userEmail, setUserEmail] = useState();

	const fetchUser = () => {
		checkJwtExpired();
		setAuthToken(localStorage.getItem("token"));
		axios
			.get(
				`http://localhost:8080/api/persons/${localStorage.getItem(
					"userId"
				)}`
			)
			.then((response) => {
				setUserName(
					response.data.firstName + " " + response.data.lastName
				);
				setUserEmail(response.data.email);
			})
			.catch((err) => {
				console.log(err);
			});
	};

	useEffect(() => {
		fetchUser();
	}, []);

	return (
		<div>
			<h3>Welcome back, {userName}</h3>
			<h4>
				Your email adress is:
				<p>{userEmail}</p>
			</h4>
		</div>
	);
}
export default Home;
