import StoreIntro from "../Component/DetailPage/StoreIntro";
import StoreInfo from "../Component/DetailPage/StoreInfo";
import StoreHead from "../Component/DetailPage/StoreHead";
import ReviewContainer from "../Component/DetailPage/ReviewContainer";
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const DetailPage = () => {
  return (
    <>
      <Container>
        <StoreHead />
        <StoreIntro />
        <StoreInfo />
        <ReviewContainer />
      </Container>
    </>
  );
};

export default DetailPage;
