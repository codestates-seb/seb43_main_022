import axios from "axios";

const baseURL =
  process.env.NODE_ENV === "production"
    ? process.env.REACT_APP_API_URL
    : // : "https://0524-58-122-102-109.ngrok-free.app";
      // : "http://ec2-43-202-53-65.ap-northeast-2.compute.amazonaws.com:8080";
      // "https://6e78-58-122-102-109.ngrok-free.app";
      "http://localhost:4003";
const api = axios.create({
  baseURL,
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use(function (config) {
  config.headers["authorization"] =
    window.sessionStorage.getItem("Authorization");
  return config;
});

export { api };
