import { Title } from "../../Pages/StoreList";
import { GrMap } from "react-icons/gr";
import styled from "styled-components";
import KakaoMap from "./KakaoMap";
import { useState } from "react";
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
  overflow: hidden;
`;
const StoreMap = () => {
  const [currentAddress, setCurrentAddress] = useState(null);

  const handleAddressUpdate = (newAddress) => {
    setCurrentAddress(newAddress);
  };
  return (
    <MapWrap>
      <Title>내 위치 주변 맛집</Title>
      <MyPosition>
        <GrMap className="mapIcon" />
        {currentAddress}
      </MyPosition>
      <MapArea>
        <MapFrame>
          <KakaoMap onAddressUpdate={handleAddressUpdate} />
        </MapFrame>
      </MapArea>
    </MapWrap>
  );
};

export default StoreMap;
