import { useRef, useState } from "react";
import styled from "styled-components";
import Input from "../style/StyleInput";
import Button from "../style/StyleButton";
import Slider from "./Slider";
import { Title } from "../../Pages/StoreList";
import { useRecoilState, useSetRecoilState } from "recoil";
import { keywordsAtom } from "../../state/atoms/keywordsAtom";
import { searchInputState } from "../../state/atoms/SearchStateAtom";
import { GrPowerReset } from "react-icons/gr";
// import { searchStateTag } from "../../state/atoms/SearchStateTagAtom";
export const ArticleBox = styled.article`
  width: calc(100%);
  border-radius: 30px;
  padding: 22px;
`;
/** 키워드 검색결과 페이지 상단 검색 & 인기키워드 박스 */
const StoreKeywordBox = styled(ArticleBox)`
  display: flex;
  justify-content: space-between;
  width: calc(100%);
  box-shadow: 4px 4px 30px rgba(0, 0, 0, 0.1);
`;

/** 박스 내부 검색 & 인기키워드 영역 */
const KeywordBoxLeftArea = styled.div`
  width: calc(100% - 450px);
  padding-left: 22px;
  h2:first-child {
    margin-top: 10px;
  }
  > form {
    margin-bottom: 40px;
    width: calc(100% - 40px);
  }
  .hotHeaderWrap {
    width: calc(100% - 40px);
    display: flex;
    align-items: center;
    > h2 {
      flex-basis: 170px;
    }
    > svg {
      width: 20px;
      height: 20px;
      margin-bottom: 6px;
      :hover {
        cursor: pointer;
        path {
          stroke: var(--eatsgreen);
        }
      }
    }
  }
`;
/** 박스 내부 인기키워드 묶음 */
const HotKeyword = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
`;

const StoreKeywordSearch = () => {
  const [keywords] = useRecoilState(keywordsAtom);
  const searchInputRef = useRef(null);
  const setSearchInput = useSetRecoilState(searchInputState);
  const [, setRefreshKey] = useState(0);
  const handleInputChange = (event) => {
    searchInputRef.current = event.target.value;
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    setSearchInput(searchInputRef.current);
  };
  const handleKeywordClick = (keyword) => {
    setSearchInput(keyword);
  };

  const refreshKeywords = () => {
    setRefreshKey((prevKey) => prevKey + 1);
  };
  const randomKeywords = [...keywords]
    .sort(() => Math.random() - 0.5)
    .slice(0, 12);

  return (
    <>
      <StoreKeywordBox>
        <KeywordBoxLeftArea>
          <Title>원하는 키워드가 있나요?</Title>
          <form onSubmit={handleFormSubmit}>
            <Input
              type="text"
              placeholder="원하는 키워드가 있나요?(ex.한식, 중식, ...)"
              onChange={handleInputChange}
              inputType="default"
              width="100%"
              border="1px solid var(--black-100);"
            />
          </form>
          <div className="hotHeaderWrap">
            <Title>인기 키워드로 찾기</Title>
            <GrPowerReset onClick={refreshKeywords} />
          </div>

          <HotKeyword>
            {randomKeywords.map((keyword, index) => (
              <Button
                key={index}
                btnstyle="Btn2"
                onClick={() => handleKeywordClick(keyword)}
              >
                {keyword}
              </Button>
            ))}
          </HotKeyword>
        </KeywordBoxLeftArea>
        <Slider />
      </StoreKeywordBox>
    </>
  );
};

export default StoreKeywordSearch;
