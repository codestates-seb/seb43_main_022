import styled from "styled-components";
import { useState, useEffect } from "react";
import { useRecoilState } from "recoil";
import ImgBtn from "../style/ImgBtn";
import Input from "../style/StyleInput";
import Plus from "../style/img/signup.svg";
import { useInput } from "../../hooks/useInput";
import { ReviewState } from "../../state/atoms/ReviewAtom";

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

  .img-Container {
    display: flex;
    align-items: center;
  }
  .review-rating {
    display: flex;
    div {
      display: flex;
      align-items: center;
      margin-right: 10px;
    }
  }
`;

const Imgul = styled.ul`
  display: flex;
  flex-direction: row;
  margin: 20px;
  li {
    position: relative;
    width: 300px;
    height: 200px;
    list-style: none;
    margin-right: 30px;
    img {
      width: 100%;
      height: 100%;
      border-radius: 10px;
    }
    div {
      position: absolute;
      right: 10px;
      top: 10px;
      button {
        font-weight: bold;
        font-size: var(--small-font);
        cursor: pointer;
        background-color: var(--black-070);
        color: var(--eatsgreen);
        padding: 10px 14px;
        border-radius: 50%;
        opacity: 0.9;
      }
    }
  }
`;

const Imgadd = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: var(--black-070);
  label {
    cursor: pointer;
    width: 50%;
    height: 50%;
    background-image: url(${Plus});
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 50%;
    input {
      display: none;
    }
  }
`;

const ReviewInfo = () => {
  const [{ title, content }, onInputChange] = useInput({
    title: "",
    content: "",
  });
  const [rating, setRating] = useState("");
  const [showImages, setShowImages] = useState([]);
  const [reviewData, setReviewData] = useRecoilState(ReviewState);
  //rating 변경 함수
  const handleRating = (choice) => {
    setRating(choice);
    console.log(rating);
  };
  // 이미지 추가 기능
  const handleAddImages = (e) => {
    const imgLists = e.target.files;
    let imgUrlLists = [...showImages];
    for (let i = 0; i < imgLists.length; i++) {
      const currentImgUrl = URL.createObjectURL(imgLists[i]);
      imgUrlLists.push({ photoUrl: currentImgUrl });
    }
    // 3개넘게 선택 시 3개만 잘라서 보여주기
    if (imgUrlLists.length > 3) {
      imgUrlLists = imgUrlLists.slice(0, 3);
    }
    setShowImages(imgUrlLists);
    console.log(showImages);
  };
  // 이미지 삭제 기능
  const handleDeleteImage = (id) => {
    const updatedImages = showImages.filter((_, index) => index !== id);
    setShowImages(updatedImages);
    console.log(updatedImages.length);
  };

  useEffect(() => {
    const photos = showImages.map((image) => ({ photoUrl: image }));
    setReviewData({
      title,
      content,
      photo: photos,
      rating,
    });
    console.log(reviewData);
  }, [title, content, showImages, rating]);
  return (
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
      <label htmlFor="img-input" className="review-font bold margin">
        리뷰 사진
      </label>
      <p>리뷰 사진은 3개만 등록이 가능합니다.</p>
      <div className="img-Container">
        {/* // 저장해둔 이미지들을 순회하면서 화면에 이미지 출력 */}
        <Imgul>
          {showImages.map((image, id) => (
            <li className="review-img" key={id}>
              <img src={image.photoUrl} alt={`${image.photoUrl}-${id}`} />
              <div>
                <button onClick={() => handleDeleteImage(id)}>X</button>
              </div>
            </li>
          ))}
        </Imgul>
        {showImages.length >= 3 ? null : (
          <Imgadd className="img-add margin">
            <label htmlFor="img-input">
              <input
                type="file"
                id="img-input"
                multiple
                accept="image/*"
                onChange={handleAddImages}
              />
            </label>
          </Imgadd>
        )}
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
  );
};

export default ReviewInfo;
