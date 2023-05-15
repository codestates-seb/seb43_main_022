import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import AddHeader from "../Component/AddStoreComp/AddHeader";
import AddImg from "../Component/AddStoreComp/AddImg";
import AddEplanation from "../Component/AddStoreComp/AddExplanation";
import AddInfo from "../Component/AddStoreComp/AddInfo";
import AddMenu from "../Component/AddStoreComp/AddMenu";
import Button from "../Component/style/StyleButton";

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
// const { id } = useParams(); // URL 파라미터에서 업체 ID를 가져옴
// 서버에서 업체 정보를 가져옴 http://localhost:4000/restaurants/${id}
const EditStore = () => {
  const history = useNavigate();
  const initFormData = {
    name: "",
    tag: [],
    photoUrl: null,
    content: "",
    tel: "",
    category: "",
    openTime: "",
    menuList: [{ menu: "", price: "" }],
    streetAddress: "",
    detailAddress: "",
  };
  const [formData, setFormData] = useState(initFormData);

  useEffect(() => {
    const fetchRestaurant = async () => {
      try {
        const response = await axios.get(`http://localhost:4000/restaurants/2`);
        setFormData(response.data);
        console.log("페이지렌더링시 저장된 데이터 : ", response.data);
      } catch (error) {
        console.error(error);
        alert("업체 정보를 가져오는데 실패하였습니다.");
      }
    };
    fetchRestaurant();
  }, []);

  const patchFormData = async () => {
    try {
      await axios.patch(`http://localhost:4000/restaurants/2`, formData);
      console.log(formData);
      alert("업체 정보가 수정되었습니다.");
    } catch (error) {
      console.error(error);
      alert("업체 정보 수정에 실패하였습니다.");
    }
  };
  const deleteFormData = async () => {
    try {
      await axios.delete(`http://localhost:4000/restaurants/2`);
      alert("업체 정보가 삭제되었습니다.");
      history(-1);
    } catch (error) {
      console.error(error);
      alert("업체 정보 삭제에 실패하였습니다.");
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
        <Button onClick={deleteFormData}>업체 삭제</Button>
        <Button onClick={patchFormData}>수정 완료</Button>
        <Button onClick={handleCancel}>취소</Button>
      </AddBtnWrap>
    </AddContainer>
  );
};

export default EditStore;
