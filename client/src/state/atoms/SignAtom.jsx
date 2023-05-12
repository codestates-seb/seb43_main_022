import { atom } from "recoil";

let memberState = atom({
  key: "member",
  default: {
    email: "",
    username: "",
    password: "",
    location: "",
    la: "",
    ma: "",
    CEO: false,
    img: "",
  },
});

export default memberState;
