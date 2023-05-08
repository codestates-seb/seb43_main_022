import styled from "styled-components";
import { useInput } from "../hooks/useInput";
import Input from "../Component/style/Input";
import Button from "../Component/style/button";
import ImgBtn from "../Component/style/ImgBtn";

const BasicContainer = styled.div`
  width: 100%;
  height: 100vh;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

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
      font-size: 28px;
      font-weight: 600;
    }
  }
  .res-sosial {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: -40px;
    .imgBtn {
      display: flex;
      div {
        margin: 0px 15px 0px 0px;
        font-size: 22px;
      }
    }
  }
`;
const ReviewContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  .review-font {
    font-size: 20px;
    margin-right: 10px;
  }
  .margin {
    margin: 20px 0px 10px 0px;
  }
  .bold {
    font-weight: 600;
  }
  .review-recommend {
    display: flex;
    div {
      display: flex;
      align-items: center;
      margin-right: 10px;
    }
  }
  .review-img {
    width: 100px;
  }
`;
const ButtonContainer = styled.div`
  width: 100%;
  display: flex;
  justify-content: right;
  align-items: center;
  Button {
    margin: 0px 0px 0px 10px;
  }
`;
/* 컨테이너 구성 트리 구조 
  전체 컨테이너
    > 가게 정보 컨테이너
      # 가게 이름
      # 태그 
      # 조회수 + 즐겨찾기 수

    > 리뷰 정보 컨테이너
      # 리뷰 제목
        ＊ text
        ＊ input
      # 리뷰 내용
        ＊ text
        ＊ input
      # 리뷰 사진
        ＊ input 창
      # 가게 만족도
        ＊ 맛있어요
          $ text
          $ img
        ＊ 별로에요
          $ text
          $ img
    > 버튼 컨테이너
      # 리뷰 남기기
      # 취소
*/

const Review = () => {
  const [{ title, detail }, onInputChange] = useInput({
    title: "",
    detail: "",
  });
  return (
    <BasicContainer className="Basic-Container">
      <RestaurantContainer className="restaurant-Container">
        <div className="res-info">
          <div className="res-title">title</div>
          <Button btnstyle="Btn2" className="res-font">
            양식
          </Button>
        </div>
        <div className="res-sosial">
          <div className="imgBtn">
            <ImgBtn imgstyle="View" />
            <div className="res-font">1.2k</div>
          </div>
          <div className="imgBtn">
            <ImgBtn imgstyle="Heart" />
            <div>1234</div>
          </div>
          <div className="imgBtn">
            <ImgBtn imgstyle="Share" />
          </div>
        </div>
      </RestaurantContainer>
      <ReviewContainer className="Review-Container">
        <div className="review-title">
          <div className="review-font bold margin">리뷰 한줄평 / 제목</div>
          <Input
            placeholder="제목을 입력해주세요."
            id="title"
            name="title"
            value={title}
            onChange={onInputChange}
            width="100%"
          />
        </div>
        <div className="review-detail">
          <div className="review-font bold margin">리뷰 내용</div>
          <Input
            placeholder="내용을 입력해주세요."
            id="detail"
            name="detail"
            value={detail}
            onChange={onInputChange}
            width="100%"
            height="10vw"
          />
        </div>

        <div className="margin">
          <img
            className="review-img"
            src="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg"
            alt="사진이 비어있을때의 모습"
          />

          <input
            type="image"
            className="review-img"
            src="https://i0.wp.com/adventure.co.kr/wp-content/uploads/2020/09/no-image.jpg"
            alt="제출버튼"
          />
          {/* <label for="file" class="upload-btn">
            <input id="file" type="file" accept="image/*" />
          </label> */}
        </div>

        <div className="review-font bold margin">가게 만족도</div>
        <div className="review-recommend">
          <div className="review-good">
            <div className="review-font">맛있어요</div>
            <ImgBtn imgstyle="Like" />
          </div>
          <div className="review-bad">
            <div className="review-font">별로에요</div>
            <ImgBtn imgstyle="Hate" />
          </div>
        </div>
      </ReviewContainer>
      <ButtonContainer className="Button-Container">
        <Button btnstyle="Btn">리뷰 남기기</Button>
        <Button btnstyle="Btn">취 소</Button>
      </ButtonContainer>
    </BasicContainer>
  );
};

export default Review;
