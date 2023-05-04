import GlobalStyles from "./Globalstyle";
import styled from "styled-components";

const Div = styled.div`
  background: var(--eatsgreen);
  width: 300px;
  height: 300px;
`;

function App() {
  return (
    <>
      <GlobalStyles />
      <div className="App">
        <Div>dd</Div>
      </div>
    </>
  );
}

export default App;
