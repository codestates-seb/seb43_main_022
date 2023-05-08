import GlobalStyles from "./Globalstyle";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import RouteList from "./routes/RouteList";

function App() {
  return (
    <BrowserRouter>
      <GlobalStyles />
      <div className="container">
        <Routes>
          {RouteList.map((route, idx) => (
            <Route path={route.path} element={route.element} key={idx} />
          ))}
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
