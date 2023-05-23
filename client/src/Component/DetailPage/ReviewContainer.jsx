import styled from "styled-components";
import Button from "../style/StyleButton";
import ReviewList from "./ReviewList";
import { useNavigate, useParams } from "react-router-dom";
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
  const onClickReview = () => {
    navigate(`/review/restaurants/${res_id}`);
  };

  const data = useRecoilValue(reviewDataAtom);
  const [filteredData, setFilteredData] = useState(data);

  //최신순 정렬
  const sortByLatest = (reviews) => {
    return reviews.sort((a, b) => {
      const dateA = new Date(a.createdAt);
      const dateB = new Date(b.createdAt);
      return dateB - dateA;
    });
  };

  //최신순
  const handleLatest = () => {
    setFilteredData(sortByLatest([...data]));
  };

  //긍정순
  const handlePositive = () => {
    const positiveReviews = data.filter((review) => review.rating === "LIKE");
    setFilteredData(sortByLatest(positiveReviews));
  };

  //부정순
  const handleNegative = () => {
    const negativeReviews = data.filter((review) => review.rating === "HATE");
    setFilteredData(sortByLatest(negativeReviews));
  };

  //첫렌더링에서 최신순으로 초기 정렬
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
