import styled from "styled-components";
import Button from "../style/StyleButton";
import ImgBtn from "../style/ImgBtn";
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

const Left = styled.div`
  display: flex;
  justify-content: start;
  align-items: center;
`;
const Profile = styled.img`
  height: 110px;
  width: 110px;
  border: 1px solid gray;
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
const Photo = styled.div`
  display: flex;
  width: 1200px;
  flex-direction: row;
  margin-top: 20px;
  overflow: hidden;
`;
const PhotoItem = styled.img`
  width: 281px;
  height: 135px;
  flex-shrink: 0;
  margin-right: 10px;
  margin-top: 50px;
  border: 1px solid gray;
`;

const ReviewItem = ({ data, onDelete }) => {
  const { res_id } = useParams();

  const navigate = useNavigate();
  const modifyClick = () => {
    navigate(`/review/edit/${res_id}/${data.reviewId}`);
  };

  const member = useRecoilValue(memberState);

  const handleDelete = async () => {
    if (window.confirm("리뷰를 삭제 하시겠습니까?"))
      try {
        await api.delete(`/reviews/${data.reviewId}`);
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
            <Profile src={data.member.image} />
            <TitleInfo>
              <div className="title">{data.title}</div>
              <div className="day-button">
                <span>
                  {data.modifiedAt.slice(0, 10) || data.createdAt.slice(0, 10)}
                </span>
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

          {data.rating === "LIKE" ? (
            <ImgBtn imgstyle={"LIKEFill"} />
          ) : (
            <ImgBtn imgstyle={"HATEFill"} />
          )}
        </ReviewHead>
        <ReviewContent>
          <div className="username">{data.member.nickName}</div>
          <div className="contents">
            <Text>{data.content}</Text>
            <Photo>
              {data.image &&
                data.image.length > 0 &&
                data.image.map((imageData) => (
                  <PhotoItem
                    key={imageData.imageId}
                    src={imageData.image}
                    alt={imageData.imageName}
                  />
                ))}
            </Photo>
          </div>
        </ReviewContent>
      </Container>
    </>
  );
};

export default ReviewItem;
