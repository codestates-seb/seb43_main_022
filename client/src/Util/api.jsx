import axios from "axios";

const baseURL = process.env.REACT_APP_API_URL;

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
