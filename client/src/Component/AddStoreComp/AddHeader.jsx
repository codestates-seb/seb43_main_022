// AddHeader.jsx

import React from "react";
import styled from "styled-components";
import AddTagsInput from "./AddTagsInput";

const AddHeaderArea = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
`;
const StoreTitle = styled.input`
  font-size: var(--xx-large-font);
  font-weight: 700;
  padding: 6px;
  color: var(--black-900);
  width: 100%;
  &:focus {
    outline: transparent;
  }
`;

const AddHeader = ({ formData, setFormData }) => {
  const onInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const onAddTag = (newTag) => {
    setFormData({ ...formData, tags: [...formData.tags, newTag] });
  };

  return (
    <AddHeaderArea>
      <StoreTitle
        type="text"
        name="storeName"
        value={formData.storeName || ""}
        onChange={onInputChange}
        placeholder="이곳에 가게 이름을 입력해주세요!"
      />
      <AddTagsInput onAddTag={onAddTag} />
    </AddHeaderArea>
  );
};

export default AddHeader;
