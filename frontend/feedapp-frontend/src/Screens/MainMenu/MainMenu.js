import logo from '../../logo.svg';
import { Button } from "../../components/Button";
import './MainMenu.css';


function MainMenu() {
  return (
      <div className="main-menu">
        <Button
            className="button-instance"
            color="primary"
            label="JOIN POLL"
            size="large"
            stateProp="enabled"
            variant="contained"
        />
        <Button
            className="button-instance"
            color="primary"
            label="CREATE POLL"
            size="large"
            stateProp="enabled"
            variant="contained"
        />
        <Button
            className="button-instance"
            color="primary"
            label="VIEW POLL"
            size="large"
            stateProp="enabled"
            variant="contained"
        />
      </div>
  );
}

export default MainMenu;
