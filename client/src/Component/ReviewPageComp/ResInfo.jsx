import styled from "styled-components";
import { useRecoilState } from "recoil";
import ImgBtn from "../style/ImgBtn";
import { RestaurantState } from "../../state/atoms/RestaurantAtom";

const RestaurantContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 3px solid var(--black-200);
  .res-info {
    margin-bottom: 20px;
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
    margin-bottom: -20px;
    .imgBtn {
      display: flex;
      span {
        margin: 0px 15px 0px 0px;
        font-size: var(--x-large-font);
      }
    }
  }
`;

const ResInfo = () => {
  const [resinfo] = useRecoilState(RestaurantState);

  return (
    <RestaurantContainer className="restaurant-Container">
      <div className="res-info">
        <span className="res-title">{resinfo.title}</span>
        <ul className="tag-ul">
          {resinfo.tags.map((tags, tagId) => (
            <li key={tagId}>
              <span>{tags.name}</span>
            </li>
          ))}
        </ul>
      </div>
      <div className="res-sosial">
        <div className="imgBtn">
          <ImgBtn imgstyle="View" />
          <span>{resinfo.total_views.toLocaleString("ko-KR")}</span>
        </div>
        <div className="imgBtn">
          <ImgBtn imgstyle="Heart" />
          <span>{resinfo.totalFavorite.toLocaleString("ko-KR")}</span>
        </div>
        <div className="imgBtn">
          <ImgBtn imgstyle="Share" />
        </div>
      </div>
    </RestaurantContainer>
  );
};

export default ResInfo;
