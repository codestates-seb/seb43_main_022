import { atom } from "recoil";
//검색 결과 상태를 설정_가게업체들
export const searchResultsState = atom({
  key: "searchResultsState",
  default: [],
});
//검색백업데이터
export const searchDefaultState = atom({
  key: "searchDefaultState",
  default: [],
});
//헤더 검색 키워드 상태를 설정_헤더에 검색키워드
export const searchKeywordState = atom({
  key: "searchKeywordState",
  default: "",
});

//태그 검색 키워드
export const searchInputState = atom({
  key: "searchInputState",
  default: "",
});
