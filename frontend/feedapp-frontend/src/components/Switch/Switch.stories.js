import { Switch } from ".";

export default {
    title: "Components/Switch",
    component: Switch,
    argTypes: {
        size: {
            options: ["medium", "small"],
            control: { type: "select" },
        },
        color: {
            options: ["warning", "info", "default", "success", "secondary", "primary", "error"],
            control: { type: "select" },
        },
        stateProp: {
            options: ["hovered", "disabled", "focused", "enabled"],
            control: { type: "select" },
        },
    },
};

export const Default = {
    args: {
        checked: true,
        size: "medium",
        color: "warning",
        stateProp: "hovered",
        className: {},
        switchClassName: {},
        overlapGroupClassName: {},
        slideClassName: {},
    },
};
