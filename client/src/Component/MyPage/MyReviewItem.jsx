import React from "react";
import styled from "styled-components";

const Container = styled.div`
  border-bottom: 1px solid var(--black-350);
  width: 480px;
  display: flex;
  margin-left: 57px;
  flex-direction: column;
`;
const StoreName = styled.div`
  font-size: var(--large-font);
  margin-top: 30px;
  margin-bottom: 10px;
`;

const Content = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  > .title {
    font-size: var(--medium-font);
  }
  > .day {
    font-size: var(--medium-font);
    color: var(--black-350);
  }
`;

const MyReviewItem = () => {
  return (
    <Container>
      <StoreName>김밥천국</StoreName>
      <Content>
        <div className="title">김밥 맛있게 먹었습니다.</div>
        <div className="day">2023.05.08</div>
      </Content>
    </Container>
  );
};

export default MyReviewItem;
