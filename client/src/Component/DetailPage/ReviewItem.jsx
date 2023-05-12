import styled from "styled-components";
import Button from "../style/StyleButton";
import ImgBtn from "../style/ImgBtn";
// import profile from "../style/img/profile.png";

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
  /* white-space: pre-wrap; // 줄바꿈을 유지하면서 공백도 유지
  overflow-wrap: break-all; */
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

const ReviewItem = ({ data, onDelete }) => {
  const handleDelete = () => {
    onDelete(data);
  };

  return (
    <>
      <Container>
        <ReviewHead>
          <Left>
            <Profile src={data.profileImg} alt="profile" />
            <TitleInfo>
              <div className="title">{data.title}</div>
              <div className="day-button">
                <span>{data.date}</span>
                <Button btnstyle="SBtn">수정</Button>
                {/* 수정은 어떻게? */}
                <Button btnstyle="SBtn" onClick={handleDelete}>
                  삭제
                </Button>
              </div>
            </TitleInfo>
          </Left>
          <ImgBtn imgstyle={"Like"} />
          {/* 수정하기 */}
        </ReviewHead>
        <ReviewContent>
          <div className="username">{data.username}</div>
          <div className="contents">
            <Text>{data.text}</Text>
            <Poto>
              {data.potoImg.map((img, imgIndex) => (
                <PotoItem key={imgIndex} src={img} />
              ))}
            </Poto>
          </div>
        </ReviewContent>
      </Container>
    </>
  );
};

export default ReviewItem;
