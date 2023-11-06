/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";
import { useReducer } from "react";
import "./style.css";

export const Link = ({ link = "Link", color, onClick, underline, stateProp, className }) => {
  const [state, dispatch] = useReducer(reducer, {
    color: color || "primary",
    underline: underline || "always",
    state: stateProp || "enabled",
  });

  return (
    <div
      className={`link ${state.state} ${className}`}
      onMouseLeave={() => {
        dispatch("mouse_leave");
      }}
      onMouseEnter={() => {
        dispatch("mouse_enter");
      }}
      onClick={onClick}
    >
      <div className={`text-wrapper ${state.state} ${state.color}`}>{link}</div>
      {(state.underline === "always" || (state.state === "hovered" && state.underline === "on-hover")) && (
        <div className={`underline color-${state.color} state-${state.state}`} />
      )}
    </div>
  );
};

function reducer(state, action) {
  switch (action) {
    case "mouse_enter":
      return {
        ...state,
        state: "hovered",
      };

    case "mouse_leave":
      return {
        ...state,
        state: "enabled",
      };
  }

  return state;
}

Link.propTypes = {
  link: PropTypes.string,
  color: PropTypes.oneOf(["primary", "inherit"]),
  underline: PropTypes.oneOf(["on-hover", "always", "none"]),
  stateProp: PropTypes.oneOf(["hovered", "focused", "enabled"]),
};
