import React, { useState } from "react";
import PropTypes from "prop-types";
import "./style.css";

export const TextField = ({
                            label,
                            value,
                            onChange,
                            placeholder,
                            className,
                            stateProp,
                            disabled = false,
                            // other props you might need
                          }) => {
  // Local state to manage focus
  const [isFocused, setIsFocused] = useState(false);

  // Focus and blur handlers
  const handleFocus = () => {
    if (!disabled) {
      setIsFocused(true);
    }
  };

  const handleBlur = () => {
    setIsFocused(false);
  };

  // Determine style based on field state
  const fieldState = disabled ? "disabled" : isFocused ? "focused" : stateProp;

  return (
      <div className={`text-field ${className} state-${fieldState}`}>
        {label && <label className={`label state-${fieldState}`}>{label}</label>}

        <input
            type="text"
            value={value}
            onChange={onChange}
            onFocus={handleFocus}
            onBlur={handleBlur}
            placeholder={placeholder}
            disabled={disabled}
            className={`input state-${fieldState}`} // Adjust class names as necessary
        />

        {/* Visuals for underline or other styling can be placed here */}
        <div className={`underline state-${fieldState}`}></div>

        {/* Any additional elements or styling based on focus, value, etc. */}
      </div>
  );
};

TextField.propTypes = {
  label: PropTypes.string,
  value: PropTypes.string.isRequired, // value is now required
  onChange: PropTypes.func.isRequired, // onChange function is now required
  placeholder: PropTypes.string,
  className: PropTypes.string,
  stateProp: PropTypes.string,
  disabled: PropTypes.bool,
  // Add any other prop types as necessary
};
