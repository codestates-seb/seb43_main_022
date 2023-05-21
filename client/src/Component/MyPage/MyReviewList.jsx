import MyReviewItem from "./MyReviewItem";
import { useEffect, useState } from "react";
import { api } from "../../Util/api";
const MyReviewList = () => {
  const [data, setData] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/members/mypage");
        setData(response.data.reviews.slice(0, 5));
      } catch (error) {
        console.error("마이리뷰에러", error);
      }
    };
    fetchData();
    console.log(data);
  }, []);

  return (
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
        <>"Loading..."</>
      )}
    </div>
  );
};

export default MyReviewList;
