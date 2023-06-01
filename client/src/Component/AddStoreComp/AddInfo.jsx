import { useState, useEffect } from "react";
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
  border-radius: 10px;
`;
const SelectCategory = styled.select`
  width: 100%;
  box-sizing: border-box;
  padding: 20px;
  border: 1px solid #ddd;
  font-size: var(--medium-font);
  margin-top: 5px;
  margin-bottom: 10px;
  border-radius: 10px;
`;

const AddrSearchBtn = styled.button`
  width: 20%;
  height: 60px;
  background-color: var(--black-100);
  border: none;
  border-radius: 4px;
  padding: 10px;
  margin: 5px 0 10px 10px;
  font-size: var(--medium-font);
  cursor: pointer;

  &:hover {
    background-color: var(--eatsgreen);
    color: var(--white);
  }
`;

const AddInfo = ({ formData, setFormData }) => {
  const [, setLatitude] = useState(null);
  const [, setLongitude] = useState(null);
  const [tel, setTel] = useState(formData.tel || "");

  /** 가게 전화번호 입력 */
  useEffect(() => {
    setTel(formData.tel || "");
  }, [formData.tel]);

  const formatTelNumber = (value) => {
    value = value.replace(/-/g, "");
    if (value.startsWith("02")) {
      if (value.length <= 2) {
        return value;
      } else if (value.length <= 6) {
        return value.replace(/(02)(\d{1,4})/, "$1-$2");
      } else {
        return value.replace(/(02)(\d{4})(\d{1,4})/, "$1-$2-$3");
      }
    } else if (value.length <= 3) {
      return value;
    } else if (value.length <= 6) {
      return value.replace(/(\d{3})(\d{1,3})/, "$1-$2");
    } else if (value.length === 8) {
      return value.replace(/(\d{4})(\d{4})/, "$1-$2");
    } else if (value.length <= 10) {
      return value.replace(/(\d{3})(\d{3})(\d{1,4})/, "$1-$2-$3");
    } else if (value.length <= 13) {
      return value.replace(/(\d{3})(\d{4})(\d{1,4})/, "$1-$2-$3");
    }
  };

  const handleTelChange = (e) => {
    let { value } = e.target;
    value = formatTelNumber(value);
    setTel(value);
    setFormData({ ...formData, tel: value });
  };

  useEffect(() => {
    const script = document.createElement("script");
    script.src =
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js?";
    script.async = true;
    document.body.appendChild(script);
  }, []);

  useEffect(() => {
    const script = document.createElement("script");
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?appkey=e7cd6dd18a2a66eecf9793219827c987&libraries=services";
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
        if (window.kakao && window.kakao.maps) {
          const geocoder = new window.kakao.maps.services.Geocoder();
          geocoder.addressSearch(data.roadAddress, function (result, status) {
            if (status === window.kakao.maps.services.Status.OK) {
              setLatitude(result[0].y);
              setLongitude(result[0].x);
              setFormData({
                ...formData,
                streetAddress: data.roadAddress,
                latitude: result[0].y,
                longitude: result[0].x,
              });
            } else {
              console.error("Error occurred while searching address: ", status);
              alert("주소 검색 중 오류가 발생했습니다. 다시 시도해주세요.");
            }
          });
        }
      },
    }).open();
  };
  const category = [
    "한식",
    "양식",
    "일식",
    "중식",
    "베트남 요리",
    "인도 요리",
    "분식",
    "후식",
    "퓨전요리",
    "채식",
    "해물",
    "고기",
  ];

  const onInputCategoryChange = (e) => {
    const { name, value } = e.target;

    setFormData({ ...formData, [name]: value });
  };
  return (
    <AddInfoWrap>
      <label htmlFor="streetAddress">주소</label>
      <LocationWrap>
        <InfoInput
          name="streetAddress"
          value={formData.streetAddress || ""}
          onChange={onInputChange}
          type="text"
          placeholder="주소 검색을 통해 주소를 입력해주세요"
          maxLength="200"
          readOnly
        />
        <AddrSearchBtn onClick={onSearchAddr}>주소 검색</AddrSearchBtn>
      </LocationWrap>
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
        value={tel}
        onChange={handleTelChange}
        type="text"
        placeholder="가게 전화번호를 입력하세요"
        maxLength="13"
      />
      <label htmlFor="category">카테고리</label>
      <SelectCategory
        name="category"
        value={formData.category || ""}
        onChange={onInputCategoryChange}
      >
        <option value="">가게 카테고리를 선택해주세요</option>
        {category.map((category, index) => (
          <option key={index} value={category}>
            {category}
          </option>
        ))}
      </SelectCategory>
      <label htmlFor="open_time">영업시간</label>
      <InfoInput
        name="open_time"
        value={formData.open_time || ""}
        onChange={onInputChange}
        type="text"
        placeholder="가게 영업 시간을 입력하세요"
        maxLength="100"
      />
    </AddInfoWrap>
  );
};

export default AddInfo;
