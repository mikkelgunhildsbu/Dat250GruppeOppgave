import React, { useState, useEffect } from "react";
import { Button } from "../../components/Button";
import "./viewPoll.css";
import { useNavigate } from "react-router-dom";
import axios from "axios"; // Ensure you have axios installed

export const ViewPoll = () => {
    const navigate = useNavigate();
    const [polls, setPolls] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/poll?userId=1")
            .then(response => {
                setPolls(response.data);
            })
            .catch(error => {
                console.error('Error fetching polls:', error);
            });
    }, []);

    const backToMainMenu = () => {
        navigate("/mainmenu");
    };

    return (
        <div className="view-poll">
            <div className="form">
                <div className="back-arrow" onClick={backToMainMenu}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
                    </svg>                </div>
                <div className="text-wrapper-2">Previous Polls</div>

                {polls.map((poll) => (
                    <div key={poll.id} className="previousPoll1">
                        <div className="text-wrapper-4">{poll.question} id: {poll.id}</div>
                        <Button
                            onClick={() => navigate('/pollStatus', {state: {pollData: poll}})}
                            className="button-instance"
                            color="primary"
                            label="VIEW POLL"
                            size="large"
                            variant="contained"
                        />
                    </div>
                ))}
            </div>
        </div>
    );
};
