import React from "react";
import GlobalStyles from "./Globalstyle";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import StoreList from "./Pages/StoreList";
import AddStore from "./Pages/AddStore";

function App() {
  return (
    <BrowserRouter>
      <GlobalStyles />
      <div className="container">
        <Routes>
          <Route path="/" element={<StoreList />} />
          <Route path="/add" element={<AddStore />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
