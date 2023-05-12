import { atom } from "recoil";

let isLoginState = atom({
  key: "isLogin",
  default: "false",
});
export default isLoginState;
