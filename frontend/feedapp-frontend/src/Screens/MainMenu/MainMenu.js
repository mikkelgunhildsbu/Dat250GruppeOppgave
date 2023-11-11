import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './MainMenu.css';
import {useNavigate} from "react-router-dom"
import React, {useEffect} from "react";
import '../header.css'
import {LoginView} from "../Login";
import Cookies from 'js-cookie';
import axios from "axios";





function MainMenu() {
    const navigate = useNavigate(); // Create a history instance

    const token = Cookies.get('Token');
    const email = Cookies.get('Email');


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
        Cookies.remove("Token", "")
        Cookies.remove("Email", "")
        Cookies.remove("UserID", "")

    };


    axios.defaults.baseURL = "http://localhost:8080/"
    useEffect( () => {
        axios.get("customer?email=" + email,{
            headers:{
                "Authorization": token,
            }
        }).then(response =>{
            Cookies.set("UserID", response.data["userId"])
        }).catch(error => {
            console.log( error.message)

        })

    })

    return (
      <div className="main-menu">
          <div className="form">
              <div className="back-arrow" onClick={backToLogin}>
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M15 18L9 12L15 6" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
              </svg>
              </div>

              <div className={"loginAs"}>
                  <p>Logged in as: {email}</p>
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
