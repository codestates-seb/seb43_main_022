import styled from "styled-components";
import Input from "../Component/style/StyleInput";
import Button from "../Component/style/StyleButton";
import ImgBtn from "../Component/style/ImgBtn";
import { useInput } from "../hooks/useInput";
import { useState } from "react";

import { useNavigate } from "react-router-dom";
import { api } from "../Util/api";
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
const info = {
  title: "하이마트",
  tags: ["태그1", "태그2", "태그3", "태그4", "태그5"],
  view: "1.5k",
  heart: "500",
  tag: [
    {
      tagId: 1,
      name: "#햄버거",
    },
    {
      tagId: 2,
      name: "#버거",
    },
  ],
};
const Review = (restaurant_id) => {
  const [{ title, content, img }, onInputChange] = useInput({
    title: "",
    content: "",
    img: "",
  });
  const [, setResinfo] = useState({});
  const [rating, setRating] = useState("");
  const history = useNavigate();

  api
    .get(`/restaurants/${restaurant_id}`)
    .then((res) => {
      setResinfo(res.data);
    })
    .catch((err) => {
      console.log(err);
    });
  const handleSubmit = () => {
    api
      .post(`/restaurants/${restaurant_id}/review`, {
        title,
        Comment,
        img,
        rating,
      })
      .then(() => {
        console.log("잘보냄");
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const handleCancel = () => {
    history(-1);
  };
  const handleRating = (choice) => {
    setRating(choice);
  };

  return (
    <BasicContainer className="Basic-Container">
      <RestaurantContainer className="restaurant-Container">
        <div className="res-info">
          <span className="res-title">{info.title}</span>
          <ul className="tag-ul">
            {/* 나중에 변경해야함 */}
            {info.tag.map((tag, tagId) => (
              <li key={tagId}>
                <span>{tag.name}</span>
              </li>
            ))}
          </ul>
        </div>
        <div className="res-sosial">
          <div className="imgBtn">
            <ImgBtn imgstyle="View" />
            <span>{info.view}</span>
          </div>
          <div className="imgBtn">
            <ImgBtn imgstyle="Heart" />
            <span>{info.heart}</span>
          </div>
          <div className="imgBtn">
            <ImgBtn imgstyle="Share" />
          </div>
        </div>
      </RestaurantContainer>
      <ReviewContainer className="Review-Container">
        <div className="review-title">
          <label htmlFor="title" className="review-font bold margin">
            리뷰 한줄평 / 제목
          </label>
          <Input
            placeholder="제목을 입력해주세요."
            id="title"
            name="title"
            value={title}
            onChange={onInputChange}
            inputType="default"
            width="100%"
          />
        </div>
        <div className="review-content">
          <label htmlFor="content" className="review-font bold margin">
            리뷰 내용
          </label>
          <textarea
            placeholder="내용을 입력해주세요."
            id="content"
            name="content"
            value={content}
            onChange={onInputChange}
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
        <div className="review-rating">
          <div className="review-good">
            <label htmlFor="like" className="review-font">
              맛있어요
            </label>
            <ImgBtn
              name="like"
              imgstyle="Like"
              onClick={() => handleRating("like")}
            />
          </div>
          <div className="review-bad">
            <label htmlFor="hate" className="review-font">
              별로에요
            </label>
            <ImgBtn
              name="hate"
              imgstyle="Hate"
              onClick={() => handleRating("hate")}
            />
          </div>
        </div>
      </ReviewContainer>
      <ButtonContainer className="Button-Container">
        <Button btnstyle="Btn" onClick={handleSubmit}>
          리뷰 남기기
        </Button>
        <Button btnstyle="Btn" onClick={handleCancel}>
          취 소
        </Button>
      </ButtonContainer>
    </BasicContainer>
  );
};

export default Review;

const BasicContainer = styled.div`
  width: 100%;
  max-width: 1200px;
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
const ReviewContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  .review-title,
  .review-content {
    display: flex;
    flex-direction: column;
    justify-content: center;
    textarea {
      height: auto;
      min-height: 200px;
      box-sizing: border-box;
      white-space: normal;
      font-size: var(--medium-font);
      border: var(--border, 1px solid var(--black-200));
      border-radius: 10px;
      padding: 10px;
      resize: none;
      overflow: hidden;
    }
  }
  .review-font {
    font-size: var(--large-font);
    margin-right: 10px;
  }
  .margin {
    margin: 20px 0px 10px 0px;
  }
  .bold {
    font-weight: 600;
  }
  .review-rating {
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
