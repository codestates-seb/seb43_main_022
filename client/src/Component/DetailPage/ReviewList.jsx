import ReviewItem from "./ReviewItem";
import Button from "../style/StyleButton";
import styled from "styled-components";
import profile from "../style/img/profile.png";
import { useState, useEffect } from "react";
import axios from "axios";

const ReviewListContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ReviewList = () => {
  // const [data, setData] = useState([
  //   {
  //     reviewId: 1,
  //     profileImg: profile,
  //     title: "",
  //     created_at: "",
  //     modified_at: "",
  //     memnerName: "",
  //     content: "",
  //     photoImg: [],
  //   },
  // ]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("url");
        const newData = response.data.map((item) => {
          return {
            reviewreviewId: item.reviewId,
            profileImg: profile,
            title: item.title,
            created_at: item.created_at,
            modified_at: item.modified_at,
            memberName: item.memberName,
            content: item.content,
            photoImg: item.photoImg,
          };
        });
        setData(newData);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  const [data, setData] = useState([
    {
      reviewId: 1,
      profileImg: profile,
      title: "맛있는 음식 리뷰",
      created_at: "2023년 5월 12일",
      memberName: "사용자1",
      content:
        "오늘 점심에 맛있는 음식을 먹었습니다. 맛이 정말 좋았고, 서비스도 훌륭했습니다. 다음에도 꼭 다시 방문하고 싶은 곳입니다. 강력히 추천합니다!",
      photoImg: [],
    },
    {
      reviewId: 2,
      profileImg: profile,
      title: "신나는 음식 리뷰",
      created_at: "2023년 5월 10일",
      memberName: "사용자2",
      content:
        "오늘 친구들과 함께 맛있는 음식을 먹었습니다. 분위기도 좋고, 음식도 다양하고 맛있었습니다. 모두가 만족한 시간이었어요. 다음에도 꼭 다시 방문하고 싶은 곳입니다!",
      photoImg: [],
    },
    {
      reviewId: 3,
      profileImg: profile,
      title: "편안한 음식 리뷰",
      created_at: "2023년 5월 8일",
      memberName: "사용자3",
      content:
        "오늘 저녁에 가족과 함께 편안한 분위기에서 맛있는 음식을 먹었습니다. 음식의 퀄리티도 좋았고, 직원들의 친절한 서비스에 감동했습니다. 다시 방문하고 싶은 곳이에요!",
      photoImg: [],
    },
    {
      reviewId: 4,
      profileImg: profile,
      title: "특별한 음식 리뷰",
      created_at: "2023년 5월 6일",
      memberName: "사용자4",
      content:
        "오늘은 기념일이라 특별한 음식을 먹으러 다녀왔습니다. 음식의 창의성과 맛은 물론이고, 식당의 분위기와 서비스까지 모두 훌륭했습니다. 정말 좋은 경험이었습니다!",
      photoImg: [],
    },
  ]);

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
