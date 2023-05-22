import { selector } from "recoil";
import { searchResultsState, searchInputState } from "./SearchStateAtom"; // ensure these are defined and exported properly

export const searchStateTag = selector({
  key: "searchStateTag",
  get: ({ get }) => {
    const searchInput = get(searchInputState); // corrected line
    const results = get(searchResultsState);

    return results.filter((result) => {
      const lowerCasedSearchInput = searchInput.toLowerCase(); // corrected line
      if (result.restaurantName.toLowerCase().includes(lowerCasedSearchInput)) {
        return true;
      }
      for (let i = 0; i < result.tagRestaurants.length; i++) {
        if (
          result.tagRestaurants[i].tag.name
            .toLowerCase()
            .includes(lowerCasedSearchInput)
        ) {
          return true;
        }
      }
      return false;
    });
  },
});
