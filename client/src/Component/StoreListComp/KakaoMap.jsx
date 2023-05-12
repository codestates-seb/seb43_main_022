import { useEffect, useRef } from "react";

function KakaoMap({ latitude, longitude }) {
  const mapRef = useRef(null);

  useEffect(() => {
    const container = mapRef.current;

    const options = {
      center: new window.kakao.maps.LatLng(latitude, longitude),
      level: 3,
    };

    const map = new window.kakao.maps.Map(container, options);
    const markerPosition = new window.kakao.maps.LatLng(latitude, longitude);

    const marker = new window.kakao.maps.Marker({
      position: markerPosition,
    });

    marker.setMap(map);
  }, [latitude, longitude]);

  return <div ref={mapRef} style={{ width: "100%", height: "200px" }} />;
}

export default KakaoMap;
