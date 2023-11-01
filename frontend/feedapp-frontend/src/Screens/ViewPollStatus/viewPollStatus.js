import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './viewPollStatus.css';
import {useNavigate} from "react-router-dom"
import React from "react";
import '../header.css'
import {LoginView} from "../Login";
import { useLocation } from 'react-router-dom';
import axios from "axios";





function ViewPollStatus() {
    const navigate = useNavigate(); // Create a history instance

    const location = useLocation();
    const pollData = location.state?.pollData;


    axios.defaults.baseURL = "http://localhost:8080/poll/"

    const updateGreenCount = {
        /*greenCount : pollData["greenCount"] + 1*/
    }

    const updateRedCount ={
        //redCount : pollData["redCount"] + 1
    }


    const voteYes = () => {
        axios.put(String(pollData["id"]), updateGreenCount ) .then(response => {
            console.log("Updated successfully:", response.data);
        })
    };

    const voteNo = () => {
        axios.put(String(pollData["id"]), updateRedCount ) .then(response => {
            console.log("Updated successfully:", response.data);
        })
    };

    const backToMainMenu = () => {
        navigate("/mainmenu")
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
                    <p>Logged in as:</p>
                </div>
                <div className="text-wrapper-3">{pollData?.question}</div>
                <p className="text-wrapper-3">{pollData?.timeLimitInMinutes} minutes</p>

                <div className={"resultDiv"} color={"primary"}>
                    Yes = {pollData?.greenCount}
                </div>
                <div className={"resultDiv"} color={"secondary"}>
                    No = {pollData?.redCount}
                </div>


            </div>
        </div>
    );
}

export default ViewPollStatus;
