import styled from "styled-components";
import BookmarkList from "./BookmarkList";

const Container = styled.div`
  width: 594px;
  height: 721px;
  border: none;
  border-radius: 30px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  margin-top: 40px;
  margin-left: 12px;
  overflow: hidden;
  overflow: auto;
`;
const Tilte = styled.div`
  font-size: var(--large-font);
  font-weight: bold;
  margin-left: 57px;
  padding-top: 57px;
`;

const BookmarkContainer = () => {
  return (
    <Container>
      <Tilte>즐겨찾기 목록</Tilte>
      <BookmarkList />
    </Container>
  );
};

export default BookmarkContainer;
