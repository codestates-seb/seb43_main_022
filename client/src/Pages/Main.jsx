import styled from "styled-components";
import { useRecoilValue } from "recoil";
import Hotlist from "../Component/MainPageComp/Hotlist";
import Categorylist from "../Component/MainPageComp/Categorylist";
import KakaoMap from "../Component/MainPageComp/KakaoMainMap";
import Loading from "../Component/Loading";
import { IsLoadingState } from "../state/atoms/IsLoadingAtom";

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
  const isLoading = useRecoilValue(IsLoadingState);
  return (
    <>
      {isLoading ? (
        <Loading />
      ) : (
        <BasicContainer className="Basic-Container">
          <KakaoMap />
          <Hotlist />
          <Categorylist />
        </BasicContainer>
      )}
    </>
  );
};

export default Main;
