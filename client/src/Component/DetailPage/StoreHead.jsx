import styled from "styled-components";
import ImgBtn from "../style/ImgBtn";

const Container = styled.div`
  margin: auto;
  width: 1200px;
`;

const StoreName = styled.div`
  font-size: var(--xx-large-font);
`;

const StoreInfo = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const Tags = styled.div`
  font-size: var(--x-large-font);
  color: var(--black-350);
  display: flex;
  flex-direction: row;
  margin-top: 10px;
`;
const TagItem = styled(Tags)`
  margin-right: 10px;
`;

const SubInfo = styled.div`
  & > * {
    margin-right: 20px;
    /* "& > *" 는 SubInfo 컴포넌트의 모든 자식 요소를 선택하는 선택자 */
  }
  > span {
    font-size: var(--x-large-font);
    color: var(--black-200);
  }
`;

const StoreHead = () => {
  return (
    <div>
      <Container>
        <StoreName>오픈마인드</StoreName>
        <StoreInfo>
          <Tags>
            <TagItem>#브런치</TagItem>
            <TagItem>#샌드위치</TagItem>
            <TagItem>#분위기좋은곳</TagItem>
          </Tags>
          <SubInfo>
            <ImgBtn imgstyle={"View"} />
            <span>1.2k</span>
            <ImgBtn imgstyle={"Heart"} />
            <span>302</span>
            <ImgBtn imgstyle={"Share"} />
          </SubInfo>
        </StoreInfo>
      </Container>
    </div>
  );
};

export default StoreHead;
