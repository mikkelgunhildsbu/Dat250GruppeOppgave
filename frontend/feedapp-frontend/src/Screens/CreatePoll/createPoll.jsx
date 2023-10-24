import React from "react";
import { Button } from "../../components/Button";
import { Switch } from "../../components/Switch";
import { TextField } from "../../components/TextField";
import "./createPoll.css";

export const CreatePollScreen = () => {
    return (
        <div className="create-poll-screen">
            <div className="div-2">
                <div className="text-wrapper-2">Create Poll Question</div>
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
                    className="text-field-2"
                    hasValue={false}
                    label="Time limit"
                    size="medium"
                    stateProp="enabled"
                    underline="underline-2.svg"
                    variant="standard"
                />
                <div className="text-wrapper-3">Private</div>
            </div>
        </div>
    );
};
