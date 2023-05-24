import { createGlobalStyle } from "styled-components";

const GlobalStyles = createGlobalStyle`
  * {
    box-sizing: border-box;
    padding: 0;
    margin: 0;
    border: 0;
    font-family: -apple-system, "system-ui", "Segoe UI Adjusted", "Segoe UI", "Liberations Sans", sans-serif;
    font-weight: 400; 
    font-size: 13px;  
    
  }

  .container {
    min-width: 1200px;
    height: max-content;
    min-height: 100vh;
    margin: 0;
    padding: 10px 0;
    display: flex;
    justify-content: center;
  }
  
  body {
    background: #FEFEFE;
    width: 100%;
  
    position: ${(prop) => prop.posi || "none"};
    display: flex;
    justify-content: center;
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
    --red-500: hsl(0, 81%, 54%);
    --red-600: hsl(0, 100%, 26%);


    --x-small-font : 12px;
    --small-font : 13px;
    --medium-font: 16px;
    --large-font: 20px;
    --x-large-font: 22px;
    --xx-large-font: 28px;
    
  }
`;

export default GlobalStyles;
