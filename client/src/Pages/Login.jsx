import styled from "styled-components";
import Button from "../Component/style/StyleButton";
import Input from "../Component/style/StyleInput";
import Logo from "../Component/style/img/Eaaaaaaats.svg";
import { useState } from "react";
import { Link } from "react-router-dom";
import Auth from "../Component/StyleAuth";
import Modal from "../Component/Modal";
const Main = styled.div`
  flex-direction: column;
`;

const Container = styled.div`
  width: 350px;
  height: 400px;
  box-shadow: 4px 4px 30px rgba(0, 0, 0, 0.1);
  border-radius: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 20px 30px;
`;

const Img = styled.img`
  width: 150px;
  height: 35px;
  margin: 30px 0px;
`;

const Textdiv = styled.div`
  width: 100%;
  height: 100px;
  margin-bottom: 20px;
`;

const P = styled.div`
  font-size: var(--large-font);
  font-weight: 600;
  margin-bottom: 10px;
`;

const Btndiv = styled(Textdiv)`
  margin-top: 10px;
  display: flex;
  justify-content: space-around;
`;

const Errdiv = styled.div`
  padding: 7px 6px;
`;
const Errspan = styled.div`
  color: var(--red-500);
  font-size: 14px;
`;
const Authdiv = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 20px;
`;

export default function Login() {
  const [err, setErr] = useState(true);
  const [errMessage, setErrMessage] = useState("");
  const [errPw, setErrPw] = useState("");
  const [member, setMember] = useState({
    email: "",
    password: "",
  });
  const [modal, setModal] = useState(false);

  const handleInputValue = (key) => (e) => {
    setMember({ ...member, [key]: e.target.value });
  };

  function onClick() {
    setErr(!err);
    setErrMessage("등록되지 않는 이메일입니다.");
    setErrPw("패스워드가 맞지 않습니다.");
  }

  const showModal = () => {
    setModal(!modal);
  };

  const menu = [
    { menu: "샌드위치", price: 4000 },
    { menu: "B", price: 2000 },
    { menu: "햄버거", price: 7000 },
    { menu: "샌드위치", price: 4000 },
    { menu: "콜라", price: 2000 },
    { menu: "햄버거", price: 7000 },
    { menu: "샌드위치", price: 4000 },
    { menu: "사이다", price: 2000 },
    { menu: "A", price: 7000 },
    { menu: "1", price: 4000 },
    { menu: "2", price: 2000 },
    { menu: "3", price: 7000 },
    { menu: "4", price: 4000 },
    { menu: "5", price: 2000 },
    { menu: "햄버거", price: 7000 },
    { menu: "샌드위치", price: 4000 },
    { menu: "콜라", price: 2000 },
    { menu: "햄버거", price: 7000 },
    { menu: "2", price: 2000 },
    { menu: "3", price: 7000 },
    { menu: "4", price: 4000 },
    { menu: "5", price: 2000 },
    { menu: "햄버거", price: 7000 },
    { menu: "샌드위치", price: 4000 },
    { menu: "콜라", price: 2000 },
    { menu: "햄버거", price: 7000 },
    { menu: "2", price: 2000 },
    { menu: "3", price: 7000 },
    { menu: "4", price: 4000 },
    { menu: "5", price: 2000 },
    { menu: "햄버거", price: 7000 },
    { menu: "샌드위치", price: 4000 },
    { menu: "콜라", price: 2000 },
    { menu: "햄버거", price: 7000 },
  ];

  return (
    <>
      <div>{modal ? <Modal menu={menu} showModal={showModal} /> : null}</div>
      <Main>
        <button onClick={showModal}> 모달 테스트 </button>

        <Container>
          <Img src={Logo} alt="" />

          <Textdiv>
            <P>이메일</P>
            {err ? (
              <Input
                inputType="default"
                placeholder="email"
                onChange={handleInputValue("email")}
              />
            ) : (
              <>
                <Input
                  inputType="error"
                  placeholder="email"
                  onChange={handleInputValue("email")}
                />
                <Errdiv>
                  <Errspan>* {errMessage}</Errspan>
                </Errdiv>
              </>
            )}
          </Textdiv>
          <Textdiv>
            <P>비밀번호</P>
            {err ? (
              <Input
                type="password"
                inputType="default"
                placeholder="password"
                onChange={handleInputValue("password")}
              />
            ) : (
              <>
                <Input
                  type="password"
                  inputType="error"
                  placeholder="password"
                  onChange={handleInputValue("password")}
                />
                <Errdiv>
                  <Errspan>* {errPw}</Errspan>
                </Errdiv>
              </>
            )}
          </Textdiv>
          <Btndiv>
            <Button btnstyle="Btn" width="120px" onClick={onClick}>
              로그인
            </Button>
            <Link to="/signup">
              <Button btnstyle="Btn" width="120px">
                회원가입
              </Button>
            </Link>
          </Btndiv>
        </Container>
        <Authdiv>
          <Auth Btnstyle="google"> 구글로 로그인 </Auth>
          <Auth Btnstyle="kakao"> 카카오로 로그인 </Auth>
        </Authdiv>
      </Main>
    </>
  );
}
