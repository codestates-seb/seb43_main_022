import { useEffect } from "react";
import styled from "styled-components";

const AddInfoWrap = styled.div`
  width: calc(50% - 25px);
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-top: 20px;
`;
const LocationWrap = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
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

const AddrSearchBtn = styled.button`
  width: 20%;
  height: 60px;
  background-color: #eee;
  border: none;
  border-radius: 4px;
  padding: 10px;
  margin: 5px 0 10px 10px;
  font-size: var(--small-font);
  cursor: pointer;

  &:hover {
    background-color: #ddd;
  }
`;

const AddInfo = ({ formData, setFormData }) => {
  useEffect(() => {
    const script = document.createElement("script");
    script.src =
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js?";
    script.async = true;
    document.body.appendChild(script);
  }, []);

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const onSearchAddr = () => {
    new window.daum.Postcode({
      oncomplete: function (data) {
        setFormData({
          ...formData,
          location: data.jibunAddress,
          zonecode: data.zonecode,
        });
      },
    }).open();
  };

  return (
    <AddInfoWrap>
      <label htmlFor="location">주소</label>
      <LocationWrap>
        <InfoInput
          name="location"
          value={formData.location || ""}
          onChange={onInputChange}
          type="text"
          placeholder="가게 주소를 입력하세요"
          maxLength="200"
        />
        <AddrSearchBtn onClick={onSearchAddr}>우편번호 검색</AddrSearchBtn>
      </LocationWrap>
      <InfoInput
        name="zonecode"
        value={formData.zonecode || ""}
        onChange={onInputChange}
        type="hidden"
        maxLength="100"
      />
      {console.log(formData)}
      <label htmlFor="detailAddress">상세주소</label>
      <InfoInput
        name="detailAddress"
        value={formData.detailAddress || ""}
        onChange={onInputChange}
        type="text"
        placeholder="상세주소를 입력하세요"
        maxLength="100"
      />
      <label htmlFor="tel">전화번호</label>
      <InfoInput
        name="tel"
        value={formData.tel || ""}
        onChange={onInputChange}
        type="text"
        placeholder="가게 전화번호를 입력하세요"
        maxLength="100"
      />
      <label htmlFor="category">음식종류</label>
      <InfoInput
        name="category"
        value={formData.category || ""}
        onChange={onInputChange}
        type="text"
        placeholder="음식 종류를 입력하세요"
        maxLength="100"
      />
      <label htmlFor="openTime">영업시간</label>
      <InfoInput
        name="openTime"
        value={formData.openTime || ""}
        onChange={onInputChange}
        type="text"
        placeholder="가게 영업 시간을 입력하세요"
        maxLength="100"
      />
    </AddInfoWrap>
  );
};

export default AddInfo;
