import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist({
  key: "IsLogin",
  storage: sessionStorage,
});

const isLoginState = atom({
  key: "IsLogin",
  default: false,
  effects_UNSTABLE: [persistAtom],
});
export default isLoginState;
