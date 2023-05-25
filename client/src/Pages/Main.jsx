import styled from "styled-components";
import { useEffect } from "react";
import { useRecoilState } from "recoil";
import KakaoMap from "../Component/MainPageComp/KakaoMainMap";
import Hotlist from "../Component/MainPageComp/Hotlist";
import Categorylist from "../Component/MainPageComp/Categorylist";
import { RestaurantState } from "../state/atoms/RestaurantAtom";
import { api } from "../Util/api";

const BasicContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 70px 0px;
  * {
    background: none;
    font-size: var(--large-font);
    li {
      list-style: none;
    }
  }
`;

const Main = () => {
  const [, setHotListData] = useRecoilState(RestaurantState);
  const local = "강남" || "서초";

  useEffect(() => {
    const fetchHotlist = async () => {
      try {
        const res = await api.get(
          `/restaurants/search?keyword=${local}&page=1&size=15`,
        );
        setHotListData(res.data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchHotlist();
  }, []);

  return (
    <BasicContainer className="Basic-Container">
      <KakaoMap />
      <Hotlist />
      <Categorylist />
    </BasicContainer>
  );
};

export default Main;
