import styled from "styled-components";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { RestaurantState } from "../../state/atoms/RestaurantAtom";

const HotlistContainer = styled.div`
  width: 100%;
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 70px 0px;
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
      justify-content: flex-start;
      align-items: center;
      margin: 5px 10px;
      border: 1px solid var(--black-200);
      border-radius: 20px;
      box-shadow: 0px 1px 10px 1px var(--black-200);
      overflow: hidden;
      &:hover {
        box-shadow: 0px 1px 10px 1px var(--eatsgreen);
      }
      .hotlist-link {
        height: 100%;
        display: flex;
        justify-content: flex-start;
        align-items: center;
      }
      .hotitem-imgbox {
        width: 6em;
        height: 100%;
        border-right: 1px solid var(--black-200);
        margin-right: 8px;
        overflow: hidden;
        .hotitem-img {
          object-fits: cover;
          width: 100%;
          height: 100%;
        }
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
        .hotitem-grade {
          color: var(--black-800);
        }
        .hotitem-title {
          width: 6em;
          height: 3em;
          padding-right: 10px;
          color: var(--black-800);
          font-weight: bold;
          margin: 10px 0px 20px;
        }
        ul {
          display: flex;
          flex-direction: row;
          li {
            margin-right: 5px;
            p {
              font-size: 0.6em;
              color: var(--black-500);
            }
          }
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
    box-shadow: 0px 1px 10px 1px var(--black-200);
    &:hover {
      box-shadow: 0px 1px 15px 1px var(--eatsgreen);
    }
  }
`;

const Hotlist = () => {
  const hotListData = useRecoilValue(RestaurantState);
  const navi = useNavigate();
  const local = "강남" || "서초";

  const getRandomItems = (array, count) => {
    const shuffled = array.slice();
    let currentIndex = array.length;
    let temporaryValue, randomIndex;
    while (currentIndex !== 0) {
      randomIndex = Math.floor(Math.random() * currentIndex);
      currentIndex -= 1;
      temporaryValue = shuffled[currentIndex];
      shuffled[currentIndex] = shuffled[randomIndex];
      shuffled[randomIndex] = temporaryValue;
    }
    return shuffled.slice(0, count);
  };
  const filterData = hotListData.filter((item) =>
    item.streetAddress.includes(local),
  );
  const randomItems = getRandomItems(filterData, 8);

  const MoreHotList = () => {
    navi(`/itemlist?serch=${local}`);
  };

  return (
    <HotlistContainer className="Hotlist-Container">
      <div className="hotlist-title">내 지역 맛집 리스트</div>
      <div className="hotlist-subtitle">내 지역 맛집 리스트 목록이에요</div>
      <ul className="hotlist-ul">
        {hotListData ? (
          randomItems.map((resInfo, idx) => (
            <li className="hotlist-item" key={idx}>
              <Link
                to={`/detail/${resInfo.restaurantId} `}
                className="hotlist-link"
              >
                <div className="hotitem-imgbox">
                  <img
                    className="hotitem-img"
                    src={`${resInfo.image}`}
                    alt={`${resInfo.restaurantName} 이미지`}
                  />
                </div>
                <div className="hotitem-info">
                  <div className="hotitem-grade ">평점 {resInfo.rating}</div>
                  <div className="hotitem-title">{resInfo.restaurantName}</div>
                  <ul className="res-tagul">
                    {resInfo.tagRestaurants
                      ? resInfo.tagRestaurants
                          .map((tag, idx) => (
                            <li className="res-tagli" key={idx}>
                              <p>#{tag.tag.name}</p>
                            </li>
                          ))
                          .slice(0, 2)
                      : null}
                  </ul>
                </div>
              </Link>
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
