import { Button } from "./index";

export default {
  title: "Components/Button",
  component: Button,
  argTypes: {
    size: {
      options: ["large", "medium", "small"],
      control: { type: "select" },
    },
    color: {
      options: ["warning", "inherit", "info", "success", "secondary", "primary", "inherit-white", "error"],
      control: { type: "select" },
    },
    stateProp: {
      options: ["enabled", "focused", "hovered", "loading", "disabled"],
      control: { type: "select" },
    },
    variant: {
      options: ["text", "outlined", "contained"],
      control: { type: "select" },
    },
  },
};

export const Default = {
  args: {
    loadingEnd: false,
    loadingStart: true,
    label: "Label",
    startIcon: false,
    endIcon: false,
    size: "large",
    color: "warning",
    stateProp: "enabled",
    variant: "text",
    className: {},
  },
};
