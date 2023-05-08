import GlobalStyles from "./Globalstyle";
import styled from "styled-components";
import Input from "./Component/style/Input";
import Header from "./Component/Header";
import Footer from "./Component/Footer";
function App() {
  return (
    <>
      <GlobalStyles />
      <Header />
      <div className="container"></div>
      <Footer />
    </>
  );
}

export default App;
