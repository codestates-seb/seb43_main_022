import React from "react";
import styled from "styled-components";
import ImgBtn from "../style/ImgBtn";
import { useState } from "react";
// import { useRecoilState } from "recoil";
// import {
//   shareIconState,
//   heartIconState,
//   //useRecoilValue
// // storeNameState,
// //   storeTagsState,
// //   viewCountState,
// //   heartCountState,
// } from "../../state/atoms/detailInfoAtom";

const Container = styled.div`
  margin: auto;
  width: 1200px;
`;

const StoreName = styled.div`
  font-size: var(--xx-large-font);
`;

const StoreInfo = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

const Tags = styled.div`
  font-size: var(--x-large-font);
  color: var(--black-350);
  display: flex;
  flex-direction: row;
  margin-top: 10px;
`;
const TagItem = styled(Tags)`
  margin-right: 10px;
`;

const SubInfo = styled.div`
  & > * {
    margin-right: 20px;
    /* "& > *" 는 SubInfo 컴포넌트의 모든 자식 요소를 선택하는 선택자 */
  }
  > span {
    font-size: var(--x-large-font);
    color: var(--black-200);
  }
`;

const StoreHead = () => {
  // const storeName = useRecoilValue(storeNameState);
  // const storeTags = useRecoilValue(storeTagsState);
  // const viewCount = useRecoilValue(viewCountState);
  // const heartCount = useRecoilValue(heartCountState);

  const [heartIcon, setHeartIcon] = useState(false);
  const [shareIcon, setShareIcon] = useState(false);

  // const [storeData, setStoreData] = useState({
  //   storeName: "",
  //   storeTags: [],
  //   viewCount: "",
  //   heartCount: "",
  // });

  const dumyData = {
    storeName: "오프마인드",
    storeTags: ["#브런치", "#샌드위치", "#분위기좋은곳"],
    viewCount: "1.2k",
    heartCount: "302",
  };

  const handleHeartIcon = () => {
    setHeartIcon(!heartIcon);
  };
  const handleShareIcon = () => {
    setShareIcon(!shareIcon);
  };

  return (
    <div>
      <Container>
        <StoreName>{dumyData.storeName}</StoreName>
        <StoreInfo>
          <Tags>
            {dumyData.storeTags.map((tag, index) => (
              <TagItem key={index}>{tag}</TagItem>
            ))}
          </Tags>
          <SubInfo>
            <ImgBtn imgstyle={"View"} />
            <span>{dumyData.viewCount}</span>
            <ImgBtn imgstyle={"Heart"} onClick={handleHeartIcon} />
            <span>{dumyData.heartCount}</span>
            <ImgBtn imgstyle={"Share"} onClick={handleShareIcon} />
          </SubInfo>
        </StoreInfo>
      </Container>
    </div>
  );
};

export default StoreHead;
