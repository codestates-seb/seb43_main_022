import Main from "../pages/main";
import Review from "../pages/review";
import StoreList from "./Pages/StoreList";
import AddStore from "./Pages/AddStore";
import Login from "./Pages/login";
import Signup from "./Pages/signup";
import DetailPage from "../pages/DetailPage";
import MyPage from "../pages/MyPage";

const RouteList = [
  {
    path: "/review",
    element: <Review />,
  },
  {
    path: "/",
    element: <Main />,
  },
  {
    path: "/itemlist",
    element: <StoreList />,
  },
  {
    path: "/addstore",
    element: <AddStore />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/signup",
    element: <Signup />,
  },
  {
    path: "/detail",
    element: <DetailPage />,
  },
  {
    path: "/mypage",
    element: <MyPage />,
  },
];

export default RouteList;
