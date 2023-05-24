import styled from "styled-components";
import StoreKeywordSearch from "../Component/StoreListComp/StoreKeywordSearch";
import StoreKeywordResult from "../Component/StoreListComp/StoreKeywordResult";
import StoreMap from "../Component/StoreListComp/StoreMap";
import { useRecoilValue } from "recoil";
import { IsLoadingState } from "../state/atoms/IsLoadingAtom";
import Loading from "../Component/Loading";
const StoreListWrap = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 30px;
  width: 1200px;
  margin: auto;
`;
const StoreMainWrap = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-top: 40px;
`;
export const Title = styled.h2`
  font-size: var(--x-large-font);
  font-weight: 800;
  margin-bottom: 16px;
  flex-basis: 100%;
`;
const StoreList = () => {
  const isLoading = useRecoilValue(IsLoadingState);

  return (
    <>
      {isLoading ? (
        <Loading />
      ) : (
        <StoreListWrap>
          <StoreKeywordSearch />
          <StoreMainWrap>
            <StoreKeywordResult />
            <StoreMap />
          </StoreMainWrap>
        </StoreListWrap>
      )}
    </>
  );
};

export default StoreList;
