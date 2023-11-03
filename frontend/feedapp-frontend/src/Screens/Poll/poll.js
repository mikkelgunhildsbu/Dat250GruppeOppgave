import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './poll.css';
import {useNavigate} from "react-router-dom"
import React, {useEffect, useState} from "react";
import '../header.css'
import {LoginView} from "../Login";
import { useLocation } from 'react-router-dom';
import axios from "axios";



function Poll() {
    const navigate = useNavigate(); // Create a history instance

    const location = useLocation();
    const pollData = location.state?.pollData;


    axios.defaults.baseURL = "http://localhost:8080/poll/"

    const updateGreenCount = {
        greenCount : pollData["greenCount"] + 1
    }

    const updateRedCount ={
        redCount : pollData["redCount"] + 1
    }


    const voteYes = () => {
        axios.put(String(pollData["id"]), updateGreenCount ) .then(response => {
            console.log("Updated successfully:", response.data);
            navigate("/pollStatus", {state: {pollData: response.data}})
        })
    };

    const voteNo = () => {
        axios.put(String(pollData["id"]), updateRedCount ) .then(response => {
            console.log("Updated successfully:", response.data);
            navigate("/pollStatus", {state: {pollData: response.data}})

        })
    };

    const backToMainMenu = () => {
        navigate("/mainmenu")
    };

    /*const [remainingTime, setRemainingTime] = useState(pollData?.timeLimitInMinutes * 60); // convert minutes to seconds

    useEffect(() => {
        // Update the document title using the browser API
        const timer = setInterval(() => {
            setRemainingTime((prevTime) => {
                if (prevTime <= 1) {
                    clearInterval(timer); // clear timer if it reaches zero
                    // Here you can handle what happens when the timer ends, for example:
                    navigate('/pollStatus', {state:{pollData}})
                }
                return prevTime - 1; // decrease time by one second
            });
        }, 1000);

        // Clean up the interval on component unmount
        return () => clearInterval(timer);
    }, [navigate]);

    // Function to display the remaining time in minutes and seconds
    const formatTime = (seconds) => {
        const minutes = Math.floor(seconds / 60);
        const secs = seconds % 60;
        return `${minutes}:${secs < 10 ? '0' : ''}${secs}`;
    };*/

    return (
        <div className="poll">
            <div className="form">
                <div className="back-arrow" onClick={backToMainMenu}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                    </svg>
                </div>

                <div className={"loginAs"}>
                    <p>Logged in as:</p>
                </div>
                <div className="text-wrapper-3">{pollData?.question}</div>
                <p className="text-wrapper-3">{pollData?.timeLimitInMinutes} minutes</p>

                <Button
                    className="button-instance"
                    color="primary"
                    label="YES"
                    size="large"
                    stateProp="enabled"
                    variant="contained"
                    onClick={voteYes}
                />
                <Button
                    className="button-instance"
                    color="secondary"
                    label="NO"
                    size="large"
                    stateProp="enabled"
                    variant="contained"
                    onClick={voteNo}
                />
            </div>
        </div>
    );
}

export default Poll;
