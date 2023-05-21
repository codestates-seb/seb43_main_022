import styled, { css } from "styled-components";

const InputType = {
  default: css`
    --width: ${(props) => props.width || "293px"};
    --height: ${(props) => props.height || "41px"};
  `,
  error: css`
    --width: ${(props) => props.width || "293px"};
    --height: ${(props) => props.height || "41px"};
    --border: 1px solid var(--red-500);
  `,
  keyword: css`
    --width: ${(props) => props.width || "293px"};
    --height: ${(props) => props.height || "41px"};
    --border: ${(props) => props.border || "1px solid black"};
    --border-radius: ${(props) => props.radius || "10px"};
  `,
};

const TextInput = styled.input`
  ${(p) => p.inputType}

  width: var(--width, 293px);
  height: var(--height, 41px);
  font-size: var(--medium-font);
  border: var(--border, 1px solid var(--black-200));
  border-radius: var(--border-radius, 10px);
  padding: 0px 10px;
  white-space: pre-wrap;
  &:active,
  &:focus {
    outline: none;
  }
`;

function Input({
  type,
  placeholder,
  onChange,
  onKeyPress,
  name,
  value,
  id,
  width,
  height,
  radius,
  readOnly,
  inputType,
}) {
  const inputStyle = InputType[inputType];

  return (
    <TextInput
      type={type}
      placeholder={placeholder}
      onChange={onChange}
      onKeyPress={onKeyPress}
      id={id}
      name={name}
      value={value}
      width={width}
      height={height}
      radius={radius}
      readOnly={readOnly}
      inputType={inputStyle}
    ></TextInput>
  );
}

export default Input;
