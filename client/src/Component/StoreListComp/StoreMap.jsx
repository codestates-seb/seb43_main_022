import { Title } from "../../Pages/StoreList";
import { GrMap } from "react-icons/gr";
import styled from "styled-components";
import KakaoMap from "./KakaoMap";
const MapWrap = styled.div`
  width: calc(100% - 830px);
`;
const MyPosition = styled.p`
  display: flex;
  vertical-align: middle;
  justify-content: flex-end;
  font-size: var(--medium-font);
  color: var(--black-700);
  margin-bottom: 40px;
  .mapIcon {
    margin-right: 4px;
    font-size: var(--medium-font);
    path {
      stroke: var(--black-700);
    }
  }
`;
/** 지도 들어갈 곳 css*/
const MapArea = styled.div`
  width: 100%;
`;
const MapFrame = styled(MapArea)`
  height: 300px;
  background-color: #ebebeb;
  border-radius: 30px;
`;
const StoreMap = () => {
  return (
    <MapWrap>
      <Title>내 위치 주변 맛집</Title>
      <MyPosition>
        <GrMap className="mapIcon" />
        현재 위치는 {123}입니다
      </MyPosition>
      <MapArea>
        <MapFrame>
          <KakaoMap />
        </MapFrame>
      </MapArea>
    </MapWrap>
  );
};

export default StoreMap;
