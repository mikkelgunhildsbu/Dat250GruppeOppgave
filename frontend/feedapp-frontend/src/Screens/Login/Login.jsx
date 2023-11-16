import React, { useState } from 'react';
import { Button } from "../../components/Button";
import { Link } from "../../components/Link";
import { TextField } from "../../components/TextField";
import "./Login.css";
import {useNavigate} from "react-router-dom"
import {type} from "@testing-library/user-event/dist/type";
import axios from "axios";
import Cookies from 'js-cookie';



export const LoginView = () => {

    Cookies.set('userKey', "2");

    const [user, setUser] = useState({});


    const navigate = useNavigate(); // Create a history instance

    const handleContinueAsGuestClick = () => {
        navigate("/mainmenu", {state: {userData : user }})
        Cookies.set("Email", "GUEST")

        console.log(user)
    };



    const [inputValue, setInputValue] = useState('');
    const [passwordValue, setpasswordValue] = useState('');

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setpasswordValue(event.target.value);

    }

    const validateEmail = (email) => {
        return email.match(
            /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    }
    const loginRequest = {
        email: inputValue,
        password: passwordValue
    };

    const handleSignIn = () => {

        if (passwordValue === '' || inputValue === ''){
            alert("Both email and password must be filled")
        }else if(!validateEmail(inputValue)){
            alert("enter valid email")
        }else {
            axios.post("http://localhost:8080/login", loginRequest).then((response) => {
                console.log('Login successful');
                Cookies.set("Email", inputValue)
                Cookies.set('Token', response.data.token);
                navigate("/mainmenu")

            }).catch((error) => {
                if (error.response) {
                    if (error.response.status === 403) {
                        alert("Access denied! Wrong password or email!");
                    } else {
                        // Handle other HTTP errors
                        alert("An unexpected error occurred: " + error.response.status);
                    }}
            });
        }
    }

    const handleLinkClick = () => {
        navigate("/createUser")
    }


    return (

        <div className="login-view">
                <div className="form">
                    <div className="text-wrapper-3">Sign in</div>
                    <div className="span">
                        <div className="text-wrapper-4">New user?</div>
                        <Link
                            className="link-instance"
                            onClick = {handleLinkClick}
                            color="primary"
                            link="Create an account"
                            stateProp="enabled"
                            underline="on-hover"
                        />
                    </div>
                    <TextField
                        type="email"
                        className="text-field-instance"
                        value={inputValue}
                        onChange={handleInputChange}
                        label="Email:"
                        size="medium"
                        stateProp="enabled"
                        underline="https://c.animaapp.com/Enf9PaAO/img/underline-1.svg"
                        variant="standard"
                    />
                    <TextField
                        className="design-component-instance-node"
                        idName="password"
                        type = "password"
                        value={passwordValue}
                        onChange={handlePasswordChange}
                        label="Password:"
                        size="medium"
                        stateProp="enabled"
                        underline="https://c.animaapp.com/Enf9PaAO/img/underline.svg"
                        variant="standard"
                    />
                    <Button
                        className="button-instance"
                        color="primary"
                        label="SIGN IN"
                        size="medium"
                        stateProp="enabled"
                        variant="contained"
                        onClick={handleSignIn}
                    />
                    <Button
                        className="button-instance"
                        onClick={handleContinueAsGuestClick}
                        color="secondary"
                        label="CONTINUE AS GUEST"
                        size="large"
                        stateProp="enabled"
                        variant="outlined"

                    />
                </div>
        </div>
    );
};
