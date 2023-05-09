import Main from "../pages/main";
import Review from "../pages/review";

const RouteList = [
  {
    path: "/review",
    element: <Review />,
  },
  {
    path: "/",
    element: <Main />,
  },
];

export default RouteList;
