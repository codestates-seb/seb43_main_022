import axios from "axios";

const baseURL =
  process.env.NODE_ENV === "production"
    ? process.env.RESOUCE_URL
    : "https://0524-58-122-102-109.ngrok-free.app";

const SignupApi = axios.create({
  baseURL,
  timeout: 3000,
  headers: {
    "Content-Type": "application/json",
  },
});

export { SignupApi };
