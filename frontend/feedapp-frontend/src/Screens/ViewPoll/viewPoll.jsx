import React from "react";
import { Button } from "../../components/Button";
import "./viewPoll.css";

export const ViewPoll = () => {
    return (
        <div className="view-poll">
            <div className="div">
                <div className="text-wrapper-2">Is react a library?</div>
                <p className="p">Is Messi a football player?</p>
                <p className="text-wrapper-3">Were 50 cent and Charli Chaplin alive at the same time?</p>
                <p className="text-wrapper-4">Is Michigan larger than Great Britain?</p>
                <p className="text-wrapper-5">There are 14 bones in a human foot?</p>
                <p className="text-wrapper-6">The population of the world has doubled the last 50 years?</p>
                <Button className="button-instance" color="primary" label="VIEW POLL" size="large" variant="contained" />
                <Button
                    className="design-component-instance-node"
                    color="primary"
                    label="VIEW POLL"
                    size="large"
                    variant="contained"
                />
                <Button className="button-2" color="primary" label="VIEW POLL" size="large" variant="contained" />
                <Button className="button-3" color="primary" label="VIEW POLL" size="large" variant="contained" />
                <Button className="button-4" color="primary" label="VIEW POLL" size="large" variant="contained" />
                <Button className="button-5" color="primary" label="VIEW POLL" size="large" variant="contained" />
                <div className="text-wrapper-7">Previous Polls</div>
            </div>
        </div>
    );
};
