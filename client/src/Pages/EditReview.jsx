import styled from "styled-components";
import { useEffect } from "react";
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

const EditReview = () => {
  const navi = useNavigate();
  const [reviewData, setReviewData] = useRecoilState(ReviewState);
  const { review_id } = useParams();

  useEffect(() => {
    const fetchReviewData = async () => {
      try {
        const res = await api.get(`/reviews/${review_id}`);
        setReviewData(res.data);
      } catch (error) {
        alert("리뷰 데이터를 가져오는데 실패하였습니다.");
        console.error(error);
      }
    };
    fetchReviewData();
  }, []);

  // 리뷰 남기기 버튼
  const handleEdit = async () => {
    await api
      .patch(`/reviews/${review_id}`, reviewData)
      .then(() => {
        alert("리뷰를 정상적으로 수정하였습니다.");
        setReviewData({});
        navi(-1);
      })
      .catch((err) => {
        console.log(`${err} 에러가 발생함`);
      });
  };
  //리뷰 삭제 버튼
  const hendleDelete = async () => {
    if (window.confirm("리뷰를 삭제 하시겠습니까?")) {
      try {
        await api.delete(`/reviews/${review_id}`);
        setReviewData({});
        alert("리뷰가 삭제되었습니다.");
        navi(-1);
      } catch (err) {
        console.log(err);
      }
    }
  };
  // 취소 버튼
  const handleCancel = () => {
    setReviewData({});
    navi(-1);
  };

  return (
    <BasicContainer className="Basic-Container">
      <ResInfo />
      {reviewData.title ? (
        <ReviewInfo reviewData={reviewData} setReviewData={setReviewData} />
      ) : null}
      <ButtonContainer className="Button-Container">
        <Button btnstyle="Btn" onClick={hendleDelete}>
          리뷰 삭제
        </Button>
        <Button btnstyle="Btn" onClick={handleEdit}>
          리뷰 수정
        </Button>
        <Button btnstyle="Btn" onClick={handleCancel}>
          취 소
        </Button>
      </ButtonContainer>
    </BasicContainer>
  );
};

export default EditReview;
