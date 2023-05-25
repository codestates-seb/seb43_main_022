import styled from "styled-components";
import { api } from "../../Util/api";
import XBtn from "../style/img/x.svg";
import { Link } from "react-router-dom";
const Container = styled.div`
  border-bottom: 1px solid var(--black-350);

  width: 480px;
  display: flex;
  margin-left: 57px;
  flex-direction: column;
`;
const StoreName = styled.div`
  display: flex;
  color: black;
  justify-content: space-between;
  font-size: var(--large-font);
  margin-top: 30px;
  margin-bottom: 10px;
`;

const Content = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  min-height: 33px;
  color: black;

  > .title {
    font-size: var(--medium-font);
  }
  > .created_at {
    font-size: var(--medium-font);
    color: var(--black-350);
  }
`;

const XContainer = styled.div`
  width: 30px;
  min-height: 33px;
  position: relative;
  right: 23px;
  padding-top: 20px;
  > .Btn {
    background: none;
  }
`;
const Condiv = styled.div`
  width: 100%;
  display: flex;
  background: white;
`;

const MyReviewItem = ({ setData, setSlice, review, idx, setCount }) => {
  const deleteFunc = (key) => {
    if (window.confirm("리뷰를 삭제하시겠습니까?")) {
      return api
        .delete(`/reviews/${key}`)
        .then(() => {
          api.get("/members/mypage").then((res) => {
            setData(res.data.reviews);
            setSlice(res.data.reviews.slice(0, 6));
          });
          setCount(0);
        })
        .catch((err) => console.log("삭제", err));
    }
  };

  return (
    <Condiv>
      <Link
        to={`/review/edit/${review[idx].restaurantId}/${review[idx].reviewId}`}
      >
        <Container>
          <StoreName>{review[idx].restaurantName}</StoreName>
          <Content>
            <div className="title">{review[idx].title}</div>
            <div className="created_at">
              {review[idx].createdAt.slice(0, 10)}
            </div>
          </Content>
        </Container>
      </Link>
      <XContainer>
        <button
          className="Btn"
          onClick={() => deleteFunc(review[idx].reviewId)}
        >
          <img src={XBtn} alt=""></img>
        </button>
      </XContainer>
    </Condiv>
  );
};

export default MyReviewItem;
