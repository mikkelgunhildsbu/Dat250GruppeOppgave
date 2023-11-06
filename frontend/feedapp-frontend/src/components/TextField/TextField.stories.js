import { TextField } from "./index";

export default {
  title: "Components/TextField",
  component: TextField,
  argTypes: {
    variant: {
      options: ["standard"],
      control: { type: "select" },
    },
    size: {
      options: ["medium", "small"],
      control: { type: "select" },
    },
    stateProp: {
      options: ["enabled", "focused", "hovered", "error", "disabled"],
      control: { type: "select" },
    },
  },
};

export const Default = {
  args: {
    placeholder: "Placeholder",
    value: "Value",
    adornEnd: false,
    placeholder1: false,
    value1: true,
    label: "Label",
    adornStart: false,
    helper: false,
    variant: "standard",
    size: "medium",
    stateProp: "enabled",
    hasValue: true,
    className: {},
    underline: "https://c.animaapp.com/Enf9PaAO/img/underline-15.svg",
  },
};
