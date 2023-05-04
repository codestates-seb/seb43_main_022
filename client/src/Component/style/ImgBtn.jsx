import styled from "styled-components";

import { ReactComponent as Plus } from "./img/plus.svg";
import { ReactComponent as Heart } from "./img/heart.svg";
import { ReactComponent as Like } from "./img/like.svg";
import { ReactComponent as Share } from "./img/share.svg";
import { ReactComponent as Hate } from "./img/hate.svg";

const Btn = styled.button`
  border: none;
`;

export default function Plusfunc({ imgstyle }) {
  let color = "";
  let type = "";

  const selecter = (imgstyle) => {
    switch (imgstyle) {
      case "Plus":
        type = Plus;
        color = `var(--eatsgreen)`;
        break;
      case "Share":
        type = Share;
        color = "var(--eatsgreen)";
        break;
      case "Like":
        type = Like;
        color = "var(--eatsgreen)";
        break;
      case "Heart":
        type = Heart;
        color = "var(--red-500)";
        break;
      case "Hate":
        type = Hate;
        color = "var(--red-600)";
        break;

      default:
        color = "var(--eatsgreen)";
        break;
    }
  };

  selecter(imgstyle);

  const Style = styled(type)`
    color: var(--black-200);
    &: hover {
      path {
        fill: ${color};
      }
    }
  `;
  return (
    <Btn>
      <Style />
    </Btn>
  );
}
