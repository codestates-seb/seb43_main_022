import styled from "styled-components";
import Button from "../Component/style/button";
import Input from "../Component/style/Input";
import Plus from "../Component/style/img/signup.svg";
import SelectImg from "../Component/style/img/select.svg";
import Auth from "../Component/Auth";
import { useState, useRef } from "react";
import Select from "react-select";
//import { Link } from "react-router-dom";

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
const Img = styled.img`
  width: ${(prop) => prop.width || "100%"};
  height: ${(prop) => prop.height || "100%"};
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

// Select = styled.select`
//   width: 293px;
//   height: 41px;
//   border: 1px solid var(--black-200);
//   border-radius: 10px;
//   padding: 10px;
//   -webkit-appearance: none;
//   -moz-appearance: none;
//   appearance: none;
//   background-image: url(${SelectImg});
//   background-repeat: no-repeat;
//   background-position: 98% center;
// `;

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

const { kakao } = window;

function Signup() {
  const [imgFile, setImgFile] = useState("");
  const [coor, setCoor] = useState("");
  const imgRef = useRef();
  const selectRef = useRef();
  const selectOption = [{ value: "강남구", label: "강남구" }];

  const saveImgFile = () => {
    const file = imgRef.current.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = () => {
      setImgFile(reader.result);
    };
  };

  // 주소 => 좌표 변환
  // const coordFunc = (coord) => {
  //   const geocoder = new kakao.maps.services.Geocoder();

  //   geocoder.addressSearch(`${coord}`, function (result, status) {
  //     // 정상적으로 검색이 완료됐으면
  //     if (status === kakao.maps.services.Status.OK) {
  //       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
  //       console.log(coords.La);
  //       console.log(coords.Ma);
  //     }
  //   });
  // };
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
            onChange={((e) => setImgFile(e.target.files), saveImgFile)}
            ref={imgRef}
          ></ImgBtn>
        </Imgdiv>
        <P>프로필 사진</P>
        <Textdiv>
          <P>닉네임</P>
          <Input inputType="default" placeholder="username" />
        </Textdiv>
        <Textdiv>
          <P>이메일</P>
          <Input inputType="default" placeholder="email" />
        </Textdiv>
        <Textdiv>
          <P>비밀번호</P>
          <Input type="password" inputType="default" placeholder="password" />
        </Textdiv>
        <Textdiv>
          <P>비밀번호 확인</P>
          <Input type="password" inputType="default" placeholder="email" />
        </Textdiv>
        <Textdiv>
          <P>지역</P>
          <Select
            onChange={(e) => {
              setCoor(e.value);
            }}
            placeholder="주소"
            options={selectOption}
          ></Select>
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
            />
          </Ceodiv>
        </Textdiv>

        <Button btnstyle="Btn" width="280px">
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
