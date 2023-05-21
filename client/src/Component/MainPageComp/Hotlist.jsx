import styled from "styled-components";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
// import { useRecoilValue } from "recoil";
// import memberState from "../../state/atoms/SignAtom";
import { api } from "../../Util/api";

const HotlistContainer = styled.div`
  width: 100%;
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 100px;
  .hotlist-title {
    font-size: var(--xx-large-font);
    font-weight: bold;
    margin: 10px;
  }
  .hotlist-subtitle {
    font-weight: bold;
    color: var(--eatsgreen);
    margin-bottom: 30px;
  }
  .hotlist-ul {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    margin-bottom: 50px;
    .hotlist-item {
      width: 250px;
      height: 160px;
      display: flex;
      align-items: center;
      margin: 5px 10px;
      border: 1px solid var(--black-200);
      border-radius: 20px;
      overflow: hidden;
      .hotitem-img {
        width: 45%;
        height: 100%;
        margin-right: 8px;
      }
      .hotitem-info {
        width: 50%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        .hotitem-grade,
        .hotitem-localtag {
          font-size: var(--medium-font);
          font-weight: bold;
        }
        .hotitem-title {
          width: 95%;
          white-space: normal;
          font-weight: bold;
          margin: 10px 0px 20px;
        }
      }
    }
  }
  .hotlist-button {
    width: 200px;
    height: 41px;
    color: var(--eatsgreen);
    border: 1px solid var(--black-200);
    border-radius: 10px;
    padding: 0px 10px;
  }
`;

const Hotlist = () => {
  const [hotList, setHotList] = useState();
  const [userid] = "강남역";
  const navi = useNavigate();

  useEffect(() => {
    const fetchHotlist = async () => {
      try {
        const res = await api.get(`/itemlist?serch=${userid}`);
        setHotList(res.data);
      } catch (err) {
        console.log(err);
      }
    };
    fetchHotlist();
  }, []);

  const MoreHotList = () => {
    navi(`/itemlist?serch=${userid}`);
  };
  return (
    <HotlistContainer className="Hotlist-Container">
      <div className="hotlist-title">내 지역 인기 맛집</div>
      <div className="hotlist-subtitle">가장 많은 좋아요를 받은 곳이에요</div>
      <ul className="hotlist-ul">
        {hotList ? (
          hotList.map((resInfo) => (
            <li className="hotlist-item" key={resInfo.restauranid}>
              <img
                className="hotitem-img"
                src={resInfo.photoUrl}
                alt={`${resInfo.restaurantName} 이미지`}
              />
              <div className="hotitem-info">
                <div className="hotitem-grade ">{resInfo.rating}</div>
                <div className="hotitem-title">{resInfo.restaurantName}</div>
                <ul className="res-tagul">
                  {resInfo.tags
                    ? resInfo.tags.map((tag, idx) => (
                        <li className="res-tagli" key={idx}>
                          #{tag.name}
                        </li>
                      ))
                    : null}
                </ul>
              </div>
            </li>
          ))
        ) : (
          <p>No hot list available</p>
        )}
      </ul>
      <button className="hotlist-button" onClick={MoreHotList}>
        맛집 더보기
      </button>
    </HotlistContainer>
  );
};

export default Hotlist;
