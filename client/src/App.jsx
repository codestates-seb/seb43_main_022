import GlobalStyles from "./Globalstyle";
import styled from "styled-components";
import Button from "./Component/style/button";
import Input from "./Component/style/Input";
import ImgBtn from "./Component/style/ImgBtn.jsx";

function App() {
  const Div = styled.div`
    width: 300px;
    height: 300px;
  `;
  return (
    <>
      <GlobalStyles />
      <div className="container">
        <Div>
          <ImgBtn imgstyle={"Hate"} />
        </Div>

        <Button btnstyle="Btn">로그인</Button>
        <Button btnstyle="Btn2">중식</Button>
        <Button btnstyle="HBtn">로그인</Button>
        <Button btnstyle="SBtn">수정</Button>
        <Button btnstyle="SBtn2">수정</Button>
        <Input placeholder="아이디 입력"></Input>
        <Input inputType="error" placeholder="에러 타입"></Input>
      </div>
    </>
  );
}

export default App;
