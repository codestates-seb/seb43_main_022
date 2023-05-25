import { useEffect, useState, useRef } from "react";
import styled from "styled-components";

const MapContainer = styled.div`
  width: 100%;
  height: 400px;
  position: relative;
`;

const MapElement = styled.div`
  width: 100%;
  height: 100%;
  border-radius: 20px;
`;

const RestaurantList = styled.div`
  margin-top: 20px;
  > div {
    margin: 10px 0;
    border-bottom: 1px solid #ebebeb;
    font-size: var(--small-font);
    cursor: pointer;
    font-size: 16px;
    padding: 10px 4px;
    text-indent: 10px;
    :hover {
      color: var(--eatsgreen);
    }
  }
`;

function KakaoMap({ onAddressUpdate }) {
  const mapRef = useRef(null);

  const [restaurants, setRestaurants] = useState([]);

  let infowindow = null;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const geocoder = new window.kakao.maps.services.Geocoder();
        const coord = new window.kakao.maps.LatLng(37.5665, 126.978);
        geocoder.coord2RegionCode(
          coord.getLng(),
          coord.getLat(),
          (result, status) => {
            if (status === window.kakao.maps.services.Status.OK) {
              onAddressUpdate(result[0].address_name);
            }
          },
        );
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [onAddressUpdate]);

  useEffect(() => {
    const container = mapRef.current;
    const options = {
      center: new window.kakao.maps.LatLng(37.5665, 126.978),
      level: 5,
    };

    const map = new window.kakao.maps.Map(container, options);

    var places = new window.kakao.maps.services.Places(map);
    places.categorySearch(
      "FD6",
      function (data, status) {
        if (status === window.kakao.maps.services.Status.OK) {
          setRestaurants(data);
          for (let i = 0; i < data.length; i++) {
            let placePosition = new window.kakao.maps.LatLng(
              data[i].y,
              data[i].x,
            );
            let placeMarker = new window.kakao.maps.Marker({
              map: map,
              position: placePosition,
            });

            window.kakao.maps.event.addListener(
              placeMarker,
              "click",
              function () {
                if (infowindow) {
                  infowindow.close();
                }

                infowindow = new window.kakao.maps.InfoWindow({
                  content:
                    '<div style="padding:5px;font-size:12px;">' +
                    data[i].place_name +
                    "<br />" +
                    data[i].phone +
                    "<br />" +
                    data[i].road_address_name +
                    "</div>",
                  removable: true,
                });
                infowindow.open(map, placeMarker);
              },
            );
          }
        }
      },
      {
        location: new window.kakao.maps.LatLng(37.5665, 126.978),
      },
    );
  }, [onAddressUpdate]);

  const handleRestaurantClick = (restaurant) => {
    const placePosition = new window.kakao.maps.LatLng(
      restaurant.y,
      restaurant.x,
    );
    const map = new window.kakao.maps.Map(mapRef.current, {
      center: placePosition,
      level: 3,
    });

    const marker = new window.kakao.maps.Marker({
      position: placePosition,
      map: map,
    });

    if (infowindow) {
      infowindow.close();
    }

    infowindow = new window.kakao.maps.InfoWindow({
      content:
        '<div style="padding:5px;font-size:12px;">' +
        restaurant.place_name +
        "</div>",
      removable: true,
    });
    infowindow.open(map, marker);
  };

  return (
    <>
      <MapContainer>
        <MapElement ref={mapRef} />
      </MapContainer>
      <RestaurantList>
        {restaurants.slice(0, 10).map((restaurant, index) => (
          <div
            key={index}
            onClick={() => handleRestaurantClick(restaurant)}
            onKeyPress={() => handleRestaurantClick(restaurant)}
            tabIndex={0}
            role="button"
          >
            {index + 1}. {restaurant.place_name}
          </div>
        ))}
      </RestaurantList>
    </>
  );
}

export default KakaoMap;
