import React from "react";
import styled from "styled-components";

const Container = styled.div``;
const Menu = styled.div`
  margin-bottom: 30px;
  display: flex;
  justify-content: space-between;
  position: relative;
  > .name {
    font-size: var(--medium-font);
  }
  > .price {
    font-size: var(--medium-font);
  }
  &::before {
    content: "";
    position: absolute;
    top: 50%;
    left: 28%;
    width: 319px;

    height: 1px;
    background-color: var(--black-200);
    transform: translateY(-50%); /* 수직 가운데 정렬 */
  }
`;

const MenuItem = () => {
  return (
    <Container>
      <Menu>
        <span className="name">맛있는 샌드위치</span>
        <span className="price">9,200 원</span>
      </Menu>
      <Menu>
        <span className="name">맛있는 샌드위치</span>
        <span className="price">9,200 원</span>
      </Menu>
    </Container>
  );
};

export default MenuItem;
