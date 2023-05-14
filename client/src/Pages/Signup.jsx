import { useState, useRef } from "react";
import Select from "react-select";
//import { Link } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import Button from "../Component/style/StyleButton";
import Input from "../Component/style/StyleInput";
import Plus from "../Component/style/img/signup.svg";
import Auth from "../Component/StyleAuth";
import { useRecoilState, useSetRecoilState } from "recoil";
import memberState from "../state/atoms/SignAtom";
import isLoginState from "../state/atoms/IsLoginAtom";

const Main = styled.main`
  margin: 20px 0;
  flex-direction: column;
`;
const Container = styled.div`
  width: 350px;
  height: auto;
  box-shadow: 4px 4px 30px rgba(0, 0, 0, 0.1);
  border-radius: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 20px 30px;
`;

const Imgdiv = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 120px;
  height: 120px;
  background-color: var(--black-070);
  border-radius: 50%;
  margin-bottom: 20px;
`;
const ImgBtn = styled.input`
  display: none;
`;
const Imglabel = styled.label`
  cursor: pornter;
  width: ${(prop) => prop.width || "24px"};
  height: ${(prop) => prop.height || "24px"};
  cursor: pointer;
  border-radius: 50%;
  background-image: ${(prop) => prop.img || `url(${Plus})`};
  background-repeat: no-repeat;
  background-position: ${(prop) => prop.posi || `50% center`};
  background-size: cover;
`;

const Textdiv = styled.div`
  width: 100%;
  height: 100px;
  margin-bottom: 10px;
`;

const P = styled.div`
  font-size: var(--large-font);
  font-weight: 600;
  margin-bottom: 10px;
`;

const Ceodiv = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
  margin-bottom: 10px;
`;

const Ceospan = styled.span`
  color: var(--black-500);
  font-size: 14px;
`;

const Authdiv = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 20px;
`;

const Errdiv = styled.div`
  padding: 7px 6px;
`;
const Errspan = styled.div`
  color: var(--red-500);
  font-size: 14px;
`;

const { kakao } = window;
function Signup() {
  const imgRef = useRef();
  const [member, setMember] = useRecoilState(memberState);
  const isLoginHandler = useSetRecoilState(isLoginState);
  const [imgFile, setImgFile] = useState(""); // 프로필 이미지 상태
  const [pwCheck, setPwCheck] = useState(""); // 비밀번호 확인
  const [Check, setCheck] = useState({
    // 회원가입 양식 확인
    email: true,
    username: true,
    password: true,
    location: true,
  });

  const selectOption = [{ value: "강남구", label: "강남구" }]; // 주소 셀렉트 옵션
  const errMsg = [
    "* 이메일 형식으로 입력해주세요.",
    "* 닉네임을 입력해주세요.",
    "* 비밀번호가 다릅니다.",
    "* 지역을 선택해주세요.",
  ];

  const handleInputValue = (key) => (e) => {
    setMember({ ...member, [key]: e.target.value });
  };

  //프로필 이미지 저장
  const saveImgFile = (e) => {
    const leng = e.target.files;
    if (leng.length === 0) {
      return;
    }
    const file = imgRef.current.files[0];
    const reader = new FileReader();

    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setImgFile(reader.result);
    };
  };

  // 주소 => 좌표 변환
  const coordFunc = (coord) => {
    const geocoder = new kakao.maps.services.Geocoder();

    geocoder.addressSearch(
      `서울특별시 ${coord} 강남대로 지하396`,
      function (result, status) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          setMember({
            ...member,
            la: coords.La,
            ma: coords.Ma,
            location: coord,
          });
        }
      },
    );
  };

  const checkingFunc = () => {
    if (!member.username) {
      setCheck({ ...Check, username: false });
      return;
    } else if (!member.email || !member.email.includes("@")) {
      setCheck({ ...Check, email: false, username: true });
      return;
    } else if (
      pwCheck !== member.password ||
      !member.password ||
      member.password.length < 8
    ) {
      setCheck({ ...Check, password: false, email: true });
      return;
    } else if (!member.location) {
      setCheck({ ...Check, location: false, password: true });
      return;
    }
    return axios
      .post(`http://localhost:4000/members`, {
        email: member.email,
        nickName: member.username,
        password: member.password,
        location: member.location,
        la: member.la,
        ma: member.ma,
        CEO: member.CEO,
        img: imgFile,
      })
      .then(() => {
        isLoginHandler(true);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <Main>
      <Container>
        <Imgdiv>
          {!imgFile ? (
            <Imglabel htmlFor="profileImg"></Imglabel>
          ) : (
            <Imglabel
              htmlFor="profileImg"
              img={`url(${imgFile})`}
              posi={"none"}
              width={"100%"}
              height={"100%"}
            ></Imglabel>
          )}
          <ImgBtn
            type="file"
            accept="image/*"
            id="profileImg"
            onChange={
              ((e) => setImgFile(e.target.files), (e) => saveImgFile(e))
            }
            ref={imgRef}
          ></ImgBtn>
        </Imgdiv>
        <P>프로필 사진</P>
        <Textdiv>
          <P>닉네임</P>
          <Input
            inputType="default"
            placeholder="username"
            onChange={handleInputValue("username")}
          />
          {!Check.username ? (
            <Errdiv>
              <Errspan>{errMsg[1]}</Errspan>
            </Errdiv>
          ) : null}
        </Textdiv>
        <Textdiv>
          <P>이메일</P>
          <Input
            inputType="default"
            placeholder="email"
            onChange={handleInputValue("email")}
          />
          {!Check.email ? (
            <Errdiv>
              <Errspan>{errMsg[0]}</Errspan>
            </Errdiv>
          ) : null}
        </Textdiv>
        <Textdiv>
          <P>비밀번호</P>
          <Input
            type="password"
            inputType="default"
            placeholder="password"
            onChange={handleInputValue("password")}
          />
        </Textdiv>
        <Textdiv>
          <P>비밀번호 확인</P>
          <Input
            type="password"
            inputType="default"
            placeholder="비밀번호 확인"
            onChange={(e) => setPwCheck(e.target.value)}
          />
          {!Check.password ? (
            <Errdiv>
              <Errspan>{errMsg[2]}</Errspan>
            </Errdiv>
          ) : null}
        </Textdiv>
        <Textdiv>
          <P>지역</P>
          <Select
            onChange={(e) => coordFunc(e.value)}
            placeholder="주소"
            options={selectOption}
          ></Select>
          {!Check.location ? (
            <Errdiv>
              <Errspan>{errMsg[3]}</Errspan>
            </Errdiv>
          ) : null}
        </Textdiv>
        <Textdiv>
          <P>사장님 계정</P>
          <Ceodiv>
            <Ceospan>사업자 계정 등록</Ceospan>
            <Input
              type="checkbox"
              inputType="default"
              placeholder="email"
              width="15px"
              height="15px"
              onChange={(e) => setMember({ ...member, CEO: e.target.checked })}
            />
          </Ceodiv>
        </Textdiv>

        <Button btnstyle="Btn" width="280px" onClick={checkingFunc}>
          회원가입
        </Button>
      </Container>
      <Authdiv>
        <Auth Btnstyle="google"> 구글로 회원가입 </Auth>
        <Auth Btnstyle="kakao"> 카카오로 회원가입 </Auth>
      </Authdiv>
    </Main>
  );
}

export default Signup;
