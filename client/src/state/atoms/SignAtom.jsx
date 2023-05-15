import { atom } from "recoil";

let memberState = atom({
  key: "member",
  default: {
    email: "",
    username: "",
    password: "",
    streetAddress: "",
    latitude: "",
    longitude: "",
    businessAccount: false,
    photo: "",
  },
});

export default memberState;
