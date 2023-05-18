import { atom } from "recoil";

export let ReviewState = atom({
  key: "ReviewState",
  default: {
    title: "",
    content: "",
    photo: [],
    rating: "",
  },
});
