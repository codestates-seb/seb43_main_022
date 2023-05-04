import styled, { css } from "styled-components";

const BtnType = {
  Btn: css`
    &: active {
      --box-shadow: 0px 1px 1px 0px #00000040;
    }
  `,
  Btn2: css`
    --width: 118px;
    --height: 35px;
    --background: var(--white);
    --border: 1px solid var(--black-900);
    --font-size: 16px;
    --color: var(--black-900);
  `,
  HBtn: css`
    --width: 87px;
    --height: 40px;
    --font-size: 24px;
    --color: var(--black-900);
    --border: none;

    &: hover {
      --hover-background: var(--white);
      font-weight: 500;
    }

    &: active {
      --active-background: var(--white);
      --active-color: var(--black-900);
    }
  `,
  SBtn: css`
    --width: 41px;
    --height: 27px;
    --font-size: 16px;
    --color: var(--black-350);
    --border: none;

    &: hover {
      --hover-background: var(--white);
      font-weight: 500;
    }

    &: active {
      --active-background: var(--white);
      --active-color: var(--eatsgreen);
    }
  `,

  SBtn2: css`
    --width: 41px;
    --height: 27px;
    --font-size: 22px;
    --color: var(--black-350);
    --border: none;

    &: hover {
      --hover-background: var(--white);
      font-weight: 500;
    }

    &: active {
      --active-background: var(--white);
      --active-color: var(--eatsgreen);
    }
  `,

  custom: css`
    --width: ${(props) => props.width || "auto"};
    --height: ${(props) => props.height || "38px"};
    --padding: ${(props) => props.padding || "0px 0px 0px 0px"};
    --margin: ${(props) => props.margin || "0px 0px 0px 0px"};
  `,
};

function Button({
  type,
  btnstyle,
  children,
  onClick,
  onChange,
  width,
  height,
  padding,
  margin,
}) {
  const btnStyle = BtnType[btnstyle];

  return (
    <StyleButton
      type={type}
      btnstyle={btnStyle}
      onClick={onClick}
      onChange={onChange}
      width={width}
      height={height}
      padding={padding}
      margin={margin}
    >
      {children}
    </StyleButton>
  );
}

export const StyleButton = styled.button`
  ${(p) => p.btnstyle}
  width: var(--width, 120px);
  height: var(--height, 46px);
  background: var(--background, var(--white));
  color: var(--color, var(--eatsgreen));
  border: var(--border, 1px solid var(--eatsgreen));
  border-radius: 30px;
  font-size: var(--font-size, 18px);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: var(--padding, 0px, 0px, 0px, 0px);
  margin: var(--margin, 0px, 0px, 0px, 0px);
  cursor: pointer;

  &: hover {
    background: var(--hover-background, var(--black-100));
    border: none;
  }

  &: active {
    background: var(--active-background, var(--eatsgreen));
    border: none;
    color: var(--active-color, var(--white));
    box-shadow: var(--box-shadow, 0px 0px 0px 0px none);
  }
`;

export default Button;
