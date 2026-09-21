import { useState, useContext } from "react";
import { ThemeContext } from "./ThemeContext";

export default function ThemeContextExample() {
   const [theme, setTheme] = useState("light");
   const [count, setCount] = useState(0);

   //setCount((prevCount) => prevCount + 1); // Increment count on every render
   //setCount(count + 1); // Increment count on every render

   const incrementCount = () => {
      //setCount((prevCount) => prevCount + 1); // Increment count on button click
      setCount((prevCount) => prevCount + 1);
      setCount((prevCount) => prevCount + 1);
      setCount((prevCount) => prevCount + 1);
      console.log("Count incremented to:", count); // Log the updated count
   };

   // Toggle function to switch themes
   const toggleTheme = () => {
      setTheme((prevTheme) => (prevTheme === "light" ? "dark" : "light"));
   };

   return (
      // Wrap children with ThemeContext.Provider and pass the current theme value
      <ThemeContext.Provider value={{ theme, incrementCount, toggleTheme }}>
         <div style={styles.container(theme)}>
            <h1>useContext Example</h1>
            <p>Current Theme: {theme}</p>
            <p>Count: {count}</p>
            <button onClick={incrementCount} style={{ marginBottom: "20px" }}>
               Increment Count
            </button>
            <Toolbar />
         </div>
      </ThemeContext.Provider>
   );
}

// Intermediate component (does not need props passed down manually!)
function Toolbar() {
   return (
      <div style={{ marginTop: "20px" }}>
         <ThemedButton />
      </div>
   );
}

// Deeply nested component that actually consumes the context
function ThemedButton() {
   // Consume the context values using useContext
   const { theme, toggleTheme } = useContext(ThemeContext);

   return (
      <button
         onClick={toggleTheme}
         style={{
            padding: "10px 20px",
            backgroundColor: theme === "light" ? "#333" : "#FFF",
            color: theme === "light" ? "#FFF" : "#333",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
         }}
      >
         Switch to {theme === "light" ? "Dark" : "Light"} Mode
      </button>
   );
}

// Helper styles for the main container
const styles = {
   container: (theme) => ({
      padding: "40px",
      height: "100vh",
      backgroundColor: theme === "light" ? "#FFF" : "#222",
      color: theme === "light" ? "#000" : "#FFF",
      transition: "all 0.3s ease",
   }),
};
