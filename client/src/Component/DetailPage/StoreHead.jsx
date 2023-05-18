import styled from "styled-components";
import ImgBtn from "../style/ImgBtn";
import { useState, useEffect } from "react";
// import { api } from "../../Util/api";
import axios from "axios";

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

  // const [tags, setTags] = useState([]);

  // {
  //   tagId: 1,
  //   name: "#분위기좋은곳",
  // },
  // {
  //   tagId: 2,
  //   name: "#브런치",
  // },
  // {
  //   tagId: 3,
  //   name: "#샌드위치",
  // },

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://ec2-54-180-31-226.ap-northeast-2.compute.amazonaws.com:8080/restaurants/1",
        );
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

  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const response = await axios.get(
  //         "http://ec2-54-180-31-226.ap-northeast-2.compute.amazonaws.com:8080/tags",
  //       );
  //       const { tags } = response.data;

  //       setTags(tags);
  //     } catch (error) {
  //       console.error("Error fetching data:", error);
  //     }
  //   };

  //   fetchData();
  // }, []);

  const handleHeartIcon = () => {
    setHeartIcon(!heartIcon);
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
