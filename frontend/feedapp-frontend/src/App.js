import React from 'react';
import { Route, Routes } from 'react-router-dom';
import { LoginView } from "./Screens/Login";
import MainMenu from './Screens/MainMenu/MainMenu';
import {JoinPoll} from "./Screens/JoinPoll";
import {CreatePollScreen} from "./Screens/CreatePoll";
import {ViewPoll} from "./Screens/ViewPoll"; // Import other components as needed

const App = () => {
    return (
        <Routes>
            <Route path="/" element={<LoginView />} />
            <Route path="/mainmenu" element={<MainMenu />} />
            <Route path="/joinPoll" element={<JoinPoll/>} />
            <Route path="/createPoll" element={<CreatePollScreen/>} />
            <Route path="/viewPoll" element={<ViewPoll/>} />
            {/* ... other routes here ... */}
        </Routes>
    );
}

export default App;
