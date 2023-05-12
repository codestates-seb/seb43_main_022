import MyInfo from "../Component/MyPage/MyInfo";
import MyReviewContainer from "../Component/MyPage/MyReviewContainer";
import styled from "styled-components";
import BookmarkContainer from "../Component/MyPage/BookmarkContainer";

const RowBox = styled.div`
  display: flex;
  flex-direction: row;
`;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const MyPage = () => {
  return (
    <Container>
      <MyInfo />
      <RowBox>
        <MyReviewContainer />
        <BookmarkContainer />
      </RowBox>
    </Container>
  );
};

export default MyPage;
