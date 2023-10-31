import React from "react";
import { Button } from "../../components/Button";
import { TextField } from "../../components/TextField";
import "./joinPoll.css";
import "../header.css"
import {useNavigate} from "react-router-dom";



export const JoinPoll = () => {

    const navigate = useNavigate(); // Create a history instance


    const backToMainMenu = () => {
        navigate("/mainmenu")
    };
    return (
        <div className="join-poll">
                <div className="form">
                    <div className="back-arrow" onClick={backToMainMenu}>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15 18L9 12L15 6" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>

                    <div className="text-wrapper">Join Poll</div>

                    <div className="text-wrapper">Enter PollID</div>

                    <TextField
                        className="text-field-instance"
                        hasValue={false}
                        label="Poll ID"
                        size="medium"
                        stateProp="enabled"
                        underline="underline-2.svg"
                        variant="standard"
                    />

                    <Button
                        className="button-instance"
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
