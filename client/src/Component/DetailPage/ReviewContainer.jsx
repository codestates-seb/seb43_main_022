import styled from "styled-components";
import Button from "../style/StyleButton";
import ReviewList from "./ReviewList";
import { useNavigate, useParams } from "react-router-dom";
import { useRecoilState, useRecoilValue } from "recoil";
import { reviewDataAtom } from "../../state/atoms/reviewDataAtom";
import { useState, useEffect } from "react";
import { ReviewState } from "../../state/atoms/ReviewAtom";

const Container = styled.div`
  margin: 0 0 170px auto;
  display: flex;
  flex-direction: column;
  width: 1200px;
`;

const Head = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin-top: 30px;
`;

const Order = styled(Head)``;
const Title = styled.div`
  font-size: var(--large-font);
  font-weight: bold;
`;
const Buttons = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const VerticalLine = styled.div`
  width: 1px;
  height: 20px;
  background-color: var(--black-350);
`;

const ReviewContainer = () => {
  const navigate = useNavigate();
  const { res_id } = useParams();
  const [, setReviewData] = useRecoilState(ReviewState);
  const onClickReview = () => {
    setReviewData({});
    if (sessionStorage.getItem("Authorization")) {
      navigate(`/review/restaurants/${res_id}`);
    } else {
      alert("로그인 후 이용해주세요.");
      navigate(`/login`);
    }
  };

  const data = useRecoilValue(reviewDataAtom);
  const [filteredData, setFilteredData] = useState(data);

  const sortByLatest = (reviews) => {
    return reviews.sort((a, b) => {
      const dateA = new Date(a.createdAt);
      const dateB = new Date(b.createdAt);
      return dateB - dateA;
    });
  };

  const handleLatest = () => {
    setFilteredData(sortByLatest([...data]));
  };

  const handlePositive = () => {
    const positiveReviews = data.filter((review) => review.rating === "LIKE");
    setFilteredData(sortByLatest(positiveReviews));
  };

  const handleNegative = () => {
    const negativeReviews = data.filter((review) => review.rating === "HATE");
    setFilteredData(sortByLatest(negativeReviews));
  };

  useEffect(() => {
    setFilteredData(sortByLatest([...data]));
  }, [data]);

  return (
    <Container>
      <Head>
        <Order>
          <Title>방문자 리뷰</Title>
          <Buttons>
            <Button btnstyle="SBtn" onClick={handleLatest}>
              최신순
            </Button>
            <VerticalLine />
            <Button btnstyle="SBtn" className="latest" onClick={handlePositive}>
              긍정순
            </Button>
            <VerticalLine />
            <Button btnstyle="SBtn" onClick={handleNegative}>
              부정순
            </Button>
          </Buttons>
        </Order>

        <Button btnstyle="Btn" onClick={onClickReview}>
          리뷰 남기기
        </Button>
      </Head>
      <ReviewList data={filteredData} />
    </Container>
  );
};

export default ReviewContainer;
