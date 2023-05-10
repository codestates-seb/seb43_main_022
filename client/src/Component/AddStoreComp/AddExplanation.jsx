import React from "react";
import styled from "styled-components";

const AddInfoWrap = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  label {
    display: none;
  }
`;

const AddInfoTextarea = styled.textarea`
  width: 100%;
  min-height: 140px;
  resize: none; /* Add this line to prevent resizing */
  box-sizing: border-box;
  padding: 20px;
  border: 1px solid #ddd;
  font-size: var(--medium-font);
  display: inline-block; /* Add this line */
  vertical-align: top; /* Add this line */
`;

const AddExplanation = ({ formData, setFormData }) => {
  const onInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  return (
    <AddInfoWrap>
      <label htmlFor="storeIntroduction">Store Introduction:</label>
      <AddInfoTextarea
        name="storeIntroduction"
        value={formData.storeIntroduction || ""}
        onChange={onInputChange}
        type="text"
        placeholder="가게 소개글을 입력해주세요"
        maxLength="1000"
      />
    </AddInfoWrap>
  );
};

export default AddExplanation;
