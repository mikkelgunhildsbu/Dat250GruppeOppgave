/*
We're constantly improving the code you see.
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";
import { useReducer } from "react";
import "./style.css";

export const Switch = ({
                           checked,
                           size,
                           color,
                           stateProp,
                           className,
                           switchClassName,
                           overlapGroupClassName,
                           slideClassName,
                       }) => {
    const [state, dispatch] = useReducer(reducer, {
        checked: checked || false,
        size: size || "medium",
        color: color || "default",
        state: stateProp || "enabled",
    });

    return (
        <div
            className={`switch ${className}`}
            onMouseLeave={() => {
                dispatch("mouse_leave");
            }}
            onMouseEnter={() => {
                dispatch("mouse_enter");
            }}
            onClick={() => {
                dispatch("click");
            }}
        >
            <div className={`overlap-group-wrapper ${state.size} ${switchClassName}`}>
                <div className={`overlap-group ${overlapGroupClassName}`}>
                    <div className={`slide ${state.state} size-0-${state.size} checked-${state.checked} ${slideClassName}`}>
                        <div
                            className={`div state-${state.state} size-1-${state.size} checked-0-${state.checked} ${state.color}`}
                        />
                    </div>
                    <div
                        className={`knob state-0-${state.state} size-2-${state.size} checked-1-${state.checked} color-${state.color}`}
                    >
                        <div className="knob-2" />
                    </div>
                </div>
            </div>
        </div>
    );
};

function reducer(state, action) {
    if (state.checked === false && state.color === "default" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "default",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "primary" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "primary",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "error" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "error",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "warning" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "warning",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "info" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "info",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "success" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "success",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "default" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "default",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "secondary" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "secondary",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "error" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "error",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "warning" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "warning",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "info" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "info",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "success" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "success",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "default" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "default",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "primary" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "primary",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "error" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "error",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "warning" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "warning",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "info" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "info",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "success" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "success",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "default" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "default",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "secondary" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "secondary",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "error" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "error",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "warning" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "warning",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "info" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "info",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "success" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "success",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "secondary" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "secondary",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "primary" && state.size === "medium" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "primary",
                    size: "medium",
                    state: "enabled",
                };
        }
    }

    if (state.checked === false && state.color === "secondary" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: true,
                    color: "secondary",
                    size: "small",
                    state: "enabled",
                };
        }
    }

    if (state.checked === true && state.color === "primary" && state.size === "small" && state.state === "hovered") {
        switch (action) {
            case "click":
                return {
                    checked: false,
                    color: "primary",
                    size: "small",
                    state: "enabled",
                };
        }
    }

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

Switch.propTypes = {
    checked: PropTypes.bool,
    size: PropTypes.oneOf(["medium", "small"]),
    color: PropTypes.oneOf(["warning", "info", "default", "success", "secondary", "primary", "error"]),
    stateProp: PropTypes.oneOf(["hovered", "disabled", "focused", "enabled"]),
};
