import React, { useState } from "react";
import "../css/loginform.css"



const LoginForm = () => {

    const [popupStyle, showPopup] = useState("hide")

    const popup = () => {
        showPopup("login-popup")
        setTimeout(() => showPopup("hide"), 3000)
    }


    return (


        <div className="container">
            <h1 className="login-h1">Log in to your account</h1>
            <input type="text" placeholder="username" />
            <input type="password" placeholder="password" />

            <div className="login-btn" onClick={popup}>Login</div>
            <div className="register-btn">or register here!</div>

            <div className={popupStyle}>
                <h3>Login Failed</h3>
                <p>username or password incorrect</p>
            </div>
        </div>






    )
}

export default LoginForm;