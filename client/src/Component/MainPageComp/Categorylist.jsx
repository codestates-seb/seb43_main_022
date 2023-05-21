import styled from "styled-components";
import { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { categoryState } from "../../state/atoms/CategoryAtom";
import { searchTermState } from "../../state/atoms/SearchTermState";
import { api } from "../../Util/api";
import { useNavigate } from "react-router";

const CategoryContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .category-title {
    font-size: var(--xx-large-font);
    font-weight: bold;
    margin-bottom: 30px;
  }
  .category-list {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    position: relative;
    ul {
      display: flex;
      flex-direction: row;
      justify-content: center;
      li {
        cursor: pointer;
        width: 200px;
        max-width: 240px;
        height: 14vh;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 20px;
        margin: 10px;
        overflow: hidden;
        position: relative;
        transition: transform 0.3s;
        img {
          width: 200px;
        }
        div {
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
        }
        p {
          color: #fefefe;
          font-size: 24px;
          font-weight: bold;
          text-align: center;
        }
      }
    }
    button {
      width: 28px;
      height: 45px;
      margin: 10px;
    }
    .right-button {
      display: inline-block;
      background-image: url(https://mp-seoul-image-production-s3.mangoplate.com/web/resources/2018022864551sprites_desktop.png);
      background-position: -935px -179px;
      position: absolute;
      right: 350px;
    }
    .right-button:disabled {
      display: none;
    }
    .left-button {
      display: inline-block;
      background-image: url(https://mp-seoul-image-production-s3.mangoplate.com/web/resources/2018022864551sprites_desktop.png);
      background-position: -935px -230px;
      position: absolute;
      left: 350px;
    }
    .left-button:disabled {
      display: none;
    }
  }
`;
const Categorylist = () => {
  const [categoryData, setCategoryData] = useRecoilState(categoryState);
  const [searchTerm, setSearchTerm] = useRecoilState(searchTermState);
  const [currentPage, setCurrentPage] = useState(1);
  const [nextPageData, setNextPageData] = useState([]);
  const navi = useNavigate();

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await api.get(`/category?page=${currentPage}&size=5`);
        setCategoryData(response.data.data);
        // 다음 페이지 데이터를 미리 불러옴
        if (response.data.data.length > 0) {
          const nextResponse = await api.get(
            `/category?page=${currentPage + 1}&size=5`,
          );
          setNextPageData(nextResponse.data.data);
        }
      } catch (error) {
        console.error(error);
      }
    };
    fetchCategories();
  }, [currentPage, setCategoryData]);

  const handleCategoryClick = (name) => {
    setSearchTerm(name);
    navi(`/itemlist?serch=${searchTerm}`);
  };

  const prevPage = () => {
    setCurrentPage((page) => Math.max(page - 1, 0));
    console.log(currentPage, "이전");
  };
  const nextPage = () => {
    setCurrentPage((page) => page + 1);
    setCategoryData(nextPageData);
    console.log(currentPage, "앞으로");
  };
  return (
    <CategoryContainer className="Category-Container">
      <div className="category-title">카테고리</div>
      <div className="category-list">
        <button
          className="left-button"
          onClick={prevPage}
          disabled={!(currentPage - 1)}
        />
        <ul>
          {categoryData.map((category) => (
            <li key={category.categoryid}>
              <div
                className="category-img"
                role="button"
                tabIndex={0}
                onClick={() => handleCategoryClick(category.name)}
                onKeyDown={(event) => {
                  if (event.key === "Enter" || event.key === " ") {
                    handleCategoryClick(category.name);
                  }
                }}
              >
                <img
                  src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
                  alt={`${category.name} 그림`}
                />
                <div className="category-text">
                  <p>{category.name}</p>
                </div>
              </div>
            </li>
          ))}
        </ul>
        <button
          className="right-button"
          onClick={nextPage}
          disabled={!nextPageData.length}
        />
      </div>
    </CategoryContainer>
  );
};
export default Categorylist;
