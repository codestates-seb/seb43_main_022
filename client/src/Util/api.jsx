import axios from "axios";

const baseURL =
  process.env.NODE_ENV === "production"
    ? process.env.REACT_APP_API_URL
    : "http://ec2-54-180-31-226.ap-northeast-2.compute.amazonaws.com:8080";

const api = axios.create({
  baseURL,
  timeout: 3000,
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use(function (config) {
  config.headers["authorization"] =
    window.sessionStorage.getItem("Authorization");

  console.log(config);
  return config;
});

export { api };
