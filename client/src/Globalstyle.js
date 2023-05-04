import { createGlobalStyle } from "styled-components";

const GlobalStyles = createGlobalStyle`
  * {
    box-sizing: border-box;
    padding: 0;
    margin: 0;
    border: 0;
    font-family: -apple-system, "system-ui", "Segoe UI Adjusted", "Segoe UI", "Liberations Sans", sans-serif;
    font-weight: 400; 
    white-space: nowrap;
    font-size: 13px; 
  }
  
  .container {
    max-width: 1920px;
    margin: 0 auto;
    width: 1920;
    height: max-content;
    min-height: 1024px;
    display: flex;
    justify-content: center;
    margin: 0 auto 0;
    flex: 1 0 auto;
  }

  body {
    width: 1440px;
    height: 100vh;
    position: ${(prop) => prop.posi || "none"};
  }
  .root {
    height: 100vh;
  }


  a {
    text-decoration: none;
  }

  button {
    cursor: pointer;
  }

  :root {
    --white: hsl(0,0%,100%);
    --black-025: hsl(210,8%,98%);
    --black-050: hsl(210,8%,95%);
    --black-070: hsl(210,10%,90%);
    --black-100: hsl(216,10%,90%);
    --black-200: hsl(210,8%,75%);
    --black-350: hsl(210,8%,60%);
    --black-500: hsl(210,8%,45%);
    --black-600: hsl(210,8%,35%);
    --black-700: hsl(210,8%,25%);
    --black-800: hsl(210,8%,15%);
    --black-900: hsl(210,8%,5%);
    --green-700: hsl(140,41%,31%);
    --eatsgreen: hsl(144, 40%, 47%);
    --footergreen: hsl(144, 17%, 41%);


  }
`;

export default GlobalStyles;
