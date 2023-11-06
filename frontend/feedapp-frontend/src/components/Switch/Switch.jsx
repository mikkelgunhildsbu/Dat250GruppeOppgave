import PropTypes from 'prop-types';
import React, { useReducer } from 'react';
import './style.css';

// Constants for action types
const TOGGLE = 'toggle';
const MOUSE_ENTER = 'mouse_enter';
const MOUSE_LEAVE = 'mouse_leave';

const switchReducer = (state, action, onChange) => {
    switch (action.type) {
        case TOGGLE:
            const newState = { ...state, checked: !state.checked };
            // Call the onChange callback if it's provided
            if (onChange) {
                onChange(newState.checked);
            }
            return newState;
        case MOUSE_ENTER:
            return { ...state, state: 'hovered' };
        case MOUSE_LEAVE:
            return { ...state, state: 'enabled' };
        default:
            return state;
    }
};

export const Switch = ({
                           checked = false,
                            onChange,
                           size = 'medium',
                           color = 'default',
                           stateProp = 'enabled',
                           className = '',
                           switchClassName = '',
                           overlapGroupClassName = '',
                           slideClassName = ''
                       }) => {
    // Update the useReducer hook to include the `onChange` callback
    const [state, dispatch] = useReducer((state, action) => switchReducer(state, action, onChange), {
        checked,
        size,
        color,
        state: stateProp
    });

    const handleToggle = () => dispatch({ type: TOGGLE });
    const handleMouseEnter = () => dispatch({ type: MOUSE_ENTER });
    const handleMouseLeave = () => dispatch({ type: MOUSE_LEAVE });

    const { checked: isChecked, size: currentSize, color: currentColor, state: currentState } = state;

    return (
        <div
            className={`switch ${className}`}
            onMouseLeave={handleMouseLeave}
            onMouseEnter={handleMouseEnter}
            onClick={handleToggle}

        >
            <div className={`overlap-group-wrapper ${currentSize} ${switchClassName}`}>
                <div className={`overlap-group ${overlapGroupClassName}`}>
                    <div className={`slide ${currentState} size-0-${currentSize} checked-${isChecked} ${slideClassName}`}>
                        <div className={`div state-${currentState} size-1-${currentSize} checked-0-${isChecked} ${currentColor}`} />
                    </div>
                    <div className={`knob state-0-${currentState} size-2-${currentSize} checked-1-${isChecked} color-${currentColor}`}>
                        <div className="knob-2" />
                    </div>
                </div>
            </div>
        </div>
    );
};

Switch.propTypes = {
    checked: PropTypes.bool,
    onChange: PropTypes.func,
    size: PropTypes.oneOf(['medium', 'small']),
    color: PropTypes.oneOf(['warning', 'info', 'default', 'success', 'secondary', 'primary', 'error']),
    stateProp: PropTypes.oneOf(['hovered', 'disabled', 'focused', 'enabled'])
};
