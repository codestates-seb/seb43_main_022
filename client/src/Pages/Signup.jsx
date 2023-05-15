import { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import { useRecoilState } from "recoil";
import memberState from "../state/atoms/SignAtom";
import Button from "../Component/style/StyleButton";
import Input from "../Component/style/StyleInput";
import Auth from "../Component/StyleAuth";
import Plus from "../Component/style/img/signup.svg";

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
  margin-bottom: 5px;
`;

const LocationWrap = styled.div`
  width: 100%;
  display: flex;
  height: 35px;
  justify-content: space-between;
  margin-bottom: 10px;
`;

const LocationBtn = styled.button`
  width: 60px;
  height: 30px;
  border-radius: 20px;
  margin-left: 10px;
  font-size: 14px;
  border: 1px solid var(--black-200);
  color: var(--black-200);
  background: var(--white);

  &: hover {
    background: var(--eatsgreen);
    color: var(--white);
  }
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
  const navi = useNavigate();
  const [member, setMember] = useRecoilState(memberState);

  const [imgFile, setImgFile] = useState(""); // 프로필 이미지 상태
  const [pwCheck, setPwCheck] = useState(""); // 비밀번호 확인
  const [Address, setAddress] = useState({
    address: "",
    detailAddress: "",
  });
  const [Check, setCheck] = useState({
    // 회원가입 양식 확인
    email: true,
    username: true,
    password: true,
    streetAddress: true,
  });

  const errMsg = [
    "* 이메일 형식으로 입력해주세요.",
    "* 닉네임을 입력해주세요.",
    "* 비밀번호가 다릅니다.",
    "* 지역을 선택해주세요.",
  ];

  const handleInputValue = (key) => (e) => {
    setMember({ ...member, [key]: e.target.value });
  };

  // base64 인코딩 & 프로필 이미지 저장
  const saveImgFile = (e) => {
    const leng = e.target.files;
    if (leng.length === 0) {
      setImgFile("");
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

  const onSearchAddr = () => {
    new window.daum.Postcode({
      oncomplete: function (data) {
        setAddress({
          ...Address,
          address: data.roadAddress,
        });
      },
    }).open();
  };

  const checkingFunc = () => {
    setMember({
      ...member,
      streetAddress: Address.address + " " + Address.detailAddress,
    });
    if (!member.username) {
      setCheck({ ...Check, username: false });
      return;
    } else if (!member.email || !member.email.includes("@")) {
      setCheck({ ...Check, email: false, username: true });
      return;
    } else if (
      member.password.length < 8 ||
      pwCheck !== member.password ||
      !member.password
    ) {
      setCheck({ ...Check, password: false, email: true });
      return;
    } else if (!member.streetAddress) {
      setCheck({ ...Check, streetAddress: false, password: true });
      return;
    }

    return axios
      .post(`http://localhost:4000/members`, {
        email: member.email,
        nickName: member.username,
        password: member.password,
        streetAddress: member.streetAddress,
        latitude: member.latitude,
        longitude: member.ma,
        businessAccount: member.CEO,
        photo: imgFile,
      })
      .then(() => {
        alert("회원가입한 계정으로 로그인 해주세요.");
        //resetMember;
        navi("/login");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    const script = document.createElement("script");
    script.src =
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js?";
    script.async = true;
    document.body.appendChild(script);
  }, []);

  useEffect(() => {
    const geocoder = new kakao.maps.services.Geocoder();
    geocoder.addressSearch(
      Address.address || "제주특별자치도 제주시 첨단로 242",
      function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
          setMember({
            ...member,
            latitude: result[0].y,
            longitude: result[0].x,
          });
        }
      },
    );
  }, [Address.address]);

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
            onChange={(e) => saveImgFile(e)}
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
          <LocationWrap>
            <P>주소</P>
            <LocationBtn btnstyle="SBtn" onClick={onSearchAddr}>
              검색
            </LocationBtn>
          </LocationWrap>
          <Input
            type="text"
            inputType="default"
            value={Address.address || ""}
            readOnly="readOnly"
          />
        </Textdiv>

        {!Address.address ? null : (
          <Textdiv>
            <P>상세주소</P>
            <Input
              type="text"
              inputType="default"
              placeholder="상세 주소"
              onChange={(e) =>
                setAddress({ ...Address, detailAddress: e.target.value })
              }
            />
          </Textdiv>
        )}

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
      {console.log(member)}
      <Authdiv>
        <Auth Btnstyle="google"> 구글로 회원가입 </Auth>
        <Auth Btnstyle="kakao"> 카카오로 회원가입 </Auth>
      </Authdiv>
    </Main>
  );
}

export default Signup;
