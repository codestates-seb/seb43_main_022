import { useState, useEffect } from "react";
import { Title } from "../../Pages/StoreList";
// import { useRecoilState } from "recoil";
// import { storesState } from "../../state/atoms/keywordsAtom";
import { useRecoilValue } from "recoil";
import { searchTermState } from "../../state/atoms/SearchTermState";
import ImgBtn from "../style/ImgBtn";
import styled from "styled-components";
import {
  MdKeyboardArrowLeft,
  MdKeyboardArrowRight,
  MdKeyboardDoubleArrowLeft,
  MdKeyboardDoubleArrowRight,
} from "react-icons/md";
import { api } from "../../Util/api";
const StoreListBox = styled.div`
  width: calc(100% - 400px);
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  align-content: flex-start;
`;

const ResultFilter = styled.div`
  width: calc(100%);
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-bottom: 40px;
`;
const Total = styled.div`
  font-size: var(--medium-font);
  flex-basis: calc(50% - 10px);
  color: var(--black-700);
  margin-right: 10px;
`;
const FilterUl = styled.ul`
  flex-basis: calc(50% - 10px);

  display: flex;
  justify-content: flex-end;
  margin-right: 16px;
`;
const FilterLi = styled.li`
  list-style: none;
  font-size: var(--medium-font);
  color: var(--black-700);
  cursor: pointer;
  ::before {
    content: "";
    display: inline-block;
    width: 1px;
    height: 11px;
    margin: 0 15px;
    background-color: var(--black-350);
    vertical-align: -1px;
  }
  :first-child::before {
    display: none;
  }
  :hover {
    color: var(--eatsgreen);
  }
  &.active {
    color: var(--eatsgreen);
  }
`;
const ResultList = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  width: calc(100%);
  gap: 20px;
`;
const StoreCard = styled.div`
  width: 100%;
  max-width: calc(100% / 2 - 10px);
  border-radius: 30px;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: flex-start;
  padding: 215px 24px 22px;
  gap: 20px;
  border: 1px solid var(--black-100);
  position: relative;
  > img {
    width: calc(100%);
    height: 185px;
    border-radius: 30px 30px 0 0;
    position: absolute;
    left: 0;
    top: 0;
  }
  > span {
    position: absolute;
    right: 20px;
    top: 20px;
    button {
      background-color: transparent;
    }
  }
  > h3 {
    font-size: var(--x-large-font);
    height: 22px;
  }
  > div.filterIntro {
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
    line-height: 1.5;
    height: 3em;
    word-wrap: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    font-size: var(--medium-font);
    color: var(--black-700);
  }
  > div.filterCount {
    display: flex;
    flex-direction: row;
    gap: 40px;
    > p {
      font-size: var(--medium-font);
    }
  }
  > div.cardTag {
    display: flex;
    gap: 6px;
    > button {
      width: auto;
      height: 33px;
      border-radius: 30px;
      font-size: 14px;
      color: var(--eatsgreen);
      border: none;
      padding: 0 20px;
      background: rgba(206, 242, 220, 1);
    }
  }
`;
const PaginationWrap = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  margin: 30px 0;
`;
const PageNumber = styled.button`
  background-color: ${({ isActive, disabled }) =>
    disabled ? "transparent" : isActive ? "var(--eatsgreen)" : "transparent"};
  color: ${({ isActive, disabled }) =>
    disabled ? "var(--white)" : isActive ? "white" : "var(--black-500)"};
  font-weight: ${({ isActive }) => (isActive ? "600" : "400")};
  width: 30px;
  height: 30px;
  margin: 2px;
  border-radius: 4px;
  cursor: ${({ disabled }) => (disabled ? "not-allowed" : "pointer")};
  svg {
    width: 1.2em;
    height: 1.2em;
    margin-top: 4px;
  }
  :hover {
    color: ${({ disabled, isActive }) =>
      disabled ? "transparent" : isActive ? "" : "var(--black-500)"};
  }
`;

const StoreCardComponent = ({ store }) => {
  return (
    <StoreCard>
      <img src={store.photoUrl} alt={store.restaurantName} />
      <span>
        {store.totalFavorite > 0 ? <ImgBtn imgstyle="Heart" /> : null}
      </span>
      <h3>{store.restaurantName}</h3>
      <div className="filterIntro">{store.content}</div>
      <div className="filterCount">
        <p>리뷰 수: {store.total_reviews}</p>
        <p>즐겨찾기 수: {store.totalFavorite}</p>
      </div>
      <div className="cardTag">
        {store.tagRestaurants.map((tag, index) => (
          <button key={index}>{tag.tag.name}</button>
        ))}
        {store.category}
      </div>
    </StoreCard>
  );
};
const StoreKeywordResult = () => {
  // const [stores] = useRecoilState(storesState);
  const [stores, setStores] = useState([]);
  // eslint-disable-next-line no-unused-vars
  const [loading, setLoading] = useState(true);
  const searchTerm = useRecoilValue(searchTermState);
  useEffect(() => {
    const fetchStores = async () => {
      try {
        const response = await api.get(
          `/restaurants/search?keyword=${searchTerm}`,
        );
        let data = response.data;

        if (data.length === 0) {
          const allStoresResponse = await api.get("/restaurants");
          data = allStoresResponse.data;
        }
        console.log(response.data);

        setStores(data);
        setLoading(false);
      } catch (error) {
        console.error("Error fetching data: ", error);
        setLoading(false);
      }
    };

    fetchStores();
  }, [searchTerm]);

  // useEffect(() => {
  //   setFilteredResults(stores);
  // }, [stores]);

  // 필터 아이템
  const [activeFilter, setActiveFilter] = useState("newest");
  const [filteredResults, setFilteredResults] = useState([]);
  // 페이지네이션
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 4;
  // 페이지네이션 관련 코드
  const totalPages = Math.ceil(filteredResults.length / itemsPerPage);
  const handleClickPage = (pageNumber) => {
    setCurrentPage(pageNumber);
  };
  const displayPagination = () => {
    const pages = [];
    const startPage =
      currentPage <= 3 ? 1 : Math.min(totalPages - 4, currentPage - 2);
    const endPage =
      currentPage <= 3
        ? Math.min(5, totalPages)
        : Math.min(totalPages, currentPage + 2);
    const createPageBtn = (key, pageNumber, content, disabled = false) => (
      <PageNumber
        key={key}
        onClick={() => handleClickPage(pageNumber)}
        isActive={pageNumber === currentPage}
        disabled={disabled}
      >
        {content}
      </PageNumber>
    );

    pages.push(
      createPageBtn(
        "first",
        1,
        <MdKeyboardDoubleArrowLeft />,
        currentPage === 1,
      ),
    );
    pages.push(
      createPageBtn(
        "prev",
        Math.max(1, currentPage - 1),
        <MdKeyboardArrowLeft />,
        currentPage === 1,
      ),
    );

    for (let i = startPage; i <= endPage; i++) {
      pages.push(createPageBtn(i, i, i, false));
    }

    pages.push(
      createPageBtn(
        "next",
        Math.min(totalPages, currentPage + 1),
        <MdKeyboardArrowRight />,
        currentPage === totalPages,
      ),
    );
    pages.push(
      createPageBtn(
        "last",
        totalPages,
        <MdKeyboardDoubleArrowRight />,
        currentPage === totalPages,
      ),
    );

    return pages;
  };
  // 최신순,리뷰순, 즐겨찾기순 필터
  const handleFilterClick = (filter) => {
    setActiveFilter(filter);
    const sortedStores = sortStoresByFilter(filteredResults, filter);
    setFilteredResults(sortedStores);
  };
  //이곳
  const sortStoresByFilter = (stores, filter) => {
    if (stores.length === 0) {
      return stores;
    }

    const copiedStores = [...stores];

    if (filter === "newest") {
      return copiedStores.sort(
        (a, b) => new Date(b.createdDate) - new Date(a.createdDate),
      );
    } else if (filter === "reviews") {
      return copiedStores.sort((a, b) => b.reviews - a.reviews);
    } else if (filter === "favorites") {
      return copiedStores.sort((a, b) => b.favorites - a.favorites);
    } else {
      return copiedStores;
    }
  };
  useEffect(() => {
    handleFilterClick(activeFilter);
  }, [stores, activeFilter]);

  return (
    <>
      <StoreListBox>
        <Title>키워드 검색결과</Title>
        <ResultFilter>
          <Total>총 {filteredResults.length}개의 검색결과가 있습니다!</Total>
          <FilterUl>
            <FilterLi
              onClick={() => handleFilterClick("newest")}
              className={activeFilter === "newest" ? "active" : ""}
            >
              최신순
            </FilterLi>
            <FilterLi
              onClick={() => handleFilterClick("reviews")}
              className={activeFilter === "reviews" ? "active" : ""}
            >
              리뷰순
            </FilterLi>
            <FilterLi
              onClick={() => handleFilterClick("favorites")}
              className={activeFilter === "favorites" ? "active" : ""}
            >
              즐겨찾기순
            </FilterLi>
          </FilterUl>
        </ResultFilter>
        <ResultList>
          {filteredResults
            .slice((currentPage - 1) * itemsPerPage, currentPage * itemsPerPage)
            .map((store) => (
              <StoreCardComponent
                key={store.restaurantId}
                store={store}
                restaurantId={store.restaurantId}
              />
            ))}
        </ResultList>
        <PaginationWrap>{displayPagination()}</PaginationWrap>
      </StoreListBox>
    </>
  );
};

export default StoreKeywordResult;
