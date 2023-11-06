import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './MainMenu.css';
import {useNavigate} from "react-router-dom"
import React from "react";
import '../header.css'
import {LoginView} from "../Login";





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

    const backToLogin = () => {
        navigate("/")
    };

    return (
      <div className="main-menu">
          {/*<header className={"head"}>
              <div className="header-item">Back</div>
              <div className="header-item"><img src="../logo.png" alt="img"/></div>
              <div className="header-item">Log in</div>
          </header> */}

          <div className="form">
              <div className="back-arrow" onClick={backToLogin}>
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
              </svg>
              </div>

              <div className={"loginAs"}>
                  <p>Logged in as:</p>
              </div>
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
