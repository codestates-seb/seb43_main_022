import styled from "styled-components";
import Hotlist from "../Component/MainPageComp/Hotlist";
import Categorylist from "../Component/MainPageComp/Categorylist";
import KakaoMap from "../Component/MainPageComp/KakaoMainMap";

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
  return (
    <BasicContainer className="Basic-Container">
      <KakaoMap />
      <Hotlist />
      <Categorylist />
    </BasicContainer>
  );
};

export default Main;
