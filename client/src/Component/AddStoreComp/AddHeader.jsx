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
    setFormData({
      ...formData,
      tag: [...(formData.tag || []), newTag],
    });
  };

  return (
    <AddHeaderArea>
      <StoreTitle
        type="text"
        name="restaurantName"
        value={formData.restaurantName || ""}
        onChange={onInputChange}
        placeholder="이곳에 가게 이름을 입력해주세요!"
      />
      <AddTagsInput onAddTag={onAddTag} formData={formData} />
    </AddHeaderArea>
  );
};

export default AddHeader;
