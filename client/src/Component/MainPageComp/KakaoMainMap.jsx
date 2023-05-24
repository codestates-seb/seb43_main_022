import styled from "styled-components";
import { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import { RestaurantState } from "../../state/atoms/RestaurantAtom";
// import memberState from "../../state/atoms/SignAtom";
const { kakao } = window;

const Container = styled.div`
  width: 100%;
  max-width: 1040px;
  height: 40vh;
  height: 340px;
  display: flex;
  border-radius: 10px;
  overflow: hidden;
  background-color: #dbd9cd;
`;
const InfoContainer = styled.div`
  width: 400px;
  height: 100%;
  border: 1px solid beige;
  border-left: none;
  border-radius: 0 10px 10px 0;

  padding: 5px 20px;
  font-size: var(--medium-font);
`;
const HeadSpan = styled.span`
  font-size: 18px;
  font-weight: 600;
  display: block;
  :first-of-type {
    padding-top: 10px;
  }
`;
const TextSpan = styled.span`
  padding: 5px 10px;
  font-size: var(--medium-font);
  margin: 10px 0px;
  display: block;
  border-radius: 50px;
  background-color: #fff;
`;
const FlexSpan = styled.span`
  padding: 5px 10px;
  margin: 10px 0px;
  display: block;
  border-radius: 50px;
  text-align: center;
  display: block;
  width: 100%;
  min-width: 160px;
  background-color: #fff;
`;
const TextFlex = styled.div`
  display: flex;
  justify-content: space-between;
  gap: 10px;
  width: 100%;
  height: 80px;
`;
const TextDiv = styled.div`
  height: 80px;
`;

export default function KakaoMap() {
  const [info, setInfo] = useState({});
  const markerdata = useRecoilValue(RestaurantState);
  // const memberData = useRecoilValue(memberState);
  useEffect(() => {
    mapscript();
  });

  function filterFunc(str) {
    markerdata.filter((item) =>
      item.restaurantName === str ? setInfo(item) : null,
    );
  }

  const mapscript = () => {
    let options = {
      center: new kakao.maps.LatLng(37.624915253753194, 127.15122688059974),
      level: 3,
    };

    //map
    let container = document.getElementById("map");
    const map = new kakao.maps.Map(container, options);
    map.setDraggable(false);
    map.setZoomable(false);
    markerdata.forEach((el) => {
      const marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(el.lat, el.lng),
      });
      let infowindow = new kakao.maps.InfoWindow({
        content: `<div style="padding: 10px">${el.restaurantName}</div>`, // 인포윈도우에 표시할 내용
      });

      kakao.maps.event.addListener(
        marker,
        "click",
        makeOverListener(map, marker, infowindow),
      );
      kakao.maps.event.addListener(
        marker,
        "mouseout",
        makeOutListener(infowindow),
      );
    });

    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    function makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);

        filterFunc(infowindow.cc.slice(27, infowindow.cc.length - 6));
      };
    }

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    function makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    }
  };

  return (
    <Container>
      <div id="map" style={{ width: "100%", height: "100%" }}></div>
      {info.category && (
        <InfoContainer>
          <TextDiv>
            <HeadSpan>가게 이름</HeadSpan>
            <TextSpan>{info.restaurantName}</TextSpan>
          </TextDiv>
          <TextDiv>
            <HeadSpan>음식종류</HeadSpan>
            <TextSpan>{info.category}</TextSpan>
          </TextDiv>
          <TextDiv>
            <HeadSpan>가게 위치</HeadSpan>
            <TextSpan>{info.streetAddress}</TextSpan>
          </TextDiv>
          <TextFlex>
            <TextDiv>
              <HeadSpan>영업 시간</HeadSpan>
              <FlexSpan>{info.open_time}</FlexSpan>
            </TextDiv>
            <TextDiv>
              <HeadSpan>전화번호</HeadSpan>
              <FlexSpan>{info.tel}</FlexSpan>
            </TextDiv>
          </TextFlex>
        </InfoContainer>
      )}
      {console.log("in", info)}
    </Container>
  );
}
