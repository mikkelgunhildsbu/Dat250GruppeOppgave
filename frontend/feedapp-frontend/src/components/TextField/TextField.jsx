/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";
import { useReducer } from "react";
import "./style.css";

export const TextField = ({
  placeholder = "Placeholder",
  value = "Value",
  adornEnd = false,
  placeholder1 = false,
  value1 = true,
  label = "Label",
  adornStart = false,
  helper = false,
  variant,
  size,
  stateProp,
  hasValue,
  className,
  underline = "https://c.animaapp.com/Enf9PaAO/img/underline-15.svg",
}) => {
  const [state, dispatch] = useReducer(reducer, {
    variant: variant || "standard",
    size: size || "medium",
    state: stateProp || "error",
    hasValue: hasValue || false,
  });

  return (
    <div
      className={`text-field ${className}`}
      onMouseLeave={() => {
        dispatch("mouse_leave");
      }}
      onMouseEnter={() => {
        dispatch("mouse_enter");
      }}
    >
      <div className={`input ${state.size}`}>
        <div className={`temp-label state-0-${state.state} has-value-${state.hasValue}`}>
          {!state.hasValue && <>Label</>}

          {state.hasValue && <>{label}</>}
        </div>
        <div className={`label has-value-0-${state.hasValue} state-1-${state.state}`}>
          {!state.hasValue && <>{label}</>}

          {state.hasValue && (
            <>
              <>{value1 && <div className="value">{value}</div>}</>
            </>
          )}
        </div>
        <img
          className={`img state-3-${state.state}`}
          alt="Underline"
          src={
            state.state === "disabled"
              ? "https://c.animaapp.com/Enf9PaAO/img/underline-25.svg"
              : state.state === "focused"
              ? "https://c.animaapp.com/Enf9PaAO/img/underline-24.svg"
              : state.state === "enabled"
              ? underline
              : state.state === "hovered"
              ? "https://c.animaapp.com/Enf9PaAO/img/underline-11.svg"
              : "https://c.animaapp.com/Enf9PaAO/img/underline-27.svg"
          }
        />
      </div>
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

TextField.propTypes = {
  placeholder: PropTypes.string,
  value: PropTypes.string,
  adornEnd: PropTypes.bool,
  placeholder1: PropTypes.bool,
  value1: PropTypes.bool,
  label: PropTypes.string,
  adornStart: PropTypes.bool,
  helper: PropTypes.bool,
  variant: PropTypes.oneOf(["standard"]),
  size: PropTypes.oneOf(["medium", "small"]),
  stateProp: PropTypes.oneOf(["enabled", "focused", "hovered", "error", "disabled"]),
  hasValue: PropTypes.bool,
  underline: PropTypes.string,
};
