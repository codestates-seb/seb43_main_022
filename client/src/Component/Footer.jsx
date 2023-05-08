import styled from "styled-components";
import { ReactComponent as Logo } from "./style/img/Eaaaaaaats.svg";

const Container = styled.footer`
  width: 100%;
  height: 180px;
  background: var(--footergreen);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 300px;
  color: var(--white);
`;

const HLogo = styled(Logo)`
  width: 200px;
  height: 46px;
  background: var(--footergreen);
  path {
    fill: var(--white);
  }
`;

const Copyright = styled.div`
  background: var(--footergreen);
  color: var(--white);
`;

const Intro = styled.div`
  background: var(--footergreen);
  margin-right: 500px;
  margin-top: 27px;
`;

const FE = styled.div`
  background: var(--footergreen);
  font-size: var(--large-font);
  font-weight: 600;
  width: 60px;
  margin: 0px 10px;
  text-align: center;
  height: 30px;
`;

const P = styled.p`
  background: var(--footergreen);

  font-size: var(--large-font);
  font-weight: 600;
`;

export default function Footer() {
  return (
    <Container>
      <HLogo />
      <Intro>
        <FE>
          <P>FE 임민규 고정윤 임주헌 정은옥</P>
        </FE>
        <FE>
          <P>BE 차진수 조현민 서지웅</P>
        </FE>
      </Intro>
      <Copyright>Copyright © 2023 codea. All rights reserved.</Copyright>
    </Container>
  );
}
