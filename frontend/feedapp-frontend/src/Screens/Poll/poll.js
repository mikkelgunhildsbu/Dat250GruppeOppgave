import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './poll.css';
import {useNavigate} from "react-router-dom"
import React, {useEffect, useState} from "react";
import '../header.css'
import {LoginView} from "../Login";
import { useLocation } from 'react-router-dom';
import axios from "axios";
import Cookies from "js-cookie";
import PollCountdownTimer from "../PollCountDownTimer";



function Poll() {
    const navigate = useNavigate(); // Create a history instance

    const location = useLocation();
    const pollData = location.state?.pollData;

    let email = Cookies.get("Email")
    let token = Cookies.get("Token")


    axios.defaults.baseURL = "http://localhost:8080/poll/"

    const green = {
        vote:"GREEN"
    }
    const voteYes = () => {
        axios.post(String(pollData["id"]),green) .then(response => {
            console.log("Updated Green:", response.data);
            navigate("/pollStatus", {state: {pollData: response.data}})
        })
    };


    const voteNo = () => {
        axios.post(String(pollData["id"]), "RED" ) .then(response => {
            console.log("Updated successfully:", response.data);
            navigate("/pollStatus", {state: {pollData: response.data}})

        })
    };

    const backToMainMenu = () => {
        navigate("/mainmenu")
    };

    const intervalTime = 5000;  // every 5 seconds

    useEffect(() => {
        const closingDateTime = new Date(pollData?.closingTime);

        const fetchData = () => {

            if (closingDateTime <= new Date()) {
                let closeStatus ={
                    status : "CLOSED"
                }
                axios.put(String(pollData["id"]), closeStatus , {
                    headers: {
                        "Authorization" : token
                    }
                }).then(response => {
                    console.log("Updated successfully:", response.data);
                    navigate("/pollStatus", {state: {pollData: response.data}})
                }).catch((error) =>(
                    console.log(error)
                ))
            }}
        const intervalId = setInterval(fetchData, intervalTime);
        return () => clearInterval(intervalId);

    })




    return (
        <div className="poll">
            <div className="form">
                <div className="back-arrow" onClick={backToMainMenu}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                    </svg>
                </div>

                <div className={"loginAs"}>
                    <p>Logged in as: {email}</p>
                </div>
                <div className="text-wrapper-3">{pollData?.question}</div>
                <p className="text-wrapper-3"><PollCountdownTimer closingTime={pollData?.closingTime} /> minutes</p>

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
