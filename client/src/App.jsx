import { BrowserRouter, Routes, Route } from "react-router-dom";
import GlobalStyles from "./Globalstyle";
import Header from "./Component/Header";
import Footer from "./Component/Footer";
import Login from "./Pages/login";
import Signup from "./Pages/signup";

function App() {
  return (
    <BrowserRouter>
      <GlobalStyles />
      <Header />
      <div className="container">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
        </Routes>
      </div>

      <Footer />
    </BrowserRouter>
  );
}

export default App;
