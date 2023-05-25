import { atom } from "recoil";

export const searchResultsState = atom({
  key: "searchResultsState",
  default: [],
});

export const searchDefaultState = atom({
  key: "searchDefaultState",
  default: [],
});

export const searchKeywordState = atom({
  key: "searchKeywordState",
  default: "",
});

export const searchInputState = atom({
  key: "searchInputState",
  default: "",
});
