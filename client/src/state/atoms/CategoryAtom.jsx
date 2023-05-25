import { atom } from "recoil";
export const categoryState = atom({
  key: "categoryState",
  default: [
    {
      name: "한식",
    },
    {
      name: "양식",
    },
    {
      name: "일식",
    },
    {
      name: "중식",
    },
    {
      name: "베트남 요리",
    },
    {
      name: "인도 요리",
    },
    {
      name: "분식",
    },
    {
      name: "후식",
    },
    {
      name: "퓨전 요리",
    },
    {
      name: "채식",
    },
    {
      name: "해물",
    },
    {
      name: "고기",
    },
  ],
});
