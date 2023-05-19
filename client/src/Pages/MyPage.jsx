import MyInfo from "../Component/MyPage/MyInfo";
import MyReviewContainer from "../Component/MyPage/MyReviewContainer";
import styled from "styled-components";
import BookmarkContainer from "../Component/MyPage/BookmarkContainer";
import { useRecoilValue } from "recoil";
import isLoginAtom from "../state/atoms/IsLoginAtom";

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
  const isLogin = useRecoilValue(isLoginAtom);

  return (
    <Container>
      {isLogin && (
        <>
          <MyInfo />
          <RowBox>
            <MyReviewContainer />
            <BookmarkContainer />
          </RowBox>
        </>
      )}
    </Container>
  );
};

export default MyPage;
