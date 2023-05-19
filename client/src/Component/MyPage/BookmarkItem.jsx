import styled from "styled-components";
import ImgBtn from "./../style/ImgBtn";

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
  > .area {
    font-size: var(--medium-font);
  }
  > .menu {
    font-size: var(--medium-font);
  }
`;

const BookmarkItem = ({ name, streetAddress, category }) => {
  return (
    <Container>
      <StoreName>{name}</StoreName>
      <Content>
        <div className="area">{streetAddress}</div>
        <div className="menu">{category}</div>
        <ImgBtn imgstyle={"Heart"}></ImgBtn>
      </Content>
    </Container>
  );
};

export default BookmarkItem;
