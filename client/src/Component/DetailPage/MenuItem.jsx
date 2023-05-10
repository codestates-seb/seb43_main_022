import React from "react";
import styled from "styled-components";

const Container = styled.div``;
const Menu = styled.div`
  margin-bottom: 30px;
  display: flex;
  justify-content: space-between;
  > .name {
    font-size: var(--medium-font);
  }
  > .price {
    font-size: var(--medium-font);
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
