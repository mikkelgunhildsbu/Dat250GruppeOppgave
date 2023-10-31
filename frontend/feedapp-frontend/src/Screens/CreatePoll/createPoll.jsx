import React from "react";
import { Button } from "../../components/Button";
import { Switch } from "../../components/Switch";
import { TextField } from "../../components/TextField";
import "./createPoll.css";
import {useNavigate} from "react-router-dom";

export const CreatePollScreen = () => {
    const navigate = useNavigate(); // Create a history instance


    const backToMainMenu = () => {
        navigate("/mainmenu")
    };
    return (
        <div className="create-poll-screen">
                <div className="form">
                    <div className="back-arrow" onClick={backToMainMenu}>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15 18L9 12L15 6" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        </svg>
                    </div>
                    <div className="text-wrapper-2">Create Poll Question</div>

                    <TextField
                        className="text-field-instance"
                        hasValue={false}
                        label="Question"
                        size="medium"
                        stateProp="enabled"
                        underline="underline-3.svg"
                        variant="standard"
                    />
                    <TextField
                        className="text-field-instance"
                        hasValue={false}
                        label="Time limit"
                        size="medium"
                        stateProp="enabled"
                        underline="underline-2.svg"
                        variant="standard"
                    />

                    <div className="text-wrapper-3">Private</div>
                    <Switch
                        checked
                        className="switch-instance"
                        color="primary"
                        overlapGroupClassName="switch-2"
                        size="medium"
                        slideClassName="switch-3"
                        switchClassName="design-component-instance-node"


                    />

                    <Button className="button-instance" color="primary" label="CREATE POLL" size="large" variant="contained" />

                </div>
        </div>
    );
};
