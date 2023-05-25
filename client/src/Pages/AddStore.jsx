import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import AddHeader from "../Component/AddStoreComp/AddHeader";
import AddImg from "../Component/AddStoreComp/AddImg";
import AddEplanation from "../Component/AddStoreComp/AddExplanation";
import AddInfo from "../Component/AddStoreComp/AddInfo";
import AddMenu from "../Component/AddStoreComp/AddMenu";
import Button from "../Component/style/StyleButton";
import { api } from "../Util/api";
const AddContainer = styled.div`
  box-sizing: border-box;
  padding: 60px 0;
  width: 1200px;
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
  const history = useNavigate();
  const initFormData = [
    {
      restaurantName: "",
      tag: [],
      imageName: null,
      base64Image: null,
      content: "",
      tel: "",
      category: "",
      open_time: "",
      menu: [],
      streetAddress: "",
      detailAddress: "",
      latitude: "",
      longitude: "",
    },
  ];
  const [formData, setFormData] = useState(initFormData);

  const postFormData = async () => {
    try {
      await api.post("/restaurants", formData);

      alert("업체 정보가 등록되었습니다.");
      setFormData(initFormData);
      history("/");
    } catch (error) {
      console.error(error);
      alert("모든 내용을 입력했는지 확인해주세요.");
    }
  };

  const handleCancel = () => {
    setFormData(initFormData);
    history(-1);
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
