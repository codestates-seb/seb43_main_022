// AddStore.jsx
import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import AddHeader from "../Component/AddStoreComp/AddHeader";
import AddImg from "../Component/AddStoreComp/AddImg";
import AddEplanation from "../Component/AddStoreComp/AddExplanation";
import AddInfo from "../Component/AddStoreComp/AddInfo";
import axios from "axios";
import Button from "../Component/style/button";
import AddMenu from "../Component/AddStoreComp/AddMenu";

const AddContainer = styled.div`
  box-sizing: border-box;
  padding: 60px 0;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  label {
    font-size: var(--x-large-font);
    font-weight: 700;
  }
`;
const StoreInfoWrap = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
`;
const AddBtnWrap = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
`;
const AddStore = () => {
  // const history = useNavigate();
  const initFormData = {
    storeName: "",
    storePhoto: null,
    storeIntroduction: "",
    foodType: "",
    businessHours: "",
    menuItems: [{ name: "", price: "" }],
    phoneNumber: "",
    tags: [],
  };
  const [formData, setFormData] = useState(initFormData);

  const postFormData = async () => {
    try {
      await axios.post("server-api-url", formData);
      // 서버로 전송이 완료되면 사용자에게 알림을 줄 수 있는 로직을 추가.
      alert("업체 정보가 등록되었습니다.");
      setFormData(initFormData); // 폼 데이터 초기화
    } catch (error) {
      console.error(error);
      // 에러 처리 로직을 추가.
      alert("업체 정보 등록에 실패하였습니다.");
    }
  };

  const handleCancel = () => {
    setFormData(initFormData);
    // history.goBack();
  };
  return (
    <AddContainer>
      <AddHeader formData={formData} setFormData={setFormData} />
      <AddImg formData={formData} setFormData={setFormData} />
      <AddEplanation formData={formData} setFormData={setFormData} />
      <StoreInfoWrap>
        <AddInfo formData={formData} setFormData={setFormData} />
        <AddMenu formData={formData} setFormData={setFormData} />
      </StoreInfoWrap>
      <AddBtnWrap>
        <Button onClick={postFormData}>업체 등록</Button>
        <Button onClick={handleCancel}>취소</Button>
      </AddBtnWrap>
    </AddContainer>
  );
};

export default AddStore;
