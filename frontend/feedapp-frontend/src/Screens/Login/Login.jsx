import React, { useState } from 'react';
import { Button } from "../../components/Button";
import { Link } from "../../components/Link";
import { TextField } from "../../components/TextField";
import "./Login.css";
import {useNavigate} from "react-router-dom"



export const LoginView = () => {
    const navigate = useNavigate(); // Create a history instance

    const handleContinueAsGuestClick = () => {
        navigate("/mainmenu")
    };

    const [inputValue, setInputValue] = useState('');
    const [passwordValue, setpasswordValue] = useState('');

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setpasswordValue(event.target.value);

    }


    return (

        <div className="login-view">
            <div className="form-wrapper">
                <div className="form">
                    <div className="text-wrapper-3">Sign in</div>
                    <div className="span">
                        <div className="text-wrapper-4">New user?</div>
                        <Link
                            className="link-instance"
                            color="primary"
                            link="Create an account"
                            stateProp="enabled"
                            underline="on-hover"
                        />
                    </div>
                    <TextField
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
        </div>
    );
};
