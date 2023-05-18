import { useState, useEffect } from "react";
import BookmarkItem from "./BookmarkItem";
import { api } from "../../Util/api";

const BookmarkList = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/members/mypage");

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
