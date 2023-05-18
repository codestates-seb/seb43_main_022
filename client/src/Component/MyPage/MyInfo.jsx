import styled from "styled-components";
import Input from "./../style/StyleInput";
import Button from "../style/StyleButton";
import profile from "./../style/img/profile.png";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
// import { api } from "../../Util/api";
import axios from "axios";

const Container = styled.div`
  margin-top: 84px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

  width: 1200px;
  height: 280px;
  border: none;
  border-radius: 30px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  overflow: hidden;

  > .info {
    flex-direction: column;
    justify-content: space-between;
  }
`;
const Profile = styled.div`
  display: flex;
  flex-direction: column;
  > .profile-img {
    height: 130px;
    width: 130px;
    border: none;
    border-radius: 100%;
    margin-right: 50px;
  }
`;

const InfoName = styled.div`
  font-size: var(--large-font);
  font-weight: bold;
  padding-bottom: 20px;
`;

const UserInfo = styled.div`
  display: flex;
  flex-direction: row;
  > .userbox {
    margin-right: 10px;
  }
`;
const Select = styled.select`
  width: var(--width, 293px);
  height: var(--height, 41px);
  font-size: var(--medium-font);
  border: var(--border, 1px solid var(--black-200));
  border-radius: 10px;
  padding: 0px 10px;
`;
const Buttons = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;

  padding-top: 40px;
`;

const BusinessAccount = styled.div`
  > .button {
    width: auto;
    height: 41px;
    min-width: 110px;
    color: white;
    background-color: var(--eatsgreen);
    border: var(--border, 1px solid var(--eatsgreen));
    border-radius: 30px;
    font-size: var(--font-size, 18px);
    display: flex;
    justify-content: center;
    align-items: center;
    padding: var(--padding, 0px, 0px, 0px, 0px);
    margin: var(--margin, 0px, 0px, 0px, 0px);
    cursor: pointer;
  }
  & > * {
    margin-right: 10px;
  }
  display: flex;
  flex-direction: row;
  align-items: center;
`;
const Btns = styled.div`
  display: flex;
  flex-direction: row;
  & > * {
    margin-right: 10px;
  }
  align-items: center;
`;

const MyInfo = () => {
  const navigate = useNavigate();
  const onClickAdd = () => {
    navigate("/addstore");
  };

  const [userData, setUserData] = useState({
    memberId: 0,
    nickName: "",
    email: "",
    location: "강남구",
    businessAccount: true,
    photo: profile,
  });

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get(
          "http://ec2-54-180-31-226.ap-northeast-2.compute.amazonaws.com:8080/members/mypage",
        );
        const { memberId, nickName, email, location, businessAccount, photo } =
          response.data;
        setUserData({
          memberId: memberId,
          nickName: nickName,
          email: email,
          location: location,
          businessAccount: businessAccount,
          photo: photo || profile,
        });
      } catch (error) {
        console.error("Failed to fetch user data:", error);
      }
    };
    fetchUserData();
  }, []);

  return (
    <Container>
      <Profile>
        <InfoName>프로필 이미지</InfoName>
        <img className="profile-img" src={userData.photo} alt="profile" />
      </Profile>
      <div className="info">
        <UserInfo>
          <div className="userbox">
            <InfoName>닉네임</InfoName>
            <Input type="default" placeholder="username" />
          </div>
          <div className="userbox">
            <InfoName>이메일</InfoName>
            <Input type="default" placeholder="eaaats@codea.com" />
          </div>
          <div className="userbox">
            <InfoName>지역</InfoName>
            <Select>
              <option>강남구</option>
            </Select>
          </div>
        </UserInfo>
        <Buttons>
          {/* 서버연결 후 확인하기 */}
          {userData.businessAccount && (
            <BusinessAccount>
              <InfoName>사업자 계정</InfoName>
              <button className="button" onClick={onClickAdd}>
                업체등록
              </button>
            </BusinessAccount>
          )}
          <Btns>
            <Button btnstyle="Btn">정보수정</Button>
            <Button btnstyle="Btn">회원탈퇴</Button>
          </Btns>
        </Buttons>
      </div>
    </Container>
  );
};

export default MyInfo;
