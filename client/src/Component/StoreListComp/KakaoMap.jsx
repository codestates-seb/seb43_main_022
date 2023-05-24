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
    border-radius: 10px;
    background-color: beige;
    font-size: var(--small-font);
    cursor: pointer;
    :hover {
      color: var(--eatsgreen);
    }
  }
`;

function KakaoMap({ onAddressUpdate }) {
  const mapRef = useRef(null);
  // const [latitude, setLatitude] = useState(null);
  // const [longitude, setLongitude] = useState(null);
  const [restaurants, setRestaurants] = useState([]);

  let infowindow = null;

  useEffect(() => {
    const fetchData = async () => {
      try {
        //  if (navigator.geolocation) {
        //         navigator.geolocation.getCurrentPosition((position) => {
        //           setLatitude(position.coords.latitude);
        //           setLongitude(position.coords.longitude);
        const geocoder = new window.kakao.maps.services.Geocoder();
        const coord = new window.kakao.maps.LatLng(
          //  position.coords.latitude,
          //  position.coords.longitude,
          37.5665, // static latitude
          126.978, // static longitude
        );
        geocoder.coord2RegionCode(
          coord.getLng(),
          coord.getLat(),
          (result, status) => {
            if (status === window.kakao.maps.services.Status.OK) {
              onAddressUpdate(result[0].address_name);
            }
          },
        );
        //   });
        // } else {
        //   alert("Your browser does not support geolocation.");
        // }
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [onAddressUpdate]);

  useEffect(() => {
    // if (latitude && longitude) {
    const container = mapRef.current;
    const options = {
      //  center: new window.kakao.maps.LatLng(latitude, longitude),
      center: new window.kakao.maps.LatLng(37.5665, 126.978), // static latitude and longitude
      level: 5,
    };

    const map = new window.kakao.maps.Map(container, options);
    //  const markerPosition = new window.kakao.maps.LatLng(latitude, longitude);
    // const markerPosition = new window.kakao.maps.LatLng(37.5665, 126.978);
    // const marker = new window.kakao.maps.Marker({
    //   position: markerPosition,
    //   map: map,
    //   title: "현위치",
    // });
    // const infoWindow = new window.kakao.maps.InfoWindow({
    //   content: "<div style='padding:5px;'>  현위치  </div>",
    // });
    // infoWindow.open(map, marker);
    // Restaurant search
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

            // Register click event for each marker.
            window.kakao.maps.event.addListener(
              placeMarker,
              "click",
              function () {
                // Close the currently opened info window if it exists.
                if (infowindow) {
                  infowindow.close();
                }

                // When marker is clicked, the place name is displayed in the infowindow.
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
        location: new window.kakao.maps.LatLng(37.5665, 126.978), // static latitude and longitude
      },
    );
  }, [onAddressUpdate]);

  //       {
  //         location: new window.kakao.maps.LatLng(latitude, longitude),
  //       },
  //     );
  //   }
  // }, [latitude, longitude, onAddressUpdate]);
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

    // Close the currently opened info window if it exists.
    if (infowindow) {
      infowindow.close();
    }

    // When restaurant name is clicked, the place name is displayed in the infowindow.
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
        {restaurants.map((restaurant, index) => (
          <div
            key={index}
            onClick={() => handleRestaurantClick(restaurant)}
            onKeyPress={() => handleRestaurantClick(restaurant)}
            tabIndex={0}
            role="button"
          >
            {/* tabIndex={0} // 키보드 포커스를 위해 tabIndex를 추가합니다. */}
            {/* role="button" // 스크린 리더가 이 div가 버튼처럼 동작한다는 것을 이해하게 합니다. */}
            {index + 1}. {restaurant.place_name}
          </div>
        ))}
      </RestaurantList>
    </>
  );
}

export default KakaoMap;
