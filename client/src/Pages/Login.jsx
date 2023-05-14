import styled from "styled-components";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useSetRecoilState, useRecoilState } from "recoil";
import axios from "axios";
import isLoginState from "../state/atoms/IsLoginAtom";
import Button from "../Component/style/StyleButton";
import Input from "../Component/style/StyleInput";
import Logo from "../Component/style/img/Eaaaaaaats.svg";
import Auth from "../Component/StyleAuth";
import memberState from "../state/atoms/SignAtom";

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
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const setMember = useSetRecoilState(memberState);
  const [err, setErr] = useState(true);
  const [errMessage, setErrMessage] = useState("");
  const [errPw, setErrPw] = useState("");
  const [Loginmember, setLoginMember] = useState({
    email: "",
    password: "",
  });
  const navi = useNavigate();

  const handleInputValue = (key) => (e) => {
    setLoginMember({ ...Loginmember, [key]: e.target.value });
  };

  function onClick() {
    if (!Loginmember.email || !Loginmember.email.includes("@")) {
      setErrMessage("등록되지 않는 이메일입니다.");
      return;
    } else if (!Loginmember.password || Loginmember.password.length < 8) {
      setErrPw("패스워드가 맞지 않습니다.");
      return;
    }
    return axios
      .post(`http://localhost:4000/members`, {
        email: Loginmember.email,
        password: Loginmember.password,
      })
      .then(() => {
        setIsLogin(!isLogin);
        navi("/");
        axios.get(`http://localhost:4000/members`).then((res) => {
          setMember(res.data);
        });
      })
      .catch((err) => {
        console.log(err);
        setErr(!err);
        setErrMessage("등록되지 않는 이메일입니다.");
        setErrPw("패스워드가 맞지 않습니다.");
      });
  }

  return (
    <>
      <Main>
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
