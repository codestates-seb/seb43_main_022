import ReviewItem from "./ReviewItem";
import Button from "../style/StyleButton";
import styled from "styled-components";
import { useState, useEffect } from "react";
import { useRecoilState } from "recoil";
import { reviewDataAtom } from "../../state/atoms/reviewDataAtom";
import { api } from "../../Util/api";
import { useParams } from "react-router-dom";
const ReviewListContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const NoReviewMessage = styled.div`
  margin-top: 100px;
  color: gray;
  font-size: 20px;
`;

const ReviewList = ({ data }) => {
  const [displayData, setDisplayData] = useState(data);
  const [, setReviewData] = useRecoilState(reviewDataAtom);
  const [allReviews, setAllReviews] = useState(false);
  const [showButton, setShowButton] = useState(true);

  const { res_id } = useParams();
  useEffect(() => {
    setDisplayData(data);
  }, [data]);

  useEffect(() => {
    if (allReviews || displayData.length <= 5) {
      setShowButton(false);
    } else {
      setShowButton(true);
    }
  }, [allReviews, displayData]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get(`/restaurants/${res_id}/detail`);
        setReviewData(response.data.reviews);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  const showReview = allReviews ? displayData : displayData.slice(0, 5);

  const handleMoreReviews = () => {
    setAllReviews(true);
  };
  const handleDelete = (reviewData) => {
    const deleteData = displayData.filter(
      (item) => item.reviewId !== reviewData.reviewId,
    );
    setReviewData(deleteData);
  };
  return (
    <ReviewListContainer>
      {showReview.length === 0 && (
        <NoReviewMessage>"리뷰가 없습니다!"</NoReviewMessage>
      )}
      {showReview.map((data) => (
        <ReviewItem key={data.reviewId} data={data} onDelete={handleDelete} />
      ))}
      {showButton && (
        <Button btnstyle="Btn" onClick={handleMoreReviews}>
          리뷰 더 불러오기
        </Button>
      )}
    </ReviewListContainer>
  );
};

export default ReviewList;
