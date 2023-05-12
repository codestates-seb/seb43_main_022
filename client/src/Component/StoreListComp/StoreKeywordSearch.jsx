import styled from "styled-components";
import Input from "../style/StyleInput";
import Button from "../style/StyleButton";
import { useRecoilState } from "recoil";
import { keywordsAtom } from "../../state/atoms/keywordsAtom";
import Slider from "./Slider";
import { Title } from "../../Pages/StoreList";

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
  width: calc(100% - 500px);
  padding-left: 22px;
  h2:first-child {
    margin-top: 10px;
  }
  > form {
    margin-bottom: 40px;
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
  return (
    <>
      <StoreKeywordBox>
        <KeywordBoxLeftArea>
          <Title>원하는 키워드가 있나요?</Title>
          <form>
            <Input
              type="text"
              placeholder="키워드를 입력해주세요"
              onChange={(e) => console.log(e.target.value)}
              inputType="default"
              width="100%"
              border="1px solid var(--black-100);"
            />
          </form>
          <Title>인기 키워드로 찾기</Title>
          <HotKeyword>
            {keywords.map((keyword, index) => (
              <Button key={index} btnstyle="Btn2">
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
