import Main from "../Pages/Main";
import Review from "../Pages/Review";
import StoreList from "../Pages/StoreList";
import AddStore from "../Pages/AddStore";
import Login from "../Pages/login";
import Signup from "../Pages/Signup";
import DetailPage from "../Pages/DetailPage";
import MyPage from "../Pages/MyPage";

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
