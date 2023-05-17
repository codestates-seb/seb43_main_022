import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import Button from "../Component/style/StyleButton";
import ResInfo from "../Component/ReviewPageComp/ResInfo";
import ReviewInfo from "../Component/ReviewPageComp/ReviewInfo";
import { ReviewState } from "../state/atoms/ReviewAtom";
import { api } from "../Util/api";

const BasicContainer = styled.div`
  width: 100%;
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 70px;
`;
const ButtonContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: right;
  align-items: center;
  Button {
    margin: 0px 0px 0px 10px;
  }
`;

const Review = ({ restaurant_id }) => {
  const history = useNavigate();
  // 리뷰 남기기 버튼
  const handleSubmit = () => {
    api
      .post(`/restaurants/${restaurant_id}/review`, ReviewState)
      .then(() => {
        console.log("잘보냄");
      })
      .catch((err) => {
        console.log(`${err} 에러가 발생함`);
      });
  };
  // 취소 버튼
  const handleCancel = () => {
    history(-1);
  };
  return (
    <BasicContainer className="Basic-Container">
      <ResInfo />
      <ReviewInfo />
      <ButtonContainer className="Button-Container">
        <Button btnstyle="Btn" onClick={handleSubmit}>
          리뷰 남기기
        </Button>
        <Button btnstyle="Btn" onClick={handleCancel}>
          취 소
        </Button>
      </ButtonContainer>
    </BasicContainer>
  );
};

export default Review;
