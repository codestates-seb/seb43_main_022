import { atom } from "recoil";
export const CategoryState = atom({
  key: "categoryState",
  default: [
    "한식",
    "중식",
    "일식",
    "양식",
    "베트남 요리",
    "인도 요리",
    "분식",
    "디저트",
    "퓨전요리",
    "채식",
    "해물",
    "고기",
  ],
});
