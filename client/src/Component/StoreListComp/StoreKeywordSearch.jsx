import { useState, useEffect } from "react";
import styled from "styled-components";
import Input from "../style/StyleInput";
import Button from "../style/StyleButton";
import Slider from "./Slider";
import { Title } from "../../Pages/StoreList";
import { useRecoilState, useSetRecoilState } from "recoil";
import { keywordsAtom } from "../../state/atoms/keywordsAtom";
import { GrPowerReset } from "react-icons/gr";
import { searchInputState } from "../../state/atoms/SearchStateAtom";

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
  .hotHeaderWrap {
    width: calc(100% - 40px);
    display: flex;
    align-items: center;
    > h2 {
      flex-basis: 150px;
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
const FormArea = styled.form`
  margin-bottom: 40px;
  width: calc(100% - 40px);
  display: flex;
  flex-wrap: wrap;
  > input {
    width: calc(100% - 200px);
    border-right: none;
    border-radius: 10px 0 0 10px;
    text-indent: 10px;
  }
`;
const SubmitBtn = styled.button`
  width: 100px;
  height: 41px;
  border-radius: 0 10px 10px 0;
  border: 1px solid #ccc;
  border-left: none;
  font-size: 16px;
  font-weight: 500;
  background-color: white;
  color: var(--black-450);
  position: relative;
  :nth-of-type(1) {
    border-right: none;
    border-radius: 0;
    ::after {
      content: "";
      display: inline-block;
      width: 1px;
      height: 12px;
      background-color: #ccc;
      position: absolute;
      right: 0;
      top: 14px;
    }
  }

  :hover {
    color: var(--eatsgreen);
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
  const [searchTagInput, setSearchInput] = useState("");
  const [randomKeywords, setRandomKeywords] = useState([]);
  const setSearchInputState = useSetRecoilState(searchInputState);

  useEffect(() => {
    setSearchInputState("");
  }, []);
  // 현재 페이지에서만 작동하는 searchTagInput 입력되면 2중검색태그키워드에 저장된다.
  const handleFormSubmit = (event) => {
    event.preventDefault();
    setSearchInputState(searchTagInput);
    console.log("제출된 2차검색값 :", searchTagInput);
    setSearchInput("");
  };

  // 인풋창에 입력된값이 searchTagInput에 set되도록 함
  const handleInputChange = (event) => {
    setSearchInput(event.target.value);
  };
  //인기키워드가 클릭되면 setSearchInput에 클릭된값을 넣는다(화면용), setSearchInputState(실제 검색용)값도 변경시킨다.
  const handleKeywordClick = (keyword) => {
    // setSearchInput(keyword);
    setSearchInputState(keyword);
    console.log("인기키워드 클릭시 검색으로 전달된 값 :", keyword);
  };
  // 새로고침 클릭시 인기키워드 랜덤하게 뽑아줌
  const refreshKeywords = () => {
    setRandomKeywords(
      [...keywords].sort(() => Math.random() - 0.5).slice(0, 12),
    );
  };
  // 인기키워드 랜더링시에보이고, 클릭될때만 실행
  useEffect(() => {
    refreshKeywords();
  }, []);
  const deleteKeywords = () => {
    setSearchInputState("");
  };
  return (
    <>
      <StoreKeywordBox>
        <KeywordBoxLeftArea>
          <Title>필요한 태그를 검색해주세요</Title>
          <FormArea onSubmit={handleFormSubmit}>
            <Input
              type="text"
              placeholder="원하는 키워드가 있나요?(ex.한식, 중식, ...)"
              value={searchTagInput}
              onChange={handleInputChange}
            />
            <SubmitBtn>검색하기</SubmitBtn>
            <SubmitBtn onClick={deleteKeywords}>태그삭제</SubmitBtn>
          </FormArea>
          <div className="hotHeaderWrap">
            <Title>인기 태그로 찾기</Title>
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
