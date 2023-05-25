import { useState, useEffect } from "react";
import styled from "styled-components";
const AddInfoTagWrap = styled.div`
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
  width: 1200px;
  padding: 0 8px;
  border-radius: 6px;
  margin-bottom: 6px;

  &:focus-within {
    box-shadow: 0 0 0 1px var(--eatsgreen);
  }
  form {
    width: 1200px;
    display: flex;
    flex-direction: row;
    align-items: center;

    .enterTag {
      font-size: var(--x-large-font);
      color: var(--eatsgreen);
    }
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
  const initialTags = formData.tagRestaurants
    ? formData.tagRestaurants.map((tagRestaurant) => tagRestaurant.tag)
    : [];
  const [tag, setTag] = useState(initialTags);
  const [tagInputValue, setTagInputValue] = useState("");
  useEffect(() => {
    const updatedTags = formData.tagRestaurants
      ? formData.tagRestaurants.map((tagRestaurant) => tagRestaurant.tag)
      : [];
    setTag(updatedTags);
  }, [formData.tagRestaurants]);

  const handleTagInputChange = (event) => {
    setTagInputValue(event.target.value);
  };

  const handleAddTag = (event) => {
    event.preventDefault();
    const trimmedValue = tagInputValue.trim();
    if (trimmedValue !== "") {
      const isDuplicateTag = tag.some((tag) => tag.name === trimmedValue);
      if (isDuplicateTag) {
        alert("이미 입력된 태그입니다.");
        return;
      }

      const newTag = { name: trimmedValue };
      setTag([...tag, newTag]);
      onAddTag(newTag);
      setTagInputValue("");
    }
  };
  const removeTag = async (nameToRemove) => {
    const updatedTags = tag.filter((tag) => tag.name !== nameToRemove);
    setTag(updatedTags);
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
        <div className="enterTag">(태그입력 후 Enter를 눌러 추가해주세요)</div>
      </form>
    </AddInfoTagWrap>
  );
};
export default AddTagsInput;
