import { selector } from "recoil";
import { searchResultsState, searchKeywordState } from "./SearchStateAtom";

export const filteredResultsState = selector({
  key: "filteredResultsState",
  get: ({ get }) => {
    const results = get(searchResultsState);
    const keyword = get(searchKeywordState);
    return results.filter((result) => result.includes(keyword));
  },
});
