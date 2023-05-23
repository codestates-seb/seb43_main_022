import { selector } from "recoil";
import { searchResultsState, searchInputState } from "./SearchStateAtom";

export const searchStateTag = selector({
  key: "searchStateTag",
  get: ({ get }) => {
    const searchInput = get(searchInputState);
    const results = get(searchResultsState);
    const searchTerms = searchInput
      .split(",")
      .map((term) => term.trim().toLowerCase());

    return results.filter((result) => {
      const restaurantName = result.restaurantName.toLowerCase();
      const tagNames = result.tagRestaurants.map((tagRestaurant) =>
        tagRestaurant.tag.name.toLowerCase(),
      );

      return searchTerms.every(
        (term) =>
          restaurantName.includes(term) ||
          tagNames.some((tagName) => tagName.includes(term)),
      );
    });
  },
});
