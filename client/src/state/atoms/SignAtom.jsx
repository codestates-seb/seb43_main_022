import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist({
  key: "member",
  storage: sessionStorage,
});

const memberState = atom({
  key: "member",
  default: {
    memberId: "",
    email: "",
    nickName: "",
    password: "",
    streetAddress: "",
    latitude: "37.4978399531903",
    longitude: "127.028578846319",
    businessAccount: false,
    favorites: [],
  },
  effects_UNSTABLE: [persistAtom],
});

export default memberState;
