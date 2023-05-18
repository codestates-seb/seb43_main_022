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
  > .created_at {
    font-size: var(--medium-font);
    color: var(--black-350);
  }
`;

const MyReviewItem = ({ restaurantName, title, createdAt }) => {
  return (
    <Container>
      <StoreName>{restaurantName}</StoreName>
      <Content>
        <div className="title">{title}</div>
        <div className="created_at">{createdAt}</div>
      </Content>
    </Container>
  );
};

export default MyReviewItem;
