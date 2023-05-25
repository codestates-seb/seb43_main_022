import styled from "styled-components";
import { useEffect, useState } from "react";
import { useRecoilValue } from "recoil";
import { RestaurantState } from "../../state/atoms/RestaurantAtom";
import memberState from "../../state/atoms/SignAtom";
const { kakao } = window;

const Container = styled.div`
  width: 100%;
  max-width: 1040px;
  height: 340px;
  display: flex;
  border-radius: 10px;
  overflow: hidden;
  background-color: #dbd9cd;
  > #map {
    width: 100%;
    height: 100%;
  }
  > .InfoContainer {
    width: 500px;
    height: 340px;
    border: 1px solid beige;
    border-left: none;
    border-radius: 0 10px 10px 0;
    padding: 5px 20px;
    font-size: var(--medium-font);
    background-color: #dbd9cd;
  }
  > .InfoContainer > .TextDiv {
    gap: 10px;
    width: 100%;
    height: 80px;
  }
  > .InfoContainer > .TextFlex {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 80px;
  }
  > .InfoContainer > .TextDiv > .HeadSpan {
    font-size: 18px;
    font-weight: 600;
    display: block;
    :first-of-type {
      padding-top: 10px;
    }
  }
  > .InfoContainer > .TextDiv > .TextSpan {
    padding: 6px 10px;
    font-size: var(--medium-font);
    margin: 10px 0px;
    display: block;
    border-radius: 50px;
    background-color: #fff;
    min-height: 35px;
  }

  > .InfoContainer > .TextFlex > .TextDiv > .HeadSpan {
    font-size: 18px;
    font-weight: 600;
    display: block;
    :first-of-type {
      padding-top: 10px;
    }
  }
  > .InfoContainer > .TextFlex > .TextDiv > .TextSpan {
    padding: 5px 10px;
    font-size: var(--medium-font);
    margin: 10px 0px;
    display: block;
    border-radius: 50px;
    background-color: #fff;
    min-height: 35px;
  }
  > .InfoContainer > .TextFlex > .TextDiv > .FlexSpan {
    padding: 5px 10px;
    margin: 10px 0px;
    display: block;
    font-size: var(--medium-font);
    border-radius: 50px;
    text-align: center;
    display: block;
    min-width: 150px;
    width: 100%;
    min-height: 35px;
    background-color: #fff;
  }
`;

export default function KakaoMap() {
  const [info, setInfo] = useState({
    restaurantName: "",
    category: "",
    streetAddress: "",
    open_time: "",
    tel: "",
  });
  const markerdata = useRecoilValue(RestaurantState);
  const memberData = useRecoilValue(memberState);
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
      center: new kakao.maps.LatLng(memberData.latitude, memberData.longitude),
      level: 3,
    };

    let container = document.getElementById("map");
    const map = new kakao.maps.Map(container, options);
    map.setDraggable(true);
    map.setZoomable(true);
    markerdata.forEach((el) => {
      const marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(el.latitude, el.longitude),
      });
      let infowindow = new kakao.maps.InfoWindow({
        content: `<div style="padding:10px">${el.restaurantName}</div>`,
      });

      kakao.maps.event.addListener(
        marker,
        "click",
        makeOverListener(map, marker, el.restaurantName),
      );
      kakao.maps.event.addListener(
        marker,
        "mouseout",
        makeOutListener(infowindow),
      );
    });

    function makeOverListener(map, marker, infowindow) {
      return function () {
        filterFunc(infowindow);
      };
    }

    function makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    }
  };

  return (
    <Container>
      <div id="map"></div>
      {info.category ? (
        <div className="InfoContainer">
          <div className="TextDiv">
            <span className="HeadSpan">가게 이름</span>
            <span className="TextSpan">{info.restaurantName}</span>
          </div>
          <div className="TextDiv">
            <span className="HeadSpan">음식종류</span>
            <span className="TextSpan">{info.category}</span>
          </div>
          <div className="TextDiv">
            <span className="HeadSpan">가게 위치</span>
            <span className="TextSpan">{info.streetAddress}</span>
          </div>
          <div className="TextFlex">
            <div className="TextDiv">
              <span className="HeadSpan">영업 시간</span>
              <span className="FlexSpan">{info.open_time}</span>
            </div>
            <div className="TextDiv">
              <span className="HeadSpan">전화번호</span>
              <span className="FlexSpan">{info.tel}</span>
            </div>
          </div>
        </div>
      ) : null}
    </Container>
  );
}
