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

// api.interceptors.request.use(function (config) {
//   api.defaults.headers.common["Authorization"] = config.headers.authorization;
//   localStorage.setItem("token", config.headers.authorization);
//   console.log(config);
//   return config;
// });

// 토큰 재발급 요청....
// api.interceptors.response.use(
//   function (response) {
//     return response;
//   },
//   function (error) {
//     if (error.message === "refresh" && error.config.status === "401") {
//       try {
//         axios.defaults.headers.common["Authorization"] =
//           error.config.header.authorization;
//       } catch (e) {
//         console.log(e.error);
//       }
//     }
//     return Promise.reject(error);
//   },
// );

export { api };
