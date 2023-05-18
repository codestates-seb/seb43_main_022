import { useState, useEffect } from "react";
// import axios from "axios";
import styled from "styled-components";
import { api } from "../../Util/api";
const AddInfoTagWrap = styled.div`
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  width: 70%;
  padding: 0 8px;
  border-radius: 6px;
  margin-bottom: 6px;

  &:focus-within {
    box-shadow: 0 0 0 1px var(--eatsgreen);
  }
`;
const TagInput = styled.input`
  flex: 1;
  border: none;
  height: 44px;
  font-size: var(--x-large-font);
  padding: 2px 0 0 0;
  &:focus {
    outline: transparent;
  }
`;
const TagUl = styled.ul`
  display: flex;
  flex-wrap: wrap;

  padding: 0;
  margin: 8px 0 0 0;
`;
const TagLi = styled.li`
  width: auto;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--black-600);
  padding: 0 8px;
  font-size: var(--medium-font);
  list-style: none;
  border-radius: 6px;
  margin: 0 8px 8px 0;

  .tag-title {
    font-size: var(--x-large-font);
  }
  .tag-close-icon {
    display: block;
    width: 16px;
    height: 16px;
    line-height: 14px;
    text-align: center;
    font-size: 14px;
    margin-left: 8px;
    background-color: #2f2f2f;
    color: var(--white);
    border-radius: 50%;
    cursor: pointer;
  }
`;
const AddTagsInput = ({ onAddTag, formData }) => {
  const [tag, setTag] = useState(formData.tag || []);
  const [tagInputValue, setTagInputValue] = useState("");

  useEffect(() => {
    setTag(formData.tag || []);
  }, [formData.tag]);

  const handleTagInputChange = (event) => {
    setTagInputValue(event.target.value);
  };

  const handleAddTag = (event) => {
    event.preventDefault();
    const trimmedValue = tagInputValue.trim();
    if (trimmedValue !== "") {
      setTag([...tag, { name: trimmedValue }]);
      onAddTag({ name: trimmedValue });
      setTagInputValue("");
    }
  };
  const removeTag = async (nameToRemove) => {
    // 로컬 상태 업데이트
    const updatedTags = tag.filter((tag) => tag.name !== nameToRemove);
    setTag(updatedTags);
    console.log(updatedTags);
    // 서버에 PATCH 요청
    try {
      await api.patch("/restaurants/1", {
        tag: updatedTags,
      });
    } catch (error) {
      console.error("태그 업데이트에 실패했습니다:", error);
    }
  };
  return (
    <AddInfoTagWrap>
      <TagUl>
        {tag.map((tag, index) => (
          <TagLi key={index}>
            <span className="tag-title">#{tag.name}</span>
            <button
              className="tag-close-icon"
              onClick={() => removeTag(tag.name)}
            >
              x
            </button>
          </TagLi>
        ))}
      </TagUl>
      <form onSubmit={handleAddTag}>
        <TagInput
          type="text"
          value={tagInputValue}
          onChange={handleTagInputChange}
          placeholder="태그를 입력해주세요."
        />
      </form>
    </AddInfoTagWrap>
  );
};
export default AddTagsInput;
