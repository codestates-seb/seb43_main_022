import styled from "styled-components";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { api } from "../Util/api";
import isLoginState from "../state/atoms/IsLoginAtom";
import memberState from "../state/atoms/SignAtom";
import Button from "./style/StyleButton";
import Logo from "./style/img/Eaaaaaaats.svg";
import Search from "./style/img/search.png";
import Frame from "./style/img/Frame.svg";
import { useRecoilState, useResetRecoilState, useSetRecoilState } from "recoil";
import {
  searchResultsState,
  searchKeywordState,
} from "../state/atoms/SearchStateAtom";

const Container = styled.header`
  width: 100vw;
  height: 69px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 6px 30px rgba(0, 0, 0, 0.05);
  @media screen and (max-width: 1300px) {
    width: 100%;
  }
`;

const LogoBtn = styled.button`
  width: 170px;
  height: 39px;
  background-color: var(--white);
  background-image: url(${Logo});
  background-size: cover;
  background-repeat: no-repeat;
`;
const InputContainer = styled.div`
  position: relative;
`;
const LoginDiv = styled.div`
  display: flex;
  justify-content: center;
  width: 240px;
  @media screen and (max-width: 850px) {
    justify-content: center;
    width: 210px;
  }
`;
const ListItem = styled(LoginDiv)`
  width: 100px;
  margin-left: 20px;

  font-size: var(--medium-font);
`;
const Hinput = styled.input`
  min-width: 800px;
  height: 35px;
  font-size: var(--medium-font);
  border: 1px solid var(--black-200);
  border-radius: 10px;
  padding: 0px 20px;
  flex-grow: 0.2;
  margin-left: 40px;

  &:active,
  &:focus {
    outline: none;
  }

  @media screen and (max-width: 1330px) {
    min-width: 600px;
  }
  @media screen and (max-width: 1100px) {
    min-width: 400px;
    flex-grow: 0;
  }
  @media screen and (max-width: 850px) {
    min-width: 300px;
    flex-grow: 0;
    background-image: none;
  }
`;

const IsLoginInput = styled(Hinput)`
  background-position: 98% center;
`;
const SearchButton = styled.button`
  position: absolute;
  top: 0;
  right: 10px;
  width: 30px;
  height: 100%;
  background-color: transparent;
  background-image: url(${Search});
  background-repeat: no-repeat;
  background-position: center;
  border: none;
  cursor: pointer;
`;
const Frameicon = styled.img`
  width: 17px;
  height: 17px;
  margin: 0px 5px;
`;

const Header = () => {
  const navi = useNavigate();
  const resetMember = useResetRecoilState(memberState);
  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [isComposing, setIsComposing] = useState(false);
  const [serchKeywordHeader, setSerchKeywordHeader] = useState("");
  const setSearchResultsState = useSetRecoilState(searchResultsState);
  const setSearchKeywordState = useSetRecoilState(searchKeywordState);
  const [member, setMember] = useRecoilState(memberState);

  const logoutFunc = () => {
    setIsLogin(!isLogin);
    api.defaults.headers.common["Authorization"] = "";
    localStorage.removeItem("recoil-persist");
    sessionStorage.removeItem("Authorization");
    sessionStorage.removeItem("IsLogin");
    sessionStorage.removeItem("member");

    navi("/");
  };

  const handleSearch = async () => {
    const encodedSearchTerm = encodeURIComponent(serchKeywordHeader);

    const response = await api.get(
      `/restaurants/search?keyword=${encodedSearchTerm}`,
    );

    setSearchResultsState(response.data);

    setSearchKeywordState(serchKeywordHeader);

    setSerchKeywordHeader("");
    navi(`/itemlist?search=${encodedSearchTerm}`);
  };

  const handleInputChange = (event) => {
    setSerchKeywordHeader(event.target.value);
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter" && !isComposing) {
      handleSearch();
    }
  };

  const handleCompositionStart = () => {
    setIsComposing(true);
  };

  const handleCompositionEnd = (e) => {
    setIsComposing(false);
    if (e.key === "Enter") {
      handleSearch();
    }
  };

  const handleLinkStoreList = () => {
    if (sessionStorage.getItem("Authorization")) {
      api
        .get("/members/mypage")
        .then((res) => {
          setMember(res.data);
          setMember({
            ...member,
            streetAddress: res.data.address.streetAddress,
            latitude: res.data.address.latitude,
            longitude: res.data.address.longitude,
            favorites: res.data.favorites,
          });
        })
        .catch((err) => console.log(err));
    }
    setSearchKeywordState("");
    navi(`/itemlist`);
  };
  return (
    <>
      {!isLogin ? (
        <Container>
          <Link to="/">
            <LogoBtn />
          </Link>
          <InputContainer>
            <Hinput
              placeholder="지역/ 상호/ 키워드를 입력해주세요."
              value={serchKeywordHeader}
              onChange={handleInputChange}
              onKeyDown={handleKeyDown}
              onCompositionStart={handleCompositionStart}
              onCompositionEnd={handleCompositionEnd}
            />
            <SearchButton onClick={handleSearch} />
          </InputContainer>
          <ListItem>
            <Button btnstyle="HBtn" onClick={handleLinkStoreList}>
              가게 리스트
            </Button>
          </ListItem>
          <LoginDiv>
            <Link to="/login">
              <Button btnstyle="HBtn">로그인</Button>
            </Link>
            <Link to="/signup">
              <Button btnstyle="HBtn">회원가입</Button>
            </Link>
          </LoginDiv>
        </Container>
      ) : (
        <Container>
          <Link to="/">
            <LogoBtn />
          </Link>
          <InputContainer>
            <IsLoginInput
              placeholder="지역/ 상호/ 키워드를 입력해주세요."
              value={serchKeywordHeader}
              onChange={handleInputChange}
              onKeyDown={handleKeyDown}
              onCompositionStart={handleCompositionStart}
              onCompositionEnd={handleCompositionEnd}
            />
            <SearchButton onClick={handleSearch} />
          </InputContainer>
          <ListItem>
            <Button btnstyle="HBtn" onClick={handleLinkStoreList}>
              가게 리스트
            </Button>
          </ListItem>
          <LoginDiv>
            <Link to="/mypage">
              <Button btnstyle="HBtn">
                <Frameicon src={Frame} alt="" />
                마이페이지
              </Button>
            </Link>
            <Button btnstyle="HBtn" onClick={(resetMember, () => logoutFunc())}>
              로그아웃
            </Button>
          </LoginDiv>
        </Container>
      )}
    </>
  );
};

export default Header;
