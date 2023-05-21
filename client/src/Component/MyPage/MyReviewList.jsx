import MyReviewItem from "./MyReviewItem";
import { useEffect, useState } from "react";
import { api } from "../../Util/api";
import styled from "styled-components";

const Nonediv = styled.div`
  width: 100%;
  text-align: center;
  font-size: var(--large-font);
  padding-top: 50px;
`;
const Pagediv = styled.div`
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
`;

const PrevBtn = styled.button`
  border: none;
  background: none;
  margin: 20px 10px;
  font-size: 15px;
`;

const MyReviewList = () => {
  const [data, setData] = useState([]);

  // const Pagenation = () => {

  // }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/members/mypage");
        setData(response.data.reviews.slice(0, 6));
      } catch (error) {
        console.error("마이리뷰에러", error);
      }
    };
    fetchData();
    console.log(data);
  }, []);

  return (
    <>
      <div>
        {data ? (
          data.map((item, idx) => (
            <MyReviewItem
              key={item.reviewId}
              idx={idx}
              setreview={setData}
              review={data}
            />
          ))
        ) : (
          <Nonediv>등록된 리뷰가 없습니다.</Nonediv>
        )}
      </div>
      <Pagediv>
        <PrevBtn>이전</PrevBtn>
        <PrevBtn>다음</PrevBtn>
      </Pagediv>
    </>
  );
};

export default MyReviewList;
