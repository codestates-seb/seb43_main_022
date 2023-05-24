import { atom } from "recoil";
//검색 결과 상태를 설정_가게업체들
export const searchResultsState = atom({
  key: "searchResultsState",
  default: [],
});
//검색데이터_임시 디폴트용
export const searchDefaultState = atom({
  key: "searchDefaultState",
  default: [],
});
//헤더 검색 단어_헤더검색창에 검색한 단어
export const searchKeywordState = atom({
  key: "searchKeywordState",
  default: "",
});

//2중검색 태그 검색 키워드
export const searchInputState = atom({
  key: "searchInputState",
  default: "",
});
