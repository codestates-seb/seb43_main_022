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

export { api };
