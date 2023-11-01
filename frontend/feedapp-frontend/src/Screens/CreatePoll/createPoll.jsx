import React, {useState} from "react";
import { Button } from "../../components/Button";
import { Switch } from "../../components/Switch";
import { TextField } from "../../components/TextField";
import "./createPoll.css";
import {useNavigate} from "react-router-dom";
import axios from "axios";

export const CreatePollScreen = () => {
    const navigate = useNavigate(); // Create a history instance


    const backToMainMenu = () => {
        navigate("/mainmenu")
    };

    const [questionValue, setQuestionValue] = useState('');
    const [timeValue, setTimeValue] = useState('');

    const handleQuestionChange = (event) => {
        setQuestionValue(event.target.value);
    };

    const handleTimeChange = (event) => {
        setTimeValue(event.target.value);

    }



    const handleCreatePoll = () => {
        if (questionValue === '' || timeValue === ''){
            alert("Please fill out all fields")
        } else {
            let data = {
                "userId": 1,
                "status": "OPEN",
                "question": questionValue,
                "timeLimitInMinutes": timeValue,
                "privatePoll": true,

            }
            axios.post("http://localhost:8080/poll", data).then(response => {
                setQuestionValue('');
                setTimeValue('');
                navigate("/poll" ,{state: {pollData: response.data}})
            }).catch(error => {
                console.error("Error posting data:", error);
            });

        }
    }
    return (
        <div className="create-poll-screen">
                <div className="form">
                    <div className="back-arrow" onClick={backToMainMenu}>
                        <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                        </svg>
                    </div>
                    <div className="text-wrapper-2">Create Poll Question</div>

                    <TextField
                        onChange={handleQuestionChange}
                        className="text-field-instance"
                        hasValue={false}
                        label="Question"
                        size="medium"
                        stateProp="enabled"
                        underline="underline-3.svg"
                        variant="standard"
                     value={questionValue}/>
                    <TextField
                        type={"number"}
                        onChange={handleTimeChange}
                        className="text-field-instance"
                        hasValue={false}
                        label="Time limit"
                        size="medium"
                        stateProp="enabled"
                        underline="underline-2.svg"
                        variant="standard"
                     value={timeValue}/>

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

                    <Button onClick={handleCreatePoll} className="button-instance" color="primary" label="CREATE POLL" size="large" variant="contained" />

                </div>
        </div>
    );
};
