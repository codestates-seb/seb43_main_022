import { useState, useEffect } from "react";
import BookmarkItem from "./BookmarkItem";
import { api } from "../../Util/api";

const BookmarkList = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("members/mypage");

        setData(response.data.favorites);
        console.log("res", response.data.favorites);
      } catch (error) {
        console.error("에러", error);
      }
    };
    fetchData();
  }, []);

  return (
    <>
      {data ? (
        data.map((item) => (
          <BookmarkItem
            key={item.favoriteId}
            name={item.name}
            streetAddress={item.streetAddress}
            latitude={item.latitude}
            longitude={item.longitude}
            category={item.category}
            restaurantId={item.restaurantId}
            favoriteId={item.favoriteId}
            setData={setData}
            data={data}
          />
        ))
      ) : (
        <>"Loading..."</>
      )}
    </>
  );
};

export default BookmarkList;
