import Line from "../Component/style/img/Line 2.png";
import styled from "styled-components";
const Cdiv = styled.ul`
  width: 300px;
  height: auto;
  padding: 10px;
  flex-direction: column;
`;

const Li = styled.ol`
  width: 100%;
  height: 10px;
  margin-bottom: 18px;
  font-size: var(--medium-font);
  display: flex;
  justify-content: space-between;
  align-items: center;
`;
const El = styled.div`
  width: 80px;
  text-align: left;
  margin-left: 10px;
`;
const Center = styled.img`
  width: 150px;
  height: 3px;
`;

export default function List({ menu, i }) {
  let Slice = menu.slice(i, i + 10);
  return (
    <Cdiv>
      {Slice.map((item) => {
        return (
          <Li key={item.menuId}>
            <El>{item.name}</El> <Center src={Line} />
            <El>{item.price}원</El>
          </Li>
        );
      })}
    </Cdiv>
  );
}
