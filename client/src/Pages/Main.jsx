import styled from "styled-components";
import Hotlist from "../Component/MainPageComp/Hotlist";
import Categorylist from "../Component/MainPageComp/Categorylist";
import { useRecoilValue } from "recoil";
import memberState from "../state/atoms/SignAtom";
const Main = () => {
  const member = useRecoilValue(memberState);
  {
    console.log(member.memberId);
  }
  return (
    <BasicContainer className="Basic-Container">
      <BannerContainer className="Banner-Container">
        <ul>
          <li>
            <div className="banner-title margin">
              육즙이 가득한 패티!
              <br />
              수제 햄버거 다운타우너
            </div>
            {/* <label>#햄버거</label>
            <label>#광주 맛집</label> */}
          </li>
        </ul>

        <button className="banner-button margin">더보기</button>
        <div>01 / 03</div>
      </BannerContainer>
      <Hotlist />
      <Categorylist />
    </BasicContainer>
  );
};

export default Main;
const BasicContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 70px 0px;
  * {
    background: none;
    font-size: var(--large-font);
    li {
      list-style: none;
    }
  }
`;
const BannerContainer = styled.div`
  width: 100%;
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  margin: 70px 0px 20px 0px;
  padding: 40px 80px;
  border-radius: 20px;
  opacity: 0.7;
  object-fit: cover;
  background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxlh4D6BBz-1Ts3cj9A81xQqZD6uLvw_HtTA&usqp=CAU");
  color: #fefefe;
  ul {
    margin-bottom: 20px;
    li {
      .banner-title {
        font-size: 48px;
        font-weight: 600;
        margin-bottom: 20px;
      }
      label {
        margin-right: 10px;
      }
    }
  }
  .banner-button {
    width: 7vw;
    color: var(--white);
    background: rgba(225, 225, 225, 0.7);
    border-radius: 20px;
    padding: 5px;
    margin-bottom: 20px;
  }
`;
