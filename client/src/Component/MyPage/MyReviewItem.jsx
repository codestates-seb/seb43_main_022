import styled from "styled-components";
import { api } from "../../Util/api";
import XBtn from "../style/img/x.svg";

const Container = styled.div`
  border-bottom: 1px solid var(--black-350);

  width: 480px;
  display: flex;
  margin-left: 57px;
  flex-direction: column;
`;
const StoreName = styled.div`
  display: flex;
  justify-content: space-between;
  font-size: var(--large-font);
  margin-top: 30px;
  margin-bottom: 20px;
  > .Btn {
    background: none;
  }
`;

const Content = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  min-height: 33px;

  > .title {
    font-size: var(--medium-font);
  }
  > .created_at {
    font-size: var(--medium-font);
    color: var(--black-350);
  }
`;

const MyReviewItem = ({
  reviewId,
  restaurantName,
  title,
  setreview,
  createdAt,
}) => {
  const deleteFunc = (key) => {
    return api
      .delete(`/reviews/${key}`)
      .then(() => {
        api.get("/members/mypage").then((res) => {
          setreview(res.data.reviews);
          console.log(res);
        });
      })
      .catch((err) => console.log("삭제", err));
  };
  return (
    <Container>
      <StoreName>
        {restaurantName}
        <button className="Btn" onClick={() => deleteFunc(reviewId)}>
          <img src={XBtn} alt=""></img>
        </button>
      </StoreName>
      <Content>
        <div className="title">{title}</div>
        <div className="created_at">{createdAt}</div>
      </Content>
    </Container>
  );
};

export default MyReviewItem;
