import React from "react";
import styled from "styled-components";

const AddInfoWrap = styled.div`
  width: calc(50% - 25px);
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 20px;
`;

const InfoInput = styled.input`
  width: 100%;
  box-sizing: border-box;
  padding: 20px;
  border: 1px solid #ddd;
  font-size: var(--medium-font);
  margin-top: 5px;
  margin-bottom: 10px;
`;

const AddInfo = ({ formData, setFormData }) => {
  const onInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  return (
    <AddInfoWrap>
      <label htmlFor="location">주소</label>
      <InfoInput
        name="location"
        value={formData.location || ""}
        onChange={onInputChange}
        type="text"
        placeholder="가게 주소를 입력하세요"
        maxLength="200"
      />
      <label htmlFor="storeNumber">전화번호</label>
      <InfoInput
        name="storeNumber"
        value={formData.storeNumber || ""}
        onChange={onInputChange}
        type="text"
        placeholder="가게 전화번호를 입력하세요"
        maxLength="100"
      />
      <label htmlFor="foodType">음식종류</label>
      <InfoInput
        name="foodType"
        value={formData.foodType || ""}
        onChange={onInputChange}
        type="text"
        placeholder="음식 종류를 입력하세요"
        maxLength="100"
      />
      <label htmlFor="businessHours">영업시간</label>
      <InfoInput
        name="businessHours"
        value={formData.businessHours || ""}
        onChange={onInputChange}
        type="text"
        placeholder="가게 영업 시간을 입력하세요"
        maxLength="100"
      />
    </AddInfoWrap>
  );
};

export default AddInfo;
