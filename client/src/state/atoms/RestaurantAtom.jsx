import { atom } from "recoil";

export let RestaurantState = atom({
  key: "RestaurantState",
  default: {
    title: "test하이마트",
    tags: [{ name: "#test햄버거" }, { name: "#test버거" }],
    total_views: 1234,
    totalFavorite: 123,
  },
});
