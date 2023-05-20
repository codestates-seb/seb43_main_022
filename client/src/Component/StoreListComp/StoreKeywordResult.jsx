import { useState, useEffect } from "react";
import ReactPaginate from "react-paginate";
import { Title } from "../../Pages/StoreList";
import { useRecoilValue } from "recoil";
import { searchTermState } from "../../state/atoms/SearchTermState";
import ImgBtn from "../style/ImgBtn";
import styled from "styled-components";
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

const StoreCard = styled.li`
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
  > .photoUrl {
    width: 100%;
    position: absolute;
    left: 0;
    top: 0;
    img {
      width: 100%;
      height: 185px;
      object-fit: cover;
      object-position: center;
      border-radius: 30px 30px 0 0;
    }
    > span {
      position: absolute;
      right: 20px;
      top: 20px;
      button {
        background-color: transparent;
      }
    }
  }
  > .category {
    position: absolute;
    top: 20px;
  }

  > .restaurantName {
    font-size: var(--x-large-font);
    height: 22px;
  }
  > .content {
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
  > .Count {
    display: flex;
    flex-direction: row;
    gap: 40px;
    p {
      font-size: var(--medium-font);
    }
  }
  > .tagRestaurants {
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

  .pagination {
    display: flex;
    justify-content: center;
    margin-top: 15px;
  }

  ul {
    list-style: none;
    padding: 0;
  }

  ul.pagination li {
    display: inline-block;
    width: 30px;
    height: 30px;
    border: 1px solid #e2e2e2;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1rem;
    cursor: pointer;
  }

  ul.pagination li:first-child {
    border-radius: 5px 0 0 5px;
  }

  ul.pagination li:last-child {
    border-radius: 0 5px 5px 0;
  }

  ul.pagination li a {
    text-decoration: none;
    color: #337ab7;
    font-size: 1rem;
  }

  ul.pagination li.active a {
    color: white;
  }

  ul.pagination li.active {
    background-color: #337ab7;
  }

  ul.pagination li a:hover,
  ul.pagination li a.active {
    color: blue;
    font-weight: 700;
  }
  .page-selection {
    width: 48px;
    height: 30px;
    color: #337ab7;
  }
`;
const StoreKeywordResult = () => {
  const [stores, setStores] = useState([]);
  const [, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const searchTerm = useRecoilValue(searchTermState);
  const resultsPerPage = 4;
  const [isHeartActive, setIsHeartActive] = useState(false);
  const [currentFilter, setCurrentFilter] = useState("createdAt");

  const filterByLatest = (a, b) => {
    const dateA = new Date(a.createdAt);
    const dateB = new Date(b.createdAt);
    return dateB - dateA;
  };

  const filterByReview = (a, b) => {
    return b.total_review - a.total_review;
  };

  const filterByFavorites = (a, b) => {
    return b.total_favorite - a.total_favorite;
  };

  useEffect(() => {
    const fetchStores = async () => {
      try {
        const response = await api.get(
          // `/restaurants/search?keyword=${searchTerm}`,
          `/restaurants`,
        );
        let data = response.data;

        if (data.length === 0) {
          const allStoresResponse = await api.get("/restaurants");
          data = allStoresResponse.data;
        }

        if (currentFilter === "createdAt") {
          data.sort(filterByLatest);
        } else if (currentFilter === "total_review") {
          data.sort(filterByReview);
        } else if (currentFilter === "total_favorite") {
          data.sort(filterByFavorites);
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
  }, [searchTerm, currentFilter]);

  const handlePageClick = ({ selected: selectedPage }) => {
    setCurrentPage(selectedPage);
  };

  const offset = currentPage * resultsPerPage;

  const handleClick = () => {
    setIsHeartActive(!isHeartActive);
  };
  const currentPageData = stores
    .slice(offset, offset + resultsPerPage)
    .map((store, restaurantId) => (
      <StoreCard key={restaurantId}>
        <div className="photoUrl">
          {store.photoUrl ? (
            <img src={store.photoUrl} alt={store.restaurantName} />
          ) : (
            "등록된 이미지가 없습니다."
          )}
          {store.isFavorite === false ? (
            <span>
              <ImgBtn imgstyle="Heart" onClick={handleClick} />
            </span>
          ) : (
            <span>
              <ImgBtn imgstyle="Heart" />
            </span>
          )}
        </div>
        <p className="category">{store.category}</p>
        <h2 className="restaurantName">{store.restaurantName}</h2>
        <p className="content">{store.content}</p>
        <div className="Count">
          <p>즐겨찾기 : {store.total_favorite}</p>
          <p>리뷰 : {store.total_review}</p>
        </div>
        <div className="tagRestaurants">
          {store.tagRestaurants.map((tag, index) => (
            <button key={index} className="tagButton">
              {tag.tag.name}
            </button>
          ))}
        </div>
      </StoreCard>
    ));

  const pageCount = Math.ceil(stores.length / resultsPerPage);

  return (
    <>
      <StoreListBox>
        <Title>키워드 검색결과</Title>
        <ResultFilter>
          <Total>총 {stores.length}개의 검색결과가 있습니다!</Total>
          <FilterUl>
            <FilterLi onClick={() => setCurrentFilter("createdAt")}>
              최신순
            </FilterLi>
            <FilterLi onClick={() => setCurrentFilter("total_review")}>
              리뷰순
            </FilterLi>
            <FilterLi onClick={() => setCurrentFilter("total_favorite")}>
              즐겨찾기순
            </FilterLi>
          </FilterUl>
        </ResultFilter>
        <ResultList>{currentPageData}</ResultList>
        <PaginationWrap>
          <ReactPaginate
            previousLabel={"←"}
            nextLabel={"→"}
            pageCount={pageCount}
            onPageChange={handlePageClick}
            containerClassName={"pagination"}
            previousLinkClassName={"pagination__link"}
            nextLinkClassName={"pagination__link"}
            disabledClassName={"pagination__link--disabled"}
            activeClassName={"pagination__link--active"}
          />
        </PaginationWrap>
      </StoreListBox>
    </>
  );
};

export default StoreKeywordResult;
