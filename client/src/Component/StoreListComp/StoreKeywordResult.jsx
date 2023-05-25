import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import { Title } from "../../Pages/StoreList";
import { api } from "../../Util/api";
import ReactPaginate from "react-paginate";
import ImgBtn from "../style/ImgBtn";
import styled from "styled-components";
import { useRecoilValue, useSetRecoilState, useRecoilState } from "recoil";
import {
  searchResultsState,
  searchKeywordState,
} from "../../state/atoms/SearchStateAtom";
import { searchStateTag } from "../../state/atoms/SearchStateTagAtom";
import memberState from "../../state/atoms/SignAtom";

const NoResult = () => <div>검색결과가 없습니다</div>;
const StoreKeywordResult = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const resultsPerPage = 4;
  const [isHeartActive, setIsHeartActive] = useState(false);
  const [currentFilter, setCurrentFilter] = useState("createdAt");
  const [noResult, setNoResult] = useState(false);
  const [userDataFavor, setUserDataFavor] = useState([]);
  const [stores, setStores] = useState([]);
  const [member, setMember] = useRecoilState(memberState);
  const searchKeyword = useRecoilValue(searchKeywordState);

  const results = useRecoilValue(searchResultsState);
  const setSearchResultsState = useSetRecoilState(searchResultsState);

  const searchTagResults = useRecoilValue(searchStateTag);

  const nav = useNavigate();

  useEffect(() => {
    const encodedCategoryName = encodeURIComponent(searchKeyword);
    const fetchData = async () => {
      try {
        const refreshPageData = await api.get(
          `/restaurants/search?keyword=${encodedCategoryName}`,
        );
        setSearchResultsState(refreshPageData.data);

        if (encodedCategoryName) {
          nav(`/itemlist?search=${encodedCategoryName}`);
        }

        setUserDataFavor(member.favorites);
      } catch (error) {
        console.error("에러", error);
      }
    };
    fetchData();
  }, []);

  const handleButtonClick = async (restaurantId) => {
    try {
      if (!member.memberId) {
        alert("로그인을해주세요");
        nav(`/login`);
      }
      const isFavorite = userDataFavor.some(
        (fav) => fav.restaurantId === restaurantId,
      );

      if (isFavorite) {
        const favoriteToDelete = userDataFavor.find(
          (fav) => fav.restaurantId === restaurantId,
        );
        await api.delete(`/favorites/${favoriteToDelete.favoriteId}`);

        setUserDataFavor((prev) =>
          prev.filter((fav) => fav.favoriteId !== favoriteToDelete.favoriteId),
        );
      } else {
        await api.post(`/favorites/restaurant/${restaurantId}`);

        const response = await api.get("members/mypage");
        setMember({ ...member, favorites: response.data.favorites });
        setUserDataFavor(response.data.favorites);
      }

      setIsHeartActive(!isHeartActive);
    } catch (error) {
      console.error("Error updating favorites: ", error);
    }
  };

  const filterByLatest = (a, b) => {
    const dateA = new Date(a.createdAt);
    const dateB = new Date(b.createdAt);
    return dateB - dateA;
  };

  const filterByReview = (a, b) => {
    return b.total_reviews - a.total_reviews;
  };

  const filterByFavorites = (a, b) => {
    return b.totalFavorite - a.totalFavorite;
  };

  useEffect(() => {
    const fetchStores = async () => {
      try {
        let data = [];
        if (searchTagResults) {
          data = [...searchTagResults];
        } else if (results) {
          data = [...results];
        } else {
          data = [...stores];
        }
        if (currentFilter === "createdAt") {
          data.sort(filterByLatest);
        } else if (currentFilter === "total_reviews") {
          data.sort(filterByReview);
        } else if (currentFilter === "totalFavorite") {
          data.sort(filterByFavorites);
        }
        setStores(data);
      } catch (error) {
        console.error("Error fetching data: ", error);

        setNoResult(true);
      }
    };

    fetchStores();
  }, [currentFilter, results, searchTagResults]);

  const handlePageClick = ({ selected: selectedPage }) => {
    setCurrentPage(selectedPage);
  };

  const handleClick = () => {
    setIsHeartActive(!isHeartActive);
  };

  const offset = currentPage * resultsPerPage;
  const currentPageData = stores
    .slice(offset, offset + resultsPerPage)
    .map((store) => (
      <DataContentWrap key={store.restaurantId}>
        <Link to={`/detail/${store.restaurantId}`} onClick={handleClick}>
          <CardWrap>
            <StoreCard>
              <div className="photoUrl">
                {store.image ? (
                  <img src={store.image} alt={store.restaurantName} />
                ) : (
                  "등록된 이미지가 없습니다."
                )}
              </div>
              <p className="category">{store.category}</p>
              <h2 className="restaurantName">{store.restaurantName}</h2>
              <p className="content">{store.content}</p>
              <div className="Count">
                <p>즐겨찾기 : {store.totalFavorite}</p>
                <p>리뷰 : {store.total_reviews}</p>
              </div>
              <div className="tagRestaurants">
                {store.tagRestaurants
                  ? store.tagRestaurants
                      .map((tag, index) => (
                        <button key={index} className="tagButton">
                          {tag.tag.name}
                        </button>
                      ))
                      .slice(0, 3)
                  : null}
              </div>
            </StoreCard>
          </CardWrap>
        </Link>
        <BtnPosition>
          <ImgBtn
            imgstyle={
              userDataFavor &&
              userDataFavor.some(
                (favorites) => favorites.restaurantId === store.restaurantId,
              )
                ? "HeartActive"
                : "Heart"
            }
            onClick={() => handleButtonClick(store.restaurantId)}
          />
        </BtnPosition>
      </DataContentWrap>
    ));

  const pageCount = Math.ceil(stores.length / resultsPerPage);

  return (
    <>
      <StoreListBox>
        <Title>등록된 가게 리스트</Title>
        <ResultFilter>
          <Total>총 {stores.length}개의 검색결과가 있습니다!</Total>
          <FilterUl>
            <FilterLi
              onClick={() => setCurrentFilter("createdAt")}
              className={currentFilter === "createdAt" ? "active" : ""}
            >
              최신순
            </FilterLi>
            <FilterLi
              onClick={() => setCurrentFilter("total_reviews")}
              className={currentFilter === "total_reviews" ? "active" : ""}
            >
              리뷰순
            </FilterLi>
            <FilterLi
              onClick={() => setCurrentFilter("totalFavorite")}
              className={currentFilter === "totalFavorite" ? "active" : ""}
            >
              즐겨찾기순
            </FilterLi>
          </FilterUl>
        </ResultFilter>
        {noResult ? <NoResult /> : <ResultList>{currentPageData}</ResultList>}
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
  width: 100%;
  justify-content: flex-start;
  gap: 20px;
  > a {
    width: 100%;
    max-width: calc(100% / 2 - 10px);

    display: flex;
    flex-wrap: wrap;
    color: inherit;
  }
`;

const DataContentWrap = styled.div`
  position: relative;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  a {
    color: inherit;
  }
`;
const CardWrap = styled.div`
  width: 100%;
  position: relative;
`;
const BtnPosition = styled.div`
  position: absolute;
  top: 20px;
  right: 20px;
  background-color: transparent;
  svg {
    width: 30px;
    height: 30px;
    background-color: transparent;
  }
`;

const StoreCard = styled.li`
  height: 432px;
  cursor: pointer;
  width: 390px;
  border-radius: 30px;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: flex-start;
  padding: 215px 24px 22px;
  gap: 20px;
  border: 1px solid var(--black-100);
  > .photoUrl {
    width: 100%;
    position: absolute;
    left: 0;
    top: 0;
    border-bottom: 1px solid var(--black-100);
    img {
      width: 100%;
      height: 185px;
      object-fit: cover;
      object-position: center;
      border-radius: 30px 30px 0 0;
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
    width: 100%;
    /* overflow-x: scroll; */
    display: flex;
    flex-wrap: wrap;
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
    color: #636363;
    font-size: 1rem;
  }

  ul.pagination li.pagination__link--active a {
    color: white;
  }

  ul.pagination li.pagination__link--active {
    background-color: var(--eatsgreen);
    border: 1px solid var(--eatsgreen);
    a:hover {
      color: white;
    }
  }

  ul.pagination li a:hover,
  ul.pagination li a.active {
    color: var(--eatsgreen);
    font-weight: 700;
  }
  .page-selection {
    width: 48px;
    height: 30px;
    color: #337ab7;
  }
`;
