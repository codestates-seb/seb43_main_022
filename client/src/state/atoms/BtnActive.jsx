import { atom } from "recoil";

let BtnState = atom({
  key: "Btn",
  default: {
    Plus: false,
    Share: false,
    LIKE: false,
    Heart: false,
    HATE: false,
    View: false,
    Check: false,
    X: false,
  },
});

export default BtnState;
