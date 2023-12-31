import React, {useState} from "react";
import { Button } from "../../components/Button";
import { Switch } from "../../components/Switch";
import { TextField } from "../../components/TextField";
import "./createPoll.css";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import Cookies from 'js-cookie';


export const CreatePollScreen = () => {
    const navigate = useNavigate();

    const [isPrivate, setIsPrivate] = useState(false);


    const handleSwitchChange = (checked) => {
        setIsPrivate(checked)

    };

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

    const token = Cookies.get("Token")
    const userId = Cookies.get("UserID")
    const email = Cookies.get("Email")
    const handleCreatePoll = () => {
        if (questionValue === '' || timeValue === ''){
            alert("Please fill out all fields")
        } else {
            let data = {
                "userId": userId,
                "status": "OPEN",
                "question": questionValue,
                "timeLimitInMinutes": timeValue,
                "privatePoll": isPrivate,

            }
            axios.post("http://localhost:8080/poll", data,{
                headers:{
                    "Authorization": token,
                }
            }).then(response => {
                setQuestionValue('');
                navigate("/pollStatus" ,{state: {pollData: response.data}})
                alert("created a poll with pollId" + response.data["id"] )


            }).catch(error => {
                alert(error)
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
                    <div className={"loginAs"}>
                        <p>Logged in as: {email}</p>
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
                        checked={isPrivate}
                        onChange={handleSwitchChange}
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
