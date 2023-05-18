import { useState, useEffect } from "react";
import BookmarkItem from "./BookmarkItem";
// import { api } from "../../Util/api";
import axios from "axios";

const BookmarkList = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://ec2-54-180-31-226.ap-northeast-2.compute.amazonaws.com:8080/members/mypage",
        );

        setData(response.data.favorite);
      } catch (error) {
        console.error("에러", error);
      }
    };
    fetchData();
  }, []);

  return (
    <div>
      {data
        ? data.map((item) => (
            <BookmarkItem
              key={item.restaurantId}
              name={item.name}
              streetAddress={item.streetAddress}
              latitude={item.latitude}
              longitude={item.longitude}
              category={item.category}
            />
          ))
        : "Loading..."}
    </div>
  );
};

export default BookmarkList;
