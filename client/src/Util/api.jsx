import axios from "axios";

const baseURL =
  process.env.NODE_ENV === "production"
    ? process.env.RESOUCE_URL
    : "http://ec2-54-180-31-226.ap-northeast-2.compute.amazonaws.com:8080";

const api = axios.create({
  baseURL,
  timeout: 3000,
  headers: {
    "Content-Type": "application/json",
  },
});

// api.interceptors.request.use(
//   async (config) => {
//     const token = sessionStorage.getItem("access_token");
//     if (token) {
//       config.headers["Authorization"] = `Bearer ${token}`; //여기는 accessToken
//     }
//     return config;
//   },
//   (error) => {
//     return Promise.reject(error);
//   },
// );

// api.interceptors.response.use(
//   (response) => {
//     const res = response.data;
//     return res;
//   },
//   (error) => {
//     const { config, status } = error;
//     if (status === 401) {
//       if (error.response.data.message === "TokenExpiredError") {
//         const originalRequest = config; // 어떤 요청이 실패
//         const refreshToken = localStorage.getItem("refreshToken");
//         // token refresh 요청
//         const { data } = axios.post(
//           `${baseURL}/api/token/refresh/`, // token refresh api
//           {
//             refreshToken,
//           },
//         );
//         // 새로운 토큰 저장
//         sessionStorage.setItem("newAccessToken", data.accessToken);
//         localStorage.setItem("newRefreshToken", data.refreshToken);
//         originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
//         return api.request(originalRequest);
//       } else {
//         return Promise.reject(error);
//       }
//     } else {
//       return Promise.reject(error);
//     }
//   },
// );

export { api };
