import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
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
  margin-top: 40px;
  width: 100%;
  display: flex;
  justify-content: flex-end;
  gap: 10px;

  & button:first-of-type {
    margin-right: auto;
  }
`;
const EditStore = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const initFormData = {
    restaurantName: "",
    tag: null,
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
  };
  const [formData, setFormData] = useState(initFormData);

  useEffect(() => {
    const fetchRestaurant = async () => {
      try {
        const response = await api.get(`/restaurants/${id}`);
        setFormData(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchRestaurant();
  }, []);

  const patchFormData = async () => {
    try {
      await api.patch(`/restaurants/${id}`, formData);

      window.location.reload();
      alert("업체정보가 수정되었습니다.");
    } catch (error) {
      console.error(error);
      alert("업체정보 수정에 실패하였습니다.");
    }
  };
  const deleteFormData = async () => {
    if (window.confirm("업체정보를 정말 삭제하시겠습니까?")) {
      try {
        await api.delete(`/restaurants/${id}`);
        alert("업체정보가 삭제되었습니다.");
        navigate("/itemlist");
      } catch (error) {
        console.error(error);
        alert("업체정보 삭제에 실패하였습니다.");
      }
    }
  };

  const handleCancel = () => {
    setFormData(initFormData);
    navigate(-1);
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
        <Button onClick={deleteFormData}>업체 삭제</Button>
        <Button onClick={patchFormData}>수정 완료</Button>
        <Button onClick={handleCancel}>취소</Button>
      </AddBtnWrap>
    </AddContainer>
  );
};

export default EditStore;
