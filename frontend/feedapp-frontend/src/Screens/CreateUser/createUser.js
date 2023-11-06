import React, { useState } from 'react';
import { Button } from "../../components/Button";
import { Link } from "../../components/Link";
import { TextField } from "../../components/TextField";
import "./createUser.css";
import {useNavigate} from "react-router-dom"
import {type} from "@testing-library/user-event/dist/type";
import axios from "axios";



export const CreateUser = () => {



    const navigate = useNavigate(); // Create a history instance




    const [emailValue, setEmailValue] = useState('');
    const [userNameValue, setUserNameValue] = useState('');
    const [confirmPasswordValue, setConfirmPasswordValue] = useState('');
    const [passwordValue, setpasswordValue] = useState('');

    const handleEmailChange = (event) => {
        setEmailValue(event.target.value);
    };
    const handleUserNameChange = (event) => {
        setUserNameValue(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setpasswordValue(event.target.value);
    }
    const handleConfirmPasswordValue = (event) => {
        setConfirmPasswordValue(event.target.value);
    };

    const validateEmail = (email) => {
        return email.match(
            /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    }

    const handleAlreadyUser = () => {
        navigate("/")
    }

    const handleCreateNewUser = () => {
        if (emailValue === '' || passwordValue === '' || userNameValue === '' || confirmPasswordValue === ''){
            alert("Fill out all fields")
        } else if(!validateEmail(emailValue)){
            alert("Enter a valid email")
        } else if(passwordValue !== confirmPasswordValue){
            alert("password does no match")
        } else {
            let user = {
                "userName": userNameValue,
                "email": emailValue,
                "password": passwordValue}

            axios.post("http://localhost:8080/customer", user).then(response => {
                navigate("/" )
                console.log(response)
            }).catch(error => {
                console.error("Error creating user:", error);
            });


        }

    }
    return (

        <div className="create-user">
            <div className="form">
                <div className="text-wrapper-3">Create new user</div>
                <div className="span">
                    <div className="text-wrapper-4">Already a user?</div>
                    <Link
                        className="link-instance"
                        color="primary"
                        onClick={handleAlreadyUser}
                        link="Login or continue as guest"
                        stateProp="enabled"
                        underline="on-hover"
                    />
                </div>
                <TextField
                    type="text"
                    className="text-field-instance"
                    value={userNameValue}
                    onChange={handleUserNameChange}
                    label="UserName:"
                    size="medium"
                    stateProp="enabled"
                    underline="https://c.animaapp.com/Enf9PaAO/img/underline-1.svg"
                    variant="standard"
                />
                <TextField
                    type="email"
                    className="text-field-instance"
                    value={emailValue}
                    onChange={handleEmailChange}
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
                <TextField
                    className="design-component-instance-node"
                    idName="password"
                    type = "password"
                    value={confirmPasswordValue}
                    onChange={handleConfirmPasswordValue}
                    label="Confirm password:"
                    size="medium"
                    stateProp="enabled"
                    underline="https://c.animaapp.com/Enf9PaAO/img/underline.svg"
                    variant="standard"
                />
                <Button
                    className="button-instance"
                    onClick={handleCreateNewUser}
                    color="primary"
                    label="CREATE NEW USER"
                    size="medium"
                    stateProp="enabled"
                    variant="contained"
                />

            </div>
        </div>
    );

}

export default CreateUser;
