import React from "react";
import styled from "styled-components";
import Button from "../style/StyleButton";
import ReviewList from "./ReviewList";

const Container = styled.div`
  margin: auto;
  display: flex;
  flex-direction: column;
  width: 1200px;
`;

const Head = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-top: 30px;
`;

const Order = styled(Head)``;
const Title = styled.div`
  font-size: var(--large-font);
  font-weight: bold;
`;
const Buttons = styled.div`
  display: flex;
  flex-direction: row;
`;

const ReviewContainer = () => {
  return (
    <Container>
      <Head>
        <Order>
          <Title>방문자 리뷰</Title>
          <Buttons>
            <Button btnstyle="SBtn">최신순</Button>
            <Button btnstyle="SBtn">긍정순</Button>
            <Button btnstyle="SBtn">부정순</Button>
          </Buttons>
        </Order>
        <Button btnstyle="Btn">리뷰 남기기</Button>
      </Head>
      <ReviewList />
    </Container>
  );
};

export default ReviewContainer;
