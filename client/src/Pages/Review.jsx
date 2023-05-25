import styled from "styled-components";
import { useNavigate, useParams } from "react-router-dom";
import { useRecoilState } from "recoil";
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
  margin: 90px 0px;
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

const Review = () => {
  const navi = useNavigate();
  const [ReviewData, setReviewData] = useRecoilState(ReviewState);
  const { res_id } = useParams();
  // 리뷰 남기기 버튼
  const handleSubmit = async () => {
    await api
      .post(`/reviews/restaurants/${res_id}`, ReviewData)
      .then(() => {
        alert("리뷰를 등록하였습니다.");
        setReviewData({});
        navi(-1);
      })
      .catch((err) => {
        alert("모든 내용을 입력하였는지 확인해주세요.");
        console.log(err);
      });
  };
  // 취소 버튼
  const handleCancel = () => {
    navi(-1);
  };
  return (
    <BasicContainer className="Basic-Container">
      <ResInfo />
      <ReviewInfo reviewData={ReviewData} setReviewData={setReviewData} />
      <ButtonContainer className="Button-Container">
        <Button btnstyle="Btn" onClick={handleSubmit}>
          리뷰 작성
        </Button>
        <Button btnstyle="Btn" onClick={handleCancel}>
          취 소
        </Button>
      </ButtonContainer>
    </BasicContainer>
  );
};

export default Review;
