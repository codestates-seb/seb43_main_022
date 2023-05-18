import MyReviewItem from "./MyReviewItem";
import { useEffect, useState } from "react";
import { api } from "../../Util/api";

const MyReviewList = () => {
  const [data, setData] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/members/mypage");

        setData(response.data.reviews);
        console.log(response.data.reviews);
      } catch (error) {
        console.error("마이리뷰에러", error);
      }
    };
    fetchData();
  }, []);

  return (
    <div>
      {data
        ? data.map((item) => (
            <MyReviewItem
              key={item.reviewId}
              restaurantId={item.restaurantId}
              restaurantName={item.restaurantName}
              reviewId={item.reviewId}
              title={item.title}
              createdAt={item.createdAt}
            />
          ))
        : "Loading..."}
    </div>
  );
};

export default MyReviewList;
