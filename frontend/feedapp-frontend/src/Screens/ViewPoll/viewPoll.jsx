import React from "react";
import { Button } from "../../components/Button";
import "./viewPoll.css";
import {useNavigate} from "react-router-dom";

export const ViewPoll = () => {
    const navigate = useNavigate(); // Create a history instance


    const backToMainMenu = () => {
        navigate("/mainmenu")
    };
    return (
        <div className="view-poll">
            <div className="form">
                <div className="back-arrow" onClick={backToMainMenu}>
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M15 18L9 12L15 6" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                    </svg>
                </div>
                <div className="text-wrapper-2">Previous Polls</div>
                <div className={"previousPoll1"}>
                    <div className="text-wrapper-4">Is react a library?</div>

                    <Button
                        className="button-instance"
                        color="primary"
                        label="VIEW POLL"
                        size="large"
                        variant="contained"
                    />

                </div>
                <div className={"previousPoll1"}>
                    <div className="text-wrapper-4">Is react a library?</div>

                    <Button
                        className="button-instance"
                        color="primary"
                        label="VIEW POLL"
                        size="large"
                        variant="contained"
                    />

                </div>
                <div className={"previousPoll1"}>
                    <div className="text-wrapper-4">Is react a library?</div>

                    <Button
                        className="button-instance"
                        color="primary"
                        label="VIEW POLL"
                        size="large"
                        variant="contained"
                    />

                </div>








            </div>
        </div>
    );
};
