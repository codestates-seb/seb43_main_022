import styled from "styled-components";
import ImgBtn from "../style/ImgBtn";
import { useState, useEffect } from "react";
import { api } from "../../Util/api";

const Container = styled.div`
  margin: auto;
  width: 1200px;
`;

const StoreName = styled.div`
  font-size: var(--xx-large-font);
`;

const StoreInfo = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const Tags = styled.div`
  font-size: var(--x-large-font);
  color: var(--black-350);
  display: flex;
  flex-direction: row;
  margin-top: 10px;
`;
const TagItem = styled(Tags)`
  margin-right: 10px;
`;

const SubInfo = styled.div`
  & > * {
    margin-right: 20px;
    /* "& > *" 는 SubInfo 컴포넌트의 모든 자식 요소를 선택하는 선택자 */
  }
  > span {
    font-size: var(--x-large-font);
    color: var(--black-200);
  }
`;

const StoreHead = () => {
  const [heartIcon, setHeartIcon] = useState(false);
  const [shareIcon, setShareIcon] = useState(false);

  const [data, setData] = useState({
    restaurantName: "",
    tag: [],
    total_views: "",
    totalFavorite: "",
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/restaurants/1");
        const { restaurantName, tag, total_views, totalFavorite } =
          response.data;

        setData({
          restaurantName,
          tag,
          total_views,
          totalFavorite,
        });
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    console.log();
    fetchData();
  }, []);

  const handleHeartIcon = async () => {
    try {
      if (heartIcon) {
        await api.delete(`/favorites/1`); //${favorites-id}
        console.log("즐겨찾기 해제");
      } else {
        await api.post(`/favorites/restaurant/1`); //${restaurant - id}
        console.log("즐겨찾기 저장");
      }
      setHeartIcon(!heartIcon);
    } catch (error) {
      console.error("Error toggling favorite:", error);
    }
  };
  const handleShareIcon = () => {
    setShareIcon(!shareIcon);
  };

  return (
    <div>
      <Container>
        <StoreName>{data.restaurantName}</StoreName>
        <StoreInfo>
          <Tags>
            {data.tag &&
              data.tag.map((tag) => (
                <TagItem key={tag.tagId}>{tag.name}</TagItem>
              ))}
          </Tags>
          <SubInfo>
            <ImgBtn imgstyle={"View"} />
            <span>{data.total_views}</span>
            <ImgBtn imgstyle={"Heart"} onClick={handleHeartIcon} />
            <span>{data.totalFavorite}</span>
            <ImgBtn imgstyle={"Share"} onClick={handleShareIcon} />
          </SubInfo>
        </StoreInfo>
      </Container>
    </div>
  );
};

export default StoreHead;
