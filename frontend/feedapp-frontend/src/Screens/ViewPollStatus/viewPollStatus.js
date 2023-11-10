import React, { useState, useEffect } from 'react';
import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './viewPollStatus.css';
import { useNavigate, useLocation } from 'react-router-dom';
import axios from "axios";
import Cookies from "js-cookie";
import PollCountdownTimer from "../PollCountDownTimer";

function ViewPollStatus() {
    const navigate = useNavigate();
    const location = useLocation();
    const initialPollData = location.state?.pollData;

    const [pollData, setPollData] = useState(initialPollData);

    const intervalTime = 5000;  // every 5 seconds

    let email = Cookies.get("Email")

    useEffect(() => {
        const fetchData = () => {
            axios.get(`http://localhost:8080/poll/${pollData["id"]}`)
                .then(response => {
                    setPollData(response.data);
                })
                .catch(error => {
                    console.error('Error fetching updates:', error);
                });
        };

        const intervalId = setInterval(fetchData, intervalTime);

        // Clear the interval when the component is unmounted
        return () => clearInterval(intervalId);
    }, [pollData["id"]]); // Depend on pollData["id"] so if it changes, the effect reruns

    const backToMainMenu = () => {
        navigate("/mainmenu");
    };

    return (
        <div className="pollStatus">
            <div className="form">
                <div className="back-arrow" onClick={backToMainMenu}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                    </svg>
                </div>


                <div className={"loginAs"}>
                    <p>Logged in as: {email}</p>
                </div>
                <div className="text-wrapper-3">Results: {pollData?.question}</div>
                <p className="text-wrapper-3"><PollCountdownTimer closingTime={pollData?.closingTime} /> minutes remaining</p>

                <div className={"resultDiv"}  id={"yes"}>
                    YES: {pollData?.greenCount}
                </div>
                <div className={"resultDiv"} id={"no"}>
                    NO: {pollData?.redCount}
                </div>


            </div>
        </div>
    );
}

export default ViewPollStatus;
