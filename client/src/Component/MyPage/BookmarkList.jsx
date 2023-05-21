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
        data.map((item, idx) => (
          <BookmarkItem
            key={item.favoriteId}
            idx={idx}
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
