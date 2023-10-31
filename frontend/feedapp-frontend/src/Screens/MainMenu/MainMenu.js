import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './MainMenu.css';
import {useNavigate} from "react-router-dom"
import React from "react";



function MainMenu() {
    const navigate = useNavigate(); // Create a history instance

    const joinPollClick = () => {
        navigate("/joinPoll")
    };

    const createPollClick = () => {
        navigate("/createPoll")
    };

    const viewPollClick = () => {
        navigate("/viewPoll")
    };
    return (
      <div className="main-menu">
          <div className="form">
              <div className="text-wrapper-3">Main Menu</div>
              <Button
                    className="button-instance"
                    color="primary"
                    label="JOIN POLL"
                    size="large"
                    stateProp="enabled"
                    variant="contained"
                    onClick={joinPollClick}
                />
                <Button
                    className="button-instance"
                    color="primary"
                    label="CREATE POLL"
                    size="large"
                    stateProp="enabled"
                    variant="contained"
                    onClick={createPollClick}
                />
                <Button
                    className="button-instance"
                    color="primary"
                    label="VIEW POLL"
                    size="large"
                    stateProp="enabled"
                    variant="contained"
                    onClick={viewPollClick}
                />
          </div>
      </div>
  );
}

export default MainMenu;
