import ReviewItem from "./ReviewItem";
import Button from "../style/StyleButton";
import styled from "styled-components";
// import profile from "../style/img/profile.png";
import { useState, useEffect } from "react";
import { useRecoilState } from "recoil";
import { reviewDataAtom } from "../../state/atoms/reviewDataAtom";

import { api } from "../../Util/api";

const ReviewListContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ReviewList = () => {
  const [data, setData] = useRecoilState(reviewDataAtom);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get(`/restaurants/1`); //${restaurant-id}
        setData(response.data.reviews);
        console.log(response.data.reviews);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  const [allReviews, setAllReviews] = useState(false);
  const showReview = allReviews ? data : data.slice(0, 2);

  const handleMoreReviews = () => {
    setAllReviews(true);
  };
  const handleDelete = (reviewData) => {
    const deleteData = data.filter(
      (item) => item.reviewId !== reviewData.reviewId,
    );
    setData(deleteData);
  };
  return (
    <ReviewListContainer>
      {showReview.map((data) => (
        <ReviewItem key={data.reviewId} data={data} onDelete={handleDelete} />
      ))}
      <Button btnstyle="Btn" onClick={handleMoreReviews}>
        리뷰 더 불러오기
      </Button>
    </ReviewListContainer>
  );
};

export default ReviewList;
