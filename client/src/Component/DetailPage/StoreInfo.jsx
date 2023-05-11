import React from "react";
import styled from "styled-components";
import MenuItem from "./MenuItem";
import Button from "./../style/button";
import Modal from "../Modal";
import { useState } from "react";

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  height: 370px;
  width: 1200px;
`;

const InfoList = styled.div`
  display: flex;
  flex-direction: column;
  width: 600px;
  justify-content: space-between;
`;

const InfoItem = styled.div`
  margin-bottom: 30px;
`;

const MenuList = styled(InfoList)`
  justify-content: space-between;
`;

const InfoName = styled.div`
  font-size: var(--large-font);
  font-weight: bold;
  margin-bottom: 10px;
`;
const InfoContent = styled.div`
  font-size: var(--medium-font);
`;

const More = styled.button`
  font-size: var(--large-font);
`;

const Modify = styled.div`
  width: 1200px;
  display: flex;
  flex-direction: row;
  justify-content: end;
  align-items: center;
  margin-top: 70px;
  position: relative;
  > span {
    font-size: var(--medium-font);
    color: var(--black-350);
  }
  &::before {
    content: "";
    position: absolute;
    top: 50%;
    left: 0%;
    width: 900px;

    height: 1px;
    background-color: var(--black-200);
    transform: translateY(-50%); /* 수직 가운데 정렬 */
  }
`;

const StoreInfo = () => {
  const [modal, setModal] = useState(false);
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
      <Container>
        <InfoList>
          <InfoItem>
            <InfoName>주소</InfoName>
            <InfoContent>경기 용인시 수지구 용구대로 2725-2</InfoContent>
          </InfoItem>
          <InfoItem>
            <InfoName>전화번호</InfoName>
            <InfoContent>031-123-4567</InfoContent>
          </InfoItem>
          <InfoItem>
            <InfoName>음식 종류</InfoName>
            <InfoContent>브런치 / 샌드위치</InfoContent>
          </InfoItem>
          <InfoItem>
            <InfoName>영업시간</InfoName>
            <InfoContent>06:00~06:01</InfoContent>
          </InfoItem>
        </InfoList>
        <MenuList>
          <div>
            <InfoName>메뉴</InfoName>
            <MenuItem />
          </div>
          <div>
            {modal ? <Modal menu={menu} showModal={showModal} /> : null}
          </div>
          <More onClick={showModal}>메뉴 전체보기</More>
        </MenuList>
      </Container>
      <Modify>
        <span>최종 업데이트 2023.05.23</span>
        <Button btnstyle="SBtn2">수정</Button>
      </Modify>
    </>
  );
};

export default StoreInfo;
