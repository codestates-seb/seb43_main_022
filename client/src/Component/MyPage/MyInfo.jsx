import styled from "styled-components";
import Input from "./../style/StyleInput";
import Button from "../style/StyleButton";
import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import { api } from "../../Util/api";
import { useRecoilState } from "recoil";
import memberState from "../../state/atoms/SignAtom";
import isLoginState from "../../state/atoms/IsLoginAtom";

const Container = styled.div`
  margin-top: 84px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: auto;
  min-width: 1200px;
  height: 280px;
  border: none;
  border-radius: 30px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  overflow: hidden;

  > .info {
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
  }
`;
const LocationBtn = styled.button`
  width: auto;
  min-width: 60px;
  height: 30px;
  border-radius: 20px;
  margin-left: 10px;
  font-size: 14px;
  border: 1px solid var(--black-200);
  color: var(--black-200);
  background: var(--white);

  z-index: 90;
  &:hover {
    background: var(--eatsgreen);
    color: var(--white);
  }
`;

const LocationDiv = styled.div`
  height: 100px;
  display: flex;
  flex-direction: column;
  padding-right: 10px;
`;
const Profile = styled.div`
  display: flex;
  flex-direction: column;
  > .profile-img {
    height: 130px;
    width: 130px;
    border: none;
    border-radius: 50%;
    margin-right: 50px;
  }
`;

const InfoName = styled.div`
  font-size: var(--large-font);
  font-weight: bold;
  padding-bottom: 10px;
`;
const InfoReName = styled(InfoName)`
  padding-bottom: 0px;
`;

const UserInfo = styled.div`
  display: flex;
  flex-direction: row;
  height: 100%;
  margin-bottom: 10px;
  justify-content: left;
  align-items: center;

  > .userbox {
    height: 100px;
    margin-right: 10px;
  }
`;
const Buttons = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding-top: 10px;
`;

const ButtonDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: right;
  padding-top: 10px;
`;

const BusinessAccount = styled.div`
  height: 41px;
  display: flex;
  justify-content: center;
  align-items: center;
  > .buttonIM {
    width: auto;
    height: 41px;
    min-width: 110px;
    color: white;
    background-color: var(--eatsgreen);
    border: 1px solid var(--eatsgreen);
    border-radius: 30px;
    font-size: var(--font-size, 18px);
    display: flex;
    justify-content: center;
    align-items: center;

    cursor: pointer;
  }
  & > * {
    margin-right: 10px;
  }
  text-align: center;
`;

const ButtonIm = styled.button`
  width: auto;
  height: 41px;
  min-width: 110px;
  color: white;
  background-color: var(--eatsgreen);
  border: 1px solid var(--eatsgreen);
  border-radius: 30px;
  font-size: var(--font-size, 18px);
  display: flex;
  justify-content: center;
  align-items: center;

  cursor: pointer;
`;
const Btns = styled.div`
  display: flex;
  flex-direction: row;
  & > * {
    margin-right: 10px;
  }
  align-items: center;
`;
const Errdiv = styled.div`
  padding-top: 5px;
  padding-left: 5px;
`;
const Errspan = styled.div`
  color: var(--red-500);
  font-size: 14px;
`;
const CustomDiv = styled.div`
  display: flex;
  justify-content: space-between;
  padding-right: 10px;
`;

const TextArea = styled.input`
  width: auto;
  min-width: 440px;
  height: 41px;
  font-size: var(--medium-font);
  border: 1px solid var(--black-200);
  border-radius: 10px;
  padding-left: 10px;
  overflow: hidden;

  &:active,
  &:focus {
    outline: none;
  }
`;

const { kakao } = window;
const MyInfo = () => {
  const navigate = useNavigate();

  const [member, setMember] = useRecoilState(memberState);
  const [memberUpdate, setMemberUpdate] = useState(false);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [userData, setUserData] = useState({
    memberId: 0,
    nickName: "",
    email: "",
    streetAddress: "",
    detailAddress: "",
    latitude: "",
    longitude: "",
    password: "",
    businessAccount: false,
  });

  const [patchData, setPatchData] = useState({
    nickName: "",
    password: "",
    streetAddress: "",
    detailAddress: "",
    latitude: "",
    longitude: "",
  });

  const [Check, setCheck] = useState({
    detailAddress: true,
    nickName: true,
    password: true,
    streetAddress: true,
  });

  const errMsg = [
    "* 닉네임을 입력해주세요.",
    "* 비밀번호를 입력해주세요. ",
    "* 지역을 선택해주세요.",
    "* 상세주소를 입력해주세요.",
  ];

  const handleInputValue = (key) => (e) => {
    setPatchData({ ...patchData, [key]: e.target.value });
  };
  const deleteFunc = () => {
    return api
      .delete("/members")
      .then(() => {
        alert("정상적으로 회원 탈퇴가 되었습니다.");
        setIsLogin(!isLogin);
        api.defaults.headers.common["Authorization"] = "";
        localStorage.removeItem("recoil-persist");
        sessionStorage.removeItem("Authorization");
        sessionStorage.removeItem("IsLogin");
        navigate("/");
      })
      .catch((err) => console.log("delete", err));
  };
  const checkingFunc = () => {
    if (!patchData.nickName) {
      setCheck({ ...Check, nickName: false });
      return;
    }
    if (patchData.password.length < 8 || !patchData.password) {
      setCheck({ ...Check, password: false, nickName: true });
      return;
    }
    if (!patchData.streetAddress) {
      setCheck({ ...Check, streetAddress: false, password: true });
      return;
    }

    if (!patchData.detailAddress) {
      setCheck({ ...Check, detailAddress: false, streetAddress: true });
      return;
    }

    return api
      .patch(`members`, {
        nickName: patchData.nickName,
        password: patchData.password,
        streetAddress: patchData.streetAddress + " " + patchData.detailAddress,
        latitude: patchData.latitude,
        longitude: patchData.longitude,
      })
      .then((res) => {
        setUserData({
          ...userData,
          nickName: res.data.nickName,
          password: res.data.password,
          location: res.data.address.streetAddress,
          latitude: res.data.latitude,
          longitude: res.data.longitude,
        });
        setMember({
          ...member,
          memberId: res.data.memberId,
          email: res.data.email,
          streetAddress: res.data.address.streetAddress,
          businessAccount: res.data.businessAccount,
          nickName: res.data.nickName,
          latitude: res.data.address.latitude,
          longitude: res.data.address.longitude,
          favorites: res.data.favorites,
        });

        setMemberUpdate(!memberUpdate);
      })
      .catch((err) => {
        console.log("patch error", err);
      });
  };

  const onSearchAddr = () => {
    new window.daum.Postcode({
      oncomplete: function (data) {
        setPatchData({
          ...patchData,
          streetAddress: data.roadAddress,
        });
      },
    }).open();
  };
  useEffect(() => {
    const script = document.createElement("script");
    script.src =
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js?";
    script.async = true;
    document.body.appendChild(script);

    const fetchUserData = async () => {
      try {
        const response = await api.get("members/mypage");
        const { memberId, nickName, email, businessAccount, image } =
          response.data;

        setUserData({
          memberId: memberId,
          nickName: nickName,
          password: response.data.password,
          email: email,
          location: response.data.address.streetAddress,
          businessAccount: businessAccount,
          image: image,
        });
      } catch (error) {
        console.error("Failed to fetch user data:", error);
      }
    };
    fetchUserData();
  }, []);

  useEffect(() => {
    const geocoder = new kakao.maps.services.Geocoder();
    geocoder.addressSearch(
      patchData.streetAddress || "제주특별자치도 제주시 첨단로 242",
      function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          setPatchData({
            ...patchData,
            latitude: result[0].y,
            longitude: result[0].x,
          });
        }
      },
    );
  }, [patchData.streetAddress]);

  return (
    <Container>
      <Profile>
        <InfoName>프로필 이미지</InfoName>
        <img className="profile-img" src={userData.image} alt="profile" />
      </Profile>
      {!memberUpdate ? (
        <div className="info">
          <UserInfo>
            <div className="userbox">
              <InfoName>닉네임</InfoName>
              <Input
                value={userData.nickName || ""}
                placeholder="username"
                readOnly={"readOnly"}
              />
            </div>
            <div className="userbox">
              <InfoName>이메일</InfoName>
              <Input
                value={userData.email || ""}
                placeholder="eaaats@codea.com"
                readOnly={"readOnly"}
              />
            </div>
          </UserInfo>
          <div className="userbox">
            <InfoName>지역</InfoName>
            <TextArea value={userData.location || ""} readOnly={"readOnly"} />
          </div>
          <Buttons>
            {/* 서버연결 후 확인하기 */}
            <div>
              {userData.businessAccount ? (
                <BusinessAccount>
                  <InfoReName>사업자 계정</InfoReName>
                  <Link to="/addstore">
                    <ButtonIm>업체등록</ButtonIm>
                  </Link>
                </BusinessAccount>
              ) : null}
            </div>
            <Btns>
              <Button
                btnstyle="Btn"
                onClick={() => setMemberUpdate(!memberUpdate)}
              >
                정보수정
              </Button>
              <Button btnstyle="Btn" onClick={() => deleteFunc()}>
                회원탈퇴
              </Button>
            </Btns>
          </Buttons>
        </div>
      ) : (
        <div className="info">
          <UserInfo>
            <div className="userbox">
              <InfoName>닉네임</InfoName>
              <Input
                value={patchData.nickName || ""}
                placeholder="username"
                onChange={handleInputValue("nickName")}
              />
              {!Check.nickName ? (
                <Errdiv>
                  <Errspan>{errMsg[0]}</Errspan>
                </Errdiv>
              ) : (
                <Errdiv></Errdiv>
              )}
            </div>
            <div className="userbox">
              <InfoName>비밀번호</InfoName>
              <Input
                type="password"
                value={patchData.password || ""}
                onChange={handleInputValue("password")}
              />
              {!Check.password ? (
                <Errdiv>
                  <Errspan>{errMsg[1]}</Errspan>
                </Errdiv>
              ) : (
                <Errdiv></Errdiv>
              )}
            </div>
          </UserInfo>
          <UserInfo>
            <LocationDiv>
              <CustomDiv>
                <InfoName>주소</InfoName>

                <LocationBtn btnstyle="SBtn" onClick={onSearchAddr}>
                  검색
                </LocationBtn>
              </CustomDiv>
              <TextArea
                inputType="default"
                value={patchData.streetAddress || ""}
                readOnly="readOnly"
              />

              {!Check.streetAddress ? (
                <Errdiv>
                  <Errspan>{errMsg[2]}</Errspan>
                </Errdiv>
              ) : (
                <Errdiv></Errdiv>
              )}
            </LocationDiv>

            <div className="userbox">
              <InfoName>상세 주소</InfoName>
              <Input type="text" onChange={handleInputValue("detailAddress")} />
              {!Check.detailAddress ? (
                <Errdiv>
                  <Errspan>{errMsg[3]}</Errspan>
                </Errdiv>
              ) : (
                <Errdiv> </Errdiv>
              )}
            </div>
          </UserInfo>

          <ButtonDiv>
            <Btns>
              <Button
                btnstyle="Btn"
                onClick={
                  (() => setMemberUpdate(!memberUpdate), () => checkingFunc())
                }
              >
                수정 완료
              </Button>
              <Button
                btnstyle="Btn"
                onClick={() => setMemberUpdate(!memberUpdate)}
              >
                취소
              </Button>
            </Btns>
          </ButtonDiv>
        </div>
      )}
    </Container>
  );
};

export default MyInfo;
