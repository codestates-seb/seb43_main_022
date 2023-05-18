import styled from "styled-components";

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
  ul {
    width: 100%;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    margin-bottom: 50px;
    li {
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
  return (
    <HotlistContainer className="Hotlist-Container">
      <div className="hotlist-title">내 지역 인기 맛집</div>
      <div className="hotlist-subtitle">가장 많은 좋아요를 받은 곳이에요</div>
      <ul className="hotlist">
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
        <li className="hotlist-item">
          <img
            className="hotitem-img"
            src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
            alt="자몽 그림"
          />
          <div className="hotitem-info">
            <div className="hotitem-grade ">평점 3.9</div>
            <div className="hotitem-title">스테이크립하우스</div>
            <div className="hotitem-localtag"># 청담동</div>
          </div>
        </li>
      </ul>
      <button className="hotlist-button">맛집 더보기</button>
    </HotlistContainer>
  );
};

export default Hotlist;
