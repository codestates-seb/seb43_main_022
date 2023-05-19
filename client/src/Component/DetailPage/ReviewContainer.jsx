import styled from "styled-components";
import Button from "../style/StyleButton";
import ReviewList from "./ReviewList";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { reviewDataAtom } from "../../state/atoms/reviewDataAtom";
import { useState, useEffect } from "react";

const Container = styled.div`
  margin: auto;
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
`;

const ReviewContainer = () => {
  const navigate = useNavigate();
  const onClickReview = () => {
    navigate("/review");
  };

  const data = useRecoilValue(reviewDataAtom);
  const [filteredData, setFilteredData] = useState([]);

  //최신순
  const handleLatest = () => {
    const sortedData = [...data].sort((a, b) => {
      const dateA = new Date(a.created_at);
      const dateB = new Date(b.created_at);
      return dateB - dateA;
    });
    setFilteredData(sortedData);
    console.log(sortedData);
  };

  //긍정순
  const handlePositive = () => {
    const positiveReviews = data.filter((review) => review.rating === "LIKE");
    setFilteredData(positiveReviews);
    console.log(positiveReviews);
  };

  //부정순
  const handleNegative = () => {
    const negativeReviews = data.filter((review) => review.rating === "HATE");
    setFilteredData(negativeReviews);
    console.log(negativeReviews);
  };

  useEffect(() => {
    setFilteredData(data);
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
            <Button btnstyle="SBtn" onClick={handlePositive}>
              긍정순
            </Button>
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
