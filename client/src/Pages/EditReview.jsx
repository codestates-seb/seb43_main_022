import styled from "styled-components";
import { useEffect } from "react";
import { useRecoilState } from "recoil";
import { useNavigate, useParams } from "react-router-dom";
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

const EditReview = () => {
  const navi = useNavigate();
  const [ReviewData, setReviewData] = useRecoilState(ReviewState);
  const { review_id } = useParams();

  useEffect(() => {
    api
      .get(`/reviews/${review_id}`)
      .then((res) => {
        setReviewData(res.data);
        console.log("review 데이터 : ", res.data);
      })
      .catch((err) => {
        console.log(err);
        alert("업체 정보를 가져오는데 실패하였습니다.");
      });
  }, [setReviewData]);

  // 리뷰 남기기 버튼
  const handleEdit = async () => {
    await api
      .patch(`/reviews/${review_id}`, ReviewData)
      .then(() => {
        console.log("리뷰 수정하기");
        setReviewData({});
        navi(-1);
      })
      .catch((err) => {
        console.log(`${err} 에러가 발생함`);
      });
  };

  const hendleDelete = async () => {
    if (window.confirm("리뷰를 삭제 하시겠습니까?")) {
      try {
        await api.delete(`/reviews/${review_id}`);
        alert("업체 정보가 삭제되었습니다.");
        navi(-1);
      } catch (err) {
        console.log(err);
      }
    }
  };

  // 취소 버튼
  const handleCancel = () => {
    navi(-1);
  };
  return (
    <BasicContainer className="Basic-Container">
      <ResInfo />
      <ReviewInfo />
      <ButtonContainer className="Button-Container">
        <Button btnstyle="Btn" onClick={hendleDelete}>
          리뷰 삭제하기
        </Button>
        <Button btnstyle="Btn" onClick={handleEdit}>
          리뷰 수정하기
        </Button>
        <Button btnstyle="Btn" onClick={handleCancel}>
          취 소
        </Button>
      </ButtonContainer>
    </BasicContainer>
  );
};

export default EditReview;
