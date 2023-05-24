import axios from "axios";

const baseURL = process.env.REACT_APP_API_URL;

const SignupApi = axios.create({
  baseURL,
  timeout: 3000,
  headers: {
    "Content-Type": "application/json",
  },
});

export { SignupApi };
