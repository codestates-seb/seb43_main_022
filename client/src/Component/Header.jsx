import styled from "styled-components";
import Button from "./style/button";

import Logo from "./style/img/Eaaaaaaats.svg";
import Search from "./style/img/search.png";
import Frame from "./style/img/Frame.svg";
import { useState } from "react";

const Container = styled.header`
  width: 100%;
  height: 69px;
  background: var(--white);
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0px 6px 30px rgba(0, 0, 0, 0.05);
  padding: 0px 300px;
`;

const LogoBtn = styled.button`
  width: 170px;
  height: 39px;
  background-image: url(${Logo});
  background-size: cover;
  background-repeat: no-repeat;
`;

const LoginDiv = styled.div`
  display: flex;
  justify-content: space-between;
  width: 200px;
`;

const Hinput = styled.input`
  width: 700px;
  height: 41px;
  font-size: var(--medium-font);
  border: 1px solid var(--black-200);
  border-radius: 10px;
  padding: 0px 20px;
  flex-grow: 1;
  margin: 0px 70px;
  background-image: url(${Search});
  background-repeat: no-repeat;
  background-position: 770px center;

  &:active,
  &:focus {
    outline: none;
  }

  @media screen and (max-width: 750px) {
    min-width: 300px;
  }
  @media screen and (max-width: 970px) {
    min-width: 300px;
  }
  @media screen and (max-width: 1200px) {
    min-width: 300px;
  }
`;

const IsLoginInput = styled(Hinput)`
  background-position: 880px center;
`;

const Frameicon = styled.img`
  margin-right: 8px;
`;

const Header = () => {
  const [isLogin, setIsLogin] = useState(true);

  return (
    <>
      {isLogin ? (
        <Container>
          <LogoBtn />
          <Hinput placeholder="지역/ 상호/ 키워드를 입력해주세요." />
          <LoginDiv>
            <Button btnstyle="HBtn" onClick={() => setIsLogin(false)}>
              로그인
            </Button>
            <Button btnstyle="HBtn">회원가입</Button>
          </LoginDiv>
        </Container>
      ) : (
        <Container>
          <LogoBtn />
          <IsLoginInput placeholder="지역/ 상호/ 키워드를 입력해주세요." />
          <Button btnstyle="HBtn" onClick={() => setIsLogin(true)}>
            <Frameicon src={Frame} alt="" />
            마이페이지
          </Button>
        </Container>
      )}
    </>
  );
};

export default Header;
