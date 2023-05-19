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
    left: 25%;
    width: 319px;

    height: 1px;
    background-color: var(--black-200);
    transform: translateY(-50%); /* 수직 가운데 정렬 */
  }
`;

const MenuItem = ({ menu }) => {
  const slicedMenu = menu ? menu.slice(0, 5) : [];

  return (
    <Container>
      {slicedMenu.map((item) => (
        <Menu key={item.menuId}>
          <span className="name">{item.name}</span>
          <span className="price">{item.price}원</span>
        </Menu>
      ))}
    </Container>
  );
};

export default MenuItem;
