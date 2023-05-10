import React from "react";
import styled from "styled-components";
import Button from "../style/button";
import ImgBtn from "../style/ImgBtn";
import profile from "../style/img/profile.png";

const Container = styled.div`
  width: 1200px;
  margin-top: 30px;
  margin-bottom: 150px;
  display: flex;
  flex-direction: column;
`;

const ReviewHead = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const Left = styled.div`
  display: flex;
  justify-content: start;
  align-items: center;
`;
const Profile = styled.img`
  height: 110px;
  width: 110px;
  border: none;
  border-radius: 100px;
`;
const TitleInfo = styled.div`
  margin-left: 40px;
  > .title {
    font-size: var(--xx-large-font);
    margin-bottom: 10px;
    justify-content: start;
    align-items: center;
  }
  > .day-button {
    display: flex;
    flex-direction: row;
    justify-content: start;
    align-items: center;
    > span {
      font-size: var(--medium-font);
      color: var(--black-350);
    }
  }
`;

const ReviewContent = styled.div`
  margin-top: 20px;
  display: flex;
  flex-direction: row;
  > .username {
    font-size: var(--large-font);

    margin-left: 15px;
  }
  > .contents {
    flex-direction: column;
    width: 1050px;
    margin-left: 50px;
  }
`;
const Text = styled.section`
  font-size: var(--medium-font);
  white-space: pre-wrap; // 줄바꿈을 유지하면서 공백도 유지
  overflow-wrap: break-all;
`;
const Poto = styled.div`
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  overflow: hidden; //<PotoItem>이 컨테이너에 가득 차게 되면 가로 스크롤이 생기지 않고 숨겨진 상태로 보여짐
`;
const PotoItem = styled.img`
  width: 281px;
  height: 135px;
  flex-shrink: 0; //아이템의 크기를 고정시키기 위해 flex-shrink 속성 추가
  margin-right: 10px;
  margin-top: 50px;
`;

const ReviewItem = () => {
  return (
    <>
      <Container>
        <ReviewHead>
          <Left>
            <Profile src={profile} alt="" />
            <TitleInfo>
              <div className="title">햄버거에 왜 패티가 없는거죠?</div>
              <div className="day-button">
                <span>2023.05.23</span>
                <Button btnstyle="SBtn">수정</Button>
                <Button btnstyle="SBtn">삭제</Button>
              </div>
            </TitleInfo>
          </Left>
          <ImgBtn imgstyle={"Like"} />
        </ReviewHead>
        <ReviewContent>
          <div className="username">유저네임</div>
          <div className="contents">
            <Text>
              Lorem Ipsum is simply dummy text of the printing and typesetting
              industry. Lorem Ipsum has been the industry's standard dummy text
              ever since the 1500s, when an unknown printer took a galley of
              type and scrambled it to make a type specimen book. It has
              survived not only five centuries, but also the leap into
              electronic typesetting, remaining essentially unchanged. It was
              popularised in the 1960s with the release of Letraset sheets
              containing Lorem Ipsum passages, and more recently with desktop
              publishing software like Aldus PageMaker including versions of
              Lorem Ipsum.
            </Text>
            <Poto>
              <PotoItem />
              <PotoItem />
              <PotoItem />
              <PotoItem />
              <PotoItem />
            </Poto>
          </div>
        </ReviewContent>
      </Container>
    </>
  );
};

export default ReviewItem;
