import styled from "styled-components";
import ImgBtn from "../style/ImgBtn";
import { useState, useEffect } from "react";
import { useRecoilValue } from "recoil";
import memberState from "../../state/atoms/SignAtom";
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
  // const isLogin = useRecoilValue(isLoginState);
  const member = useRecoilValue(memberState);
  const [heartIcon, setHeartIcon] = useState(false);
  const [shareIcon, setShareIcon] = useState(false);

  const [data, setData] = useState({
    restaurantName: "",
    tagRestaurants: [],
    total_views: "",
    totalFavorite: "",
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/restaurants/1");
        const { restaurantName, tagRestaurants, total_views, totalFavorite } =
          response.data.data;

        setData({
          restaurantName,
          tagRestaurants,
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
      if (!member.memberId) {
        console.log("로그인이 필요합니다");
        alert("로그인이 필요합니다.");
        return;
      }
      if (!heartIcon && member.memberId) {
        await api.post(`/favorites/restaurant/1`); //${restaurant - id}
        console.log("즐겨찾기 저장");
        setHeartIcon(true);
      } else {
        await api.delete(`/favorites/1`); //${favorites-id}
        console.log("즐겨찾기 해제");
        setHeartIcon(false);
      }
    } catch (error) {
      console.error("즐겨찾기에러:", error);
    }
  };

  const handleShareIcon = () => {
    setShareIcon(!shareIcon);
    if (navigator.clipboard) {
      navigator.clipboard.writeText(window.location.href);
      console.log("링크 복사 완료");
      alert("링크가 복사되었습니다.");
    } else {
      console.log("링크 복사 실패");
      alert("링크 복사에 실패했습니다.");
    }
  };

  return (
    <div>
      <Container>
        <StoreName>{data.restaurantName}</StoreName>
        <StoreInfo>
          <Tags>
            {data.tagRestaurants &&
              data.tagRestaurants.map((tag) => (
                <TagItem key={tag.tag.tagId}>{`#${tag.tag.name}`}</TagItem>
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
