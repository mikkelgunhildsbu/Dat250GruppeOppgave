import React from "react";
import { Button } from "../../components/Button";
import { TextField } from "../../components/TextField";
import "./joinPoll.css";

export const JoinPoll = () => {
    return (
        <div className="join-poll">
            <div className="div">
                <TextField
                    className="text-field-instance"
                    hasValue={false}
                    label="Poll ID"
                    size="medium"
                    stateProp="enabled"
                    underline="underline-2.svg"
                    variant="standard"
                />
                <Button className="button-instance" color="primary" label="JOIN POLL" size="large" variant="contained" />
            </div>
        </div>
    );
};
