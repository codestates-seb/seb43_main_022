import GlobalStyles from "./Globalstyle";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./Component/Header";
import Footer from "./Component/Footer";

import RouteList from "./routes/RouteList";

function App() {
  return (
    <BrowserRouter>
      <GlobalStyles />
      <Header />
      <div className="container">
        <Routes>
          {RouteList.map((route, idx) => (
            <Route path={route.path} element={route.element} key={idx} />
          ))}
        </Routes>
      </div>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
