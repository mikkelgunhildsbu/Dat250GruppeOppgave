import { Link } from "./index";

export default {
  title: "Components/Link",
  component: Link,
  argTypes: {
    color: {
      options: ["primary", "inherit"],
      control: { type: "select" },
    },
    underline: {
      options: ["on-hover", "always", "none"],
      control: { type: "select" },
    },
    stateProp: {
      options: ["hovered", "focused", "enabled"],
      control: { type: "select" },
    },
  },
};

export const Default = {
  args: {
    link: "Link",
    color: "primary",
    underline: "on-hover",
    stateProp: "hovered",
    className: {},
  },
};
