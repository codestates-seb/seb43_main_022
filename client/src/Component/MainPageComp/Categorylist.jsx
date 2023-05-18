import styled from "styled-components";
import { useRecoilState } from "recoil";
import { categoryState } from "../../state/atoms/CategoryAtom";

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
    ul {
      display: flex;
      flex-direction: row;
      justify-content: center;
      li {
        width: 200px;
        max-width: 240px;
        height: 14vh;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 20px;
        background: red;
        margin: 10px;
        overflow: hidden;
        position: relative;
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
    }
    .left-button {
      display: inline-block;
      background-image: url(https://mp-seoul-image-production-s3.mangoplate.com/web/resources/2018022864551sprites_desktop.png);
      background-position: -935px -230px;
    }
  }
`;
const Categorylist = () => {
  const [categoryData] = useRecoilState(categoryState);
  console.log(categoryData);
  return (
    <CategoryContainer className="Category-Container">
      <div className="category-title">카테고리</div>
      <div className="category-list">
        <button className="left-button" />
        <ul>
          {categoryData.map((name, idx) => (
            <li key={idx}>
              <div className="category-img">
                <img
                  src="https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg"
                  alt={`${name} 그림`}
                />
                <div className="category-text">
                  <p>{name}</p>
                </div>
              </div>
            </li>
          ))}
        </ul>
        <button className="right-button" />
      </div>
    </CategoryContainer>
  );
};
export default Categorylist;
