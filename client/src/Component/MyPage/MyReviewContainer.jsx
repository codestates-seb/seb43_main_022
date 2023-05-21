import MyReviewList from "./MyReviewList";
import styled from "styled-components";

const Container = styled.div`
  width: 594px;
  min-height: 721px;
  height: auto;
  border: none;
  border-radius: 30px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  overflow: hidden;
  margin-top: 40px;
  overflow: auto;
`;
const Tilte = styled.div`
  font-size: var(--large-font);
  font-weight: bold;
  margin-left: 57px;
  padding-top: 57px;
`;

const MyReviewContainer = () => {
  return (
    <Container>
      <Tilte>작성 리뷰 목록</Tilte>
      <MyReviewList />
    </Container>
  );
};

export default MyReviewContainer;
