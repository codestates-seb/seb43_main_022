import styled from "styled-components";
import MenuItem from "./MenuItem";
import Button from "./../style/StyleButton";
import Modal from "../Modal";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
// import isLoginState from "../../state/atoms/IsLoginAtom";
// import { useRecoilValue } from "recoil";
import { api } from "../../Util/api";

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
    width: 850px;

    height: 1px;
    background-color: var(--black-200);
    transform: translateY(-50%); /* 수직 가운데 정렬 */
  }
`;

const StoreInfo = () => {
  const navigate = useNavigate();
  const onClickModify = () => {
    navigate("/addstore");
  };

  const [modal, setModal] = useState(false);
  const showModal = () => {
    setModal(!modal);
  };

  const [data, setData] = useState({
    restaurantId: 1,
    address: { addressId: 0, streetAddress: "", latitude: 0, longitude: 0 },
    tel: "",
    category: "",
    open_time: "",
  });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get("/restaurants/1");
        const { restaurantId, address, tel, category, open_time, menu } =
          response.data;
        setData({
          restaurantId,
          address,
          tel,
          category,
          open_time,
          menu,
        });
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  const updatedDate = new Date().toLocaleDateString("ko-KR", {
    year: "numeric",
    month: "long",
    day: "numeric",
  }); // 수정하기

  return (
    <>
      <Container>
        <InfoList>
          <InfoItem>
            <InfoName>주소</InfoName>
            <InfoContent>{data.address.streetAddress}</InfoContent>
          </InfoItem>
          <InfoItem>
            <InfoName>전화번호</InfoName>
            <InfoContent>{data.tel}</InfoContent>
          </InfoItem>
          <InfoItem>
            <InfoName>음식 종류</InfoName>
            <InfoContent>{data.category}</InfoContent>
          </InfoItem>
          <InfoItem>
            <InfoName>영업시간</InfoName>
            <InfoContent>{data.open_time}</InfoContent>
          </InfoItem>
        </InfoList>
        <MenuList>
          <div>
            <InfoName>메뉴</InfoName>
            <MenuItem menu={data.menu} />
          </div>
          <div>
            {modal ? <Modal menu={data.menu} showModal={showModal} /> : null}
          </div>
          <More onClick={showModal}>메뉴 전체보기</More>
        </MenuList>
      </Container>
      <Modify>
        <span>최종 업데이트 {updatedDate}</span>
        <Button btnstyle="SBtn2" onClick={onClickModify}>
          수정
        </Button>
      </Modify>
    </>
  );
};

export default StoreInfo;
