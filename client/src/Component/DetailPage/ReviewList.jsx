// import React, { useState } from "react";
import ReviewItem from "./ReviewItem";
import Button from "../style/button";
import styled from "styled-components";
import profile from "../style/img/profile.png";
import { useState } from "react";

const ReviewListContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ReviewList = () => {
  // const [data, setData] = useState({
  //   profileImg: profile,
  //   title: "",
  //   date: "",
  //   username: "",
  //   text: ""
  //   potoImg: [],
  // });
  const [data, setData] = useState([
    {
      id: 1,
      profileImg: profile,
      title: "맛있는 음식 리뷰",
      date: "2023년 5월 12일",
      username: "사용자1",
      text: "오늘 점심에 맛있는 음식을 먹었습니다. 맛이 정말 좋았고, 서비스도 훌륭했습니다. 다음에도 꼭 다시 방문하고 싶은 곳입니다. 강력히 추천합니다!",
      potoImg: [],
    },
    {
      id: 2,
      profileImg: profile,
      title: "신나는 음식 리뷰",
      date: "2023년 5월 10일",
      username: "사용자2",
      text: "오늘 친구들과 함께 맛있는 음식을 먹었습니다. 분위기도 좋고, 음식도 다양하고 맛있었습니다. 모두가 만족한 시간이었어요. 다음에도 꼭 다시 방문하고 싶은 곳입니다!",
      potoImg: [],
    },
    {
      id: 3,
      profileImg: profile,
      title: "편안한 음식 리뷰",
      date: "2023년 5월 8일",
      username: "사용자3",
      text: "오늘 저녁에 가족과 함께 편안한 분위기에서 맛있는 음식을 먹었습니다. 음식의 퀄리티도 좋았고, 직원들의 친절한 서비스에 감동했습니다. 다시 방문하고 싶은 곳이에요!",
      potoImg: [],
    },
    {
      id: 4,
      profileImg: profile,
      title: "특별한 음식 리뷰",
      date: "2023년 5월 6일",
      username: "사용자4",
      text: "오늘은 기념일이라 특별한 음식을 먹으러 다녀왔습니다. 음식의 창의성과 맛은 물론이고, 식당의 분위기와 서비스까지 모두 훌륭했습니다. 정말 좋은 경험이었습니다!",
      potoImg: [],
    },
  ]);

  const [allReviews, setAllReviews] = useState(false);
  const showReview = allReviews ? data : data.slice(0, 2);

  const handleMoreReviews = () => {
    setAllReviews(true);
  };
  const handleDelete = (reviewData) => {
    const deleteData = data.filter((item) => item.id !== reviewData.id);
    setData(deleteData);
  };
  return (
    <ReviewListContainer>
      {showReview.map((data, index) => (
        <ReviewItem key={index} data={data} onDelete={handleDelete} />
      ))}
      <Button btnstyle="Btn" onClick={handleMoreReviews}>
        리뷰 더 불러오기
      </Button>
    </ReviewListContainer>
  );
};

export default ReviewList;
