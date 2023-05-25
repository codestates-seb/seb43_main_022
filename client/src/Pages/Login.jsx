import styled from "styled-components";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import memberState from "../state/atoms/SignAtom";
import isLoginState from "../state/atoms/IsLoginAtom";
import Button from "../Component/style/StyleButton";
import Input from "../Component/style/StyleInput";
import Logo from "../Component/style/img/Eaaaaaaats.svg";

import { api } from "../Util/api";

const Main = styled.div`
  flex-direction: column;
  display: flex;
  justify-content: center;
  align-items: center;
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

export default function Login() {
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [member, setMember] = useRecoilState(memberState);
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

  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") onClick();
  };

  function onClick() {
    if (!Loginmember.email || !Loginmember.email.includes("@")) {
      setErrPw("");
      setErrMessage("등록되지 않는 이메일입니다.");
      return;
    } else if (!Loginmember.password || Loginmember.password.length < 8) {
      setErrMessage("");
      setErrPw("패스워드가 양식에 맞지 않습니다.");
      return;
    }
    return api
      .post(`/members/login`, {
        email: Loginmember.email,
        password: Loginmember.password,
      })
      .then((res) => {
        setIsLogin(!isLogin);
        sessionStorage.setItem(
          "Authorization",
          res.headers.get("Authorization"),
        );
        navi("/");
        api
          .get(`/members/mypage`)
          .then((res) => {
            setIsLogin(!isLogin);
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
          })
          .catch((err) => {
            console.log(err);
          });
      })
      .catch((err) => {
        if (err.response.status === 401) {
          alert("이메일 혹은 비밀번호가 다릅니다.");
        }
        if (err.response.status === 405) {
          alert("이메일 혹은 비밀번호가 다릅니다.");
        }
      });
  }

  return (
    <>
      <Main>
        <Container>
          <Img src={Logo} alt="" />
          <Textdiv>
            <P>이메일</P>
            {!errMessage ? (
              <Input
                inputType="default"
                placeholder="email"
                value={Loginmember.email}
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
            {!errPw ? (
              <Input
                type="password"
                inputType="default"
                placeholder="password"
                value={Loginmember.password}
                onChange={handleInputValue("password")}
                onKeyPress={handleOnKeyPress}
              />
            ) : (
              <>
                <Input
                  type="password"
                  inputType="error"
                  placeholder="password"
                  onChange={handleInputValue("password")}
                  onKeyPress={handleOnKeyPress}
                />
                <Errdiv>
                  <Errspan>* {errPw}</Errspan>
                </Errdiv>
              </>
            )}
          </Textdiv>
          <Btndiv>
            <Button btnstyle="Btn" width="120px" onClick={() => onClick()}>
              로그인
            </Button>
            <Link to="/signup">
              <Button btnstyle="Btn" width="120px">
                회원가입
              </Button>
            </Link>
          </Btndiv>
        </Container>
      </Main>
    </>
  );
}
