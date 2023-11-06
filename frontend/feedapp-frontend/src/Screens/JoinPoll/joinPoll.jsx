import React, { useState, useEffect } from 'react';
import { Button } from "../../components/Button";
import { TextField } from "../../components/TextField";
import "./joinPoll.css";
import "../header.css"
import {useNavigate} from "react-router-dom";
import axios from "axios";


export const JoinPoll = () => {

    const navigate = useNavigate(); // Create a history instance

    const [inputValue, setInputValue] = useState('');
    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };

    const backToMainMenu = () => {
        navigate("/mainmenu")
    };

    const [data, setData] = useState(null);


    axios.defaults.baseURL = "http://localhost:8080/poll/"

    const handleJoinPoll = () => {
        if (inputValue === ""){
            alert("Enter poll ID")
        } else{
            axios.get(inputValue).then(response => {
                setData(response.data)
                navigate("/poll" , {state: {pollData: response.data}})
            }).catch(error => {
                alert("Poll does not exist " + error.message)
            })

            }

    }


    return (
        <div className="join-poll">
                <div className="form">
                    <div className="back-arrow" onClick={backToMainMenu}>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                        </svg>
                    </div>

                    <div className="text-wrapper">Join Poll</div>

                    <div className="text-wrapper">Enter PollID</div>

                    <TextField
                        type={"number"}
                        onChange={handleInputChange}
                        className="text-field-instance"
                        hasValue={false}
                        label="Poll ID"
                        size="medium"
                        stateProp="enabled"
                        underline="underline-2.svg"
                        variant="standard"
                     value={inputValue}/>

                    <Button
                        className="button-instance"
                        onClick={handleJoinPoll}
                        color="primary"
                        label="JOIN POLL"
                        size="large"
                        stateProp="enabled"
                        variant="contained"
                    />
                </div>
        </div>
    );
};
