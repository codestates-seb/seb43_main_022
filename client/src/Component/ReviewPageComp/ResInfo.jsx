import styled from "styled-components";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import ImgBtn from "../style/ImgBtn";
import { api } from "../../Util/api";
import { Link } from "react-router-dom";
import { useRecoilValue } from "recoil";
import memberState from "../../state/atoms/SignAtom";

const RestaurantContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 3px solid var(--black-200);
  .res-info {
    margin-bottom: 10px;
    .res-title {
      margin-bottom: 10px;
      font-size: var(--xx-large-font);
      font-weight: 600;
    }
    .tag-ul {
      display: flex;
      flex-wrap: wrap;
      li {
        list-style: none;
        margin: 4px 8px 0px 0px;
        span {
          font-size: var(--large-font);
          color: var(--black-600);
        }
      }
    }
  }
  .res-sosial {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: -30px;
    .imgBtn {
      display: flex;
      span {
        margin: -4px 15px 0px 5px;
        font-size: var(--x-large-font);
      }
    }
  }
`;

const ResInfo = () => {
  const { res_id } = useParams();
  const [resInfo, setresInfo] = useState({});
  const userData = useRecoilValue(memberState);

  useEffect(() => {
    const fetchRestaurant = async () => {
      try {
        const res = await api.get(`/restaurants/${res_id}`);
        setresInfo(res.data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchRestaurant();
  }, [res_id]);

  const isFavorite = userData.favorites.some(
    (favorites) => favorites.restaurantId === parseInt(res_id),
  );

  return (
    <RestaurantContainer className="restaurant-Container">
      <div className="res-info">
        <span className="res-title">{resInfo.restaurantName}</span>
        <ul className="tag-ul">
          {resInfo.tagRestaurants?.map((taginfo) => (
            <li key={taginfo.tag.tagId}>
              <span>#{taginfo.tag.name}</span>
              <Link to={`/itemlist/search?=${taginfo.tag.name}`}></Link>
            </li>
          ))}
        </ul>
      </div>
      <div className="res-sosial">
        <div className="imgBtn">
          <ImgBtn imgstyle="View" />
          <span>{resInfo.total_views}</span>
        </div>
        <div className="imgBtn">
          <ImgBtn imgstyle={isFavorite ? "HeartActive" : "Heart"} />
          <span>{resInfo.totalFavorite}</span>
        </div>

        <div className="imgBtn">
          <ImgBtn imgstyle="Share" />
        </div>
      </div>
    </RestaurantContainer>
  );
};

export default ResInfo;
