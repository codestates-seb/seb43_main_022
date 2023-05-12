import { useState } from "react";
import styled from "styled-components";
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
const AddTagsInput = ({ onAddTag }) => {
  const [tags, setTags] = useState([]);
  const [tagInputValue, setTagInputValue] = useState("");

  const handleTagInputChange = (event) => {
    setTagInputValue(event.target.value);
  };

  const handleAddTag = (event) => {
    event.preventDefault();
    const trimmedValue = tagInputValue.trim();
    if (trimmedValue !== "") {
      setTags([...tags, trimmedValue]);
      onAddTag(trimmedValue);
      setTagInputValue("");
    }
  };
  const removeTag = (indexToRemove) => {
    setTags(tags.filter((tag, index) => index !== indexToRemove));
  };
  return (
    <AddInfoTagWrap>
      <TagUl>
        {tags.map((tag, index) => (
          <TagLi key={index}>
            <span className="tag-title">{tag}</span>
            <button className="tag-close-icon" onClick={() => removeTag(index)}>
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
