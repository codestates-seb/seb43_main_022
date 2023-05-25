import { useState, useEffect } from "react";
import styled from "styled-components";
import { IoMdImages } from "react-icons/io";
const AddImgWrap = styled.form`
  width: 100%;
  height: 220px;
  border-radius: 20px;
  background-color: var(--black-100);
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  > .AddImgIcon {
    width: 200px;
    height: 200px;
    color: var(--black-200);
  }

  > .signup-image-label {
    /* border: 1px solid var(--eatsgreen); */
    box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.3);
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 10px 20px;
    border-radius: 10px;
    font-weight: bold;
    font-size: var(--medium-font);
    color: var(--black-800);
    background-color: rgba(255, 255, 255, 0.8);
    display: inline-block;
    cursor: pointer;
    :hover {
      background-color: var(--eatsgreen);
      color: var(--white);
    }
  }
  input[type="file"] {
    display: none;
  }
`;
const AddImgArea = styled.img`
  width: 100%;
  height: 220px;
  object-fit: cover;
  border-radius: 20px;
`;
const ButtonContainer = styled.div`
  position: absolute;
  right: 10px;
  top: 10px;
  button {
    border: none;
    padding: 10px 14px;
    border-radius: 50%;
    font-weight: bold;
    font-size: var(--small-font);
    cursor: pointer;
    outline: none;
  }

  .delete-button {
    background-color: var(--black-600);
    color: white;
  }
`;
const AddImg = ({ formData, setFormData }) => {
  const [imgFile, setImgFile] = useState("");
  const [isUploaded, setIsUploaded] = useState(false);
  useEffect(() => {
    if (formData.image) {
      setImgFile(formData.image);
      setIsUploaded(true);
    }
  }, [formData.image]);
  const handleImageChange = (e) => {
    const selectedImage = e.target.files[0];
    const reader = new FileReader();
    reader.onloadend = () => {
      setImgFile(reader.result);
      setFormData({
        ...formData,
        imageName: selectedImage.name,
        base64Image: reader.result,
      });
      setIsUploaded(true);
    };

    reader.readAsDataURL(selectedImage);
  };
  const handleDelete = () => {
    setImgFile("");
    setIsUploaded(false);
    setFormData({
      ...formData,
      image: null,
      imageName: null,
    });
  };
  return (
    <AddImgWrap>
      {imgFile ? (
        <AddImgArea src={imgFile} alt="shop image" />
      ) : (
        <IoMdImages className="AddImgIcon" />
      )}

      {isUploaded && (
        <ButtonContainer>
          <button className="delete-button" onClick={handleDelete}>
            X
          </button>
        </ButtonContainer>
      )}
      <label className="signup-image-label" htmlFor="image">
        {isUploaded ? "이미지 변경" : "이미지 등록"}
      </label>
      <input
        type="file"
        accept="image/*"
        id="image"
        onChange={handleImageChange}
      />
    </AddImgWrap>
  );
};

export default AddImg;
