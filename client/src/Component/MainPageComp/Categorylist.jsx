import styled from "styled-components";
import { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { useNavigate } from "react-router";
import { categoryState } from "../../state/atoms/CategoryAtom";
import { searchKeywordState } from "../../state/atoms/SearchStateAtom";

import { api } from "../../Util/api";

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
        border: 1px solid var(--black-200);
        border-radius: 20px;
        margin: 10px;
        overflow: hidden;
        position: relative;
        box-shadow: 0px 1px 10px 1px var(--black-200);
        &:hover {
          box-shadow: 0px 1px 10px 1px var(--eatsgreen);
        }
        img {
          width: 200px;
          height: 15vh;
        }
        div {
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
        }
        p {
          width: 12vw;
          color: var(--black-050);
          font-size: 24px;
          font-weight: bold;
          text-align: center;
          text-shadow: -1px 0px var(--black-500), 0px 1px var(--black-500),
            1px 0px var(--black-500), 0px -1px var(--black-500);
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
      left: 100%;
    }
    .right-button:disabled {
      display: none;
    }
    .left-button {
      display: inline-block;
      background-image: url(https://mp-seoul-image-production-s3.mangoplate.com/web/resources/2018022864551sprites_desktop.png);
      background-position: -935px -230px;
      position: absolute;
      right: 100%;
    }
    .left-button:disabled {
      display: none;
    }
  }
`;
const Categorylist = () => {
  const [categoryData, setCategoryData] = useRecoilState(categoryState);
  const [, setSearchKeyword] = useRecoilState(searchKeywordState);
  const [currentPage, setCurrentPage] = useState(1);
  const [visibleCategory, setVisibleCategory] = useState([]);
  const navi = useNavigate();

  const pageSize = 5;

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await api.get(`/category?page=1&size=12`);
        setCategoryData(response.data.data);
      } catch (error) {
        console.error(error, "카테고리 안 불러옴");
      }
    };
    fetchCategories();
  }, [currentPage, setCategoryData]);

  const handleCategoryClick = async (name) => {
    setSearchKeyword(name);
    navi(`/itemlist?search=${name}`);
  };

  useEffect(() => {
    const startIdx = (currentPage - 1) * pageSize;
    const endIdx = startIdx + pageSize;
    setVisibleCategory(categoryData.slice(startIdx, endIdx));
  }, [currentPage, categoryData]);

  const prevPage = () => {
    setCurrentPage((page) => Math.max(page - 1, 1));
  };
  const nextPage = () => {
    setCurrentPage((page) => page + 1);
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
          {visibleCategory.map((category, idx) => (
            <li key={idx}>
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
                  src={`./CategoryImg/${category.name}.jpg`}
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
          disabled={visibleCategory.length < pageSize}
        />
      </div>
    </CategoryContainer>
  );
};
export default Categorylist;
