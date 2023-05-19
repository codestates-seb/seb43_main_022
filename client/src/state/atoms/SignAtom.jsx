import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

let memberState = atom({
  key: "member",
  default: {
    memberId: "",
    email: "",
    nickName: "",
    password: "",
    streetAddress: "",
    latitude: "",
    longitude: "",
    businessAccount: false,
    photo: "",
  },
  effects_UNSTABLE: [persistAtom],
});

export default memberState;
