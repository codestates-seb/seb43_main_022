import styled from "styled-components";
import Button from "../style/StyleButton";
// import ImgBtn from "../style/ImgBtn";
import profile from "../style/img/profile.png";
import { AiTwotoneLike, AiTwotoneDislike } from "react-icons/ai";
import { useParams, useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import memberState from "../../state/atoms/SignAtom";
import { api } from "../../Util/api";

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

const ImgBtn = styled.div`
  > .likeImg {
    height: 30px;
    width: 30px;
    color: green;
  }

  > .hateImg {
    height: 30px;
    width: 30px;
    color: darkred;
  }
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
  justify-content: center;
  > .username {
    font-size: var(--large-font);
    text-align: center;
    width: 110px;
  }
  > .contents {
    flex-direction: column;
    width: 1050px;

    margin-left: 40px;
  }
`;
const Text = styled.section`
  font-size: var(--medium-font);
  /* white-space: pre-wrap; // 줄바꿈을 유지하면서 공백도 유지
  overflow-wrap: break-all; */
`;

const ButtonGroup = styled.div`
  display: flex;
  align-items: center;
`;

const VerticalLine = styled.div`
  width: 1px;
  height: 20px;
  background-color: var(--black-350);
`;
// const Photo = styled.div`
//   display: flex;
//   flex-direction: row;
//   margin-top: 20px;
//   overflow: hidden; //<PotoItem>이 컨테이너에 가득 차게 되면 가로 스크롤이 생기지 않고 숨겨진 상태로 보여짐
// `;
// const PhotoItem = styled.img`
//   width: 281px;
//   height: 135px;
//   flex-shrink: 0; //아이템의 크기를 고정시키기 위해 flex-shrink 속성 추가
//   margin-right: 10px;
//   margin-top: 50px;
// `;

const ReviewItem = ({ data, onDelete }) => {
  const { res_id } = useParams();

  const navigate = useNavigate();
  const modifyClick = () => {
    console.log(data);
    navigate(`/review/edit/${res_id}/${data.reviewId}`);
  };
  const member = useRecoilValue(memberState);

  const handleDelete = async () => {
    try {
      const response = await api.delete(`/reviews/${data.reviewId}`);
      console.log("리뷰삭제:", response.data);
      onDelete(data);
    } catch (error) {
      console.error("리뷰삭제 실패:", error);
    }
  };

  return (
    <>
      <Container>
        <ReviewHead>
          <Left>
            <Profile src={data.member.photo || profile} alt="profile" />
            <TitleInfo>
              <div className="title">{data.title}</div>
              <div className="day-button">
                <span>{data.modifiedAt || data.createdAt}</span>
                {/* data.modified_at의 값이 존재하는 경우(truthy 값) 그 값을 반환하고, data.modified_at의 값이 존재하지 않는 경우(falsy 값) data.created_at의 값을 반환 */}
                {member.memberId === data.member.memberId ? (
                  <ButtonGroup>
                    <Button btnstyle="SBtn" onClick={modifyClick}>
                      수정
                    </Button>
                    <VerticalLine />
                    <Button btnstyle="SBtn" onClick={handleDelete}>
                      삭제
                    </Button>
                  </ButtonGroup>
                ) : null}
              </div>
            </TitleInfo>
          </Left>
          <ImgBtn>
            {data.rating === "LIKE" ? (
              <AiTwotoneLike className="likeImg" />
            ) : (
              <AiTwotoneDislike className="hateImg" />
            )}
          </ImgBtn>
        </ReviewHead>
        <ReviewContent>
          <div className="username">{data.member.nickName}</div>
          <div className="contents">
            <Text>{data.content}</Text>
            {/* <Photo>
              {data.photo.map((data, imgIndex) => (
                <PhotoItem key={imgIndex} src={data.photo} alt={null} />
              ))}
            </Photo> */}
          </div>
        </ReviewContent>
      </Container>
    </>
  );
};

export default ReviewItem;
