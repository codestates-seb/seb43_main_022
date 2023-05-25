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
  const [slice, setSlice] = useState([]);
  let [count, setCount] = useState(0);

  const Pagenation = (count) => {
    setCount(count);
    setSlice(data.slice(count, count + 6));
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/members/mypage");
        setData(response.data.reviews);
        setSlice(response.data.reviews.slice(0, 6));
      } catch (error) {
        console.error("마이리뷰에러", error);
      }
    };
    fetchData();
  }, []);

  return (
    <>
      <div>
        {data ? (
          slice.map((item, idx) => (
            <MyReviewItem
              key={item.reviewId}
              idx={idx}
              review={slice}
              setSlice={setSlice}
              setCount={setCount}
              setData={setData}
            />
          ))
        ) : (
          <Nonediv>등록된 리뷰가 없습니다.</Nonediv>
        )}
      </div>
      <Pagediv>
        {count > 0 ? (
          <PrevBtn onClick={() => Pagenation((count -= 6))}>이전</PrevBtn>
        ) : null}
        {count + 6 < data.length ? (
          <PrevBtn onClick={() => Pagenation((count += 6))}>다음</PrevBtn>
        ) : null}
      </Pagediv>
    </>
  );
};

export default MyReviewList;
