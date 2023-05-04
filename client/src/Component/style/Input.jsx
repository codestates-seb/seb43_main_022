import styled, { css } from "styled-components";

const InputType = {
  default: css``,
  error: css`
    --width: ${(props) => props.width || "293px"};
    --height: ${(props) => props.height || "41px"};
    --border: 1px solid var(--red-500);
  `,
};

const TextInput = styled.input`
  ${(p) => p.inputType}

  width: var(--width, 293px);
  height: var(--height, 41px);
  font-size: var(--medium-font);
  border: var(--border, 1px solid var(--black-200));
  border-radius: 10px;
  padding: 0px 10px;

  &:active,
  &:focus {
    outline: none;
  }
`;

function Input({
  type,
  placeholder,
  onChange,
  value,
  id,
  width,
  height,
  inputType,
}) {
  const inputStyle = InputType[inputType];

  return (
    <TextInput
      type={type}
      placeholder={placeholder}
      onChange={onChange}
      id={id}
      value={value}
      width={width}
      height={height}
      inputType={inputStyle}
    ></TextInput>
  );
}

export default Input;
