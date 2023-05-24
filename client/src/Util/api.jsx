import axios from "axios";

const baseURL =
  process.env.NODE_ENV === "production"
    ? process.env.REACT_APP_API_URL
    : "https://0524-58-122-102-109.ngrok-free.app";

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
