import axios from "axios";

const baseURL =
  process.env.NODE_ENV === "production" ? process.env.RESOUCE_URL : "";

const api = axios.create({
  baseURL,
  timeout: 3000,
  headers: {
    "Content-Type": "application/json",
  },
});

export { api };
