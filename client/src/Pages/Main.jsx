import styled from "styled-components";
/*컨테이너 구성 트리 구조
전체 컨테이너 (basic)
  > 배너 컨테이너 banner
    # 아이템
      * 소개 글
      * 태그
    # 더보기
  > 내 지역 인기 맛집 hotlist
    # 맛집
    # 좋아요 많이 받은 곳
    # 맛집 리스트 목록
      * 맛집 아이템
    # 맛집 더보기 link
  > 카테고리 category
    # 카테고리 타이틀
    # 카테고리 리스트 목록
      * 카테고리 아이템
*/

const Main = () => {
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
      <CategoryContainer className="Category-Container">
        <div className="category-title">카테고리</div>
        <div className="category-list">
          <button className="left-button" />
          <ul>
            <li>
              <div className="category-img">
                <img
                  src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
                  alt="자몽 그림"
                />
                <div className="category-text">
                  <p>양식</p>
                </div>
              </div>
            </li>
            <li>
              <div className="category-img">
                <img
                  src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
                  alt="자몽 그림"
                />
                <div className="category-text">
                  <p>양식</p>
                </div>
              </div>
            </li>
            <li>
              <div className="category-img">
                <img
                  src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
                  alt="자몽 그림"
                />
                <div className="category-text">
                  <p>양식</p>
                </div>
              </div>
            </li>
            <li>
              <div className="category-img">
                <img
                  src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
                  alt="자몽 그림"
                />
                <div className="category-text">
                  <p>양식</p>
                </div>
              </div>
            </li>
            <li>
              <div className="category-img">
                <img
                  src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
                  alt="자몽 그림"
                />
                <div className="category-text">
                  <p>양식</p>
                </div>
              </div>
            </li>
          </ul>
          <button className="right-button" />
        </div>
      </CategoryContainer>
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

const CategoryContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .category-title {
    font-size: var(--xx-large-font);
    font-weight: bold;
    margin-bottom: 30px;
  }
  .category-list {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    ul {
      display: flex;
      flex-direction: row;
      justify-content: center;
      li {
        width: 200px;
        max-width: 240px;
        height: 14vh;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 20px;
        background: red;
        margin: 10px;
        overflow: hidden;
        position: relative;
        img {
          width: 200px;
        }
        div {
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
        }
        p {
          color: #fefefe;
          font-size: 24px;
          font-weight: bold;
        }
      }
    }
    button {
      width: 28px;
      height: 45px;
      margin: 10px;
    }
    .right-button {
      display: inline-block;
      background-image: url(https://mp-seoul-image-production-s3.mangoplate.com/web/resources/2018022864551sprites_desktop.png);
      background-position: -935px -179px;
    }
    .left-button {
      display: inline-block;
      background-image: url(https://mp-seoul-image-production-s3.mangoplate.com/web/resources/2018022864551sprites_desktop.png);
      background-position: -935px -230px;
    }
  }
`;
